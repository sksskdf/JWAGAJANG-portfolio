package goods.command;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.command.CommandHandler;
import goods.dao.GoodsDAO;
import goods.dto.GoodsVO;
import member.service.FavService;

public class GoodsHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	public String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();

		int md_code = Integer.parseInt(req.getParameter("md_code"));
		String userid = (String) session.getAttribute("id");

		// 상품
		GoodsDAO gDao = GoodsDAO.getInstance();
		GoodsVO md = gDao.getMd(md_code);

		// 최근 본 상품 쿠키 정보 가져와서 브라우저로 상품 목록을 전달
		List<String> ckList = getValueList("md_code", req);
		List<GoodsVO> goodsList = new ArrayList<GoodsVO>();
		if (ckList != null) {
			for (String code: ckList) {
				GoodsVO gVo = gDao.getMd(Integer.parseInt(code));
				goodsList.add(gVo);
			}
		}
		req.setAttribute("visited", goodsList);
		
		// 현재 상품을 쿠키에 추가
		setCookie("md_code", String.valueOf(md_code), 1, req, res);


		// 찜 목록
		FavService fs = FavService.getInstance();
		Integer dupchk = fs.checkFavDup(userid, md_code);
		if (dupchk != null) {
			req.setAttribute("dupchk", dupchk);
		}

		req.setAttribute("md", md);
		req.setAttribute("md_code", md_code);

		// 리뷰 수에 따른 리뷰 리스트
		List<GoodsVO> reviewList;

		int count = gDao.reviewCount(md_code);

		if (count > 0) {
			reviewList = gDao.getReviewList(count, md_code);
			req.setAttribute("reviewList", reviewList);
		}

		req.setAttribute("count", new Integer(count));

		return "/goods.jsp";

	}

	// 최근 본 상품
	private static final String encoding = "UTF-8";
	private static final String path = "/";

	/**
	 * @description 특정 key의 쿠키값을 List로 반환한다.
	 * @params key: 쿠키 이름
	 */
	public List<String> getValueList(String key, HttpServletRequest req) throws UnsupportedEncodingException {
		Cookie[] cookies = req.getCookies();
		String[] cookieValues = null;
		String value = "";
		List<String> ckList = null;

		// 특정 key의 쿠키값을 ","로 구분하여 String 배열에 담아준다.
		// ex) 쿠키의 key/value ---> key = "clickItems", value = "211, 223, 303"(상품 번호)
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(key)) {
					value = cookies[i].getValue();
					cookieValues = (URLDecoder.decode(value, encoding)).split(",");
					break;
				}
			}
		}
		// String 배열에 담겼던 값들을 List로 다시 담는다.
		if (cookieValues != null) {
			ckList = new ArrayList<String>(Arrays.asList(cookieValues));

			if (ckList.size() > 3) { // 값이 3개를 초과하면, 최근 것 3개만 담는다.
				int start = ckList.size() - 3;
				List<String> copyList = new ArrayList<String>();
				for (int i = start; i < ckList.size(); i++) {
					copyList.add(ckList.get(i));
				}
				ckList = copyList;
			}
		}
		return ckList;
	}

	/**
	 * @description 쿠키를 생성 혹은 업데이트한다.
	 * @params key: 쿠키 이름, value: 쿠키 이름과 짝을 이루는 값, day: 쿠키의 수명
	 */
	public void setCookie(String key, String value, int day, HttpServletRequest req, HttpServletResponse res)
			throws UnsupportedEncodingException {
		List<String> ckList = getValueList(key, req);
		String sumValue = "";
		int equalsValueCnt = 0;

		if (ckList != null) {
			for (int i = 0; i < ckList.size(); i++) {
				sumValue += ckList.get(i) + ",";
				if (ckList.get(i).equals(value)) {
					equalsValueCnt++;
				}
			}
			if (equalsValueCnt != 0) { // 같은 값을 넣으려고 할 때의 처리
				if (sumValue.substring(sumValue.length() - 1).equals(",")) {
					sumValue = sumValue.substring(0, sumValue.length() - 1);
				}
			} else {
				sumValue += value;
			}
		} else {
			sumValue = value;
		}
		if (!sumValue.equals("")) {
			Cookie cookie = new Cookie(key, URLEncoder.encode(sumValue, encoding));
			cookie.setMaxAge(60 * 60 * 24 * day);
			cookie.setPath(path);
			res.addCookie(cookie);
		}
	}
}
