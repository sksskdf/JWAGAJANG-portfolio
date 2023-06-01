package cart.command;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.dto.CartVO;
import common.command.CommandHandler;

public class CartPutHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("POST")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("GET")){
			return "cart.jsp";
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		ArrayList<CartVO> cartList = null;
		
		String md_code = req.getParameter("md_code");
		String md_name = req.getParameter("md_name");
		String img_main = req.getParameter("img_main");
		String md_price = req.getParameter("md_price");
		String md_dc = req.getParameter("md_dc");
		String md_count = req.getParameter("md_count");
		
		
		Object obj = session.getAttribute("cartList");	//세션 객체에서 cart 값을 가져온다.

		if(obj == null) {	//세션 정보가 없으면 배열을 생성 : 최초 주문한 경우
			cartList = new ArrayList<CartVO>();	
		} else {			//세션 정보가 있으면 강제로 캐스팅 : 추가 주문
			cartList = (ArrayList<CartVO>) obj;
		}
		
		int pos =-1;	//등록된 제품 X.
		
		//장바구니 세션에 동일한 제품이 있을 경우 : 수량(count)증가
		for (int i = 0; i < cartList.size(); i++) {
			CartVO cVo = cartList.get(i);
			if(cVo.getMd_code().equals(md_code)) {
				pos = 1;
				cVo.setMd_count(cVo.getMd_count() + Integer.parseInt(md_count));
				break;
			}
		}
		//장바구니 세션에 등록된 제품이 없을 경우 : VO 객체를 생성하여 배열에 등록
		if(pos==-1) {
			CartVO cVo = new CartVO();
			cVo.setMd_code(md_code);
			cVo.setMd_name(md_name);
			cVo.setImg_main(img_main);
			cVo.setMd_price(Integer.parseInt(md_price.replace(",", "")));
			cVo.setMd_dc(Integer.parseInt(md_dc.replace("%", "")));
			cVo.setMd_count(Integer.parseInt(md_count));
			cartList.add(cVo);
		}
		session.setAttribute("cartList", cartList);
		return "cart.jsp";
	}

}
