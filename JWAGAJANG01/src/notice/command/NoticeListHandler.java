package notice.command;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import common.command.CommandHandler;
import notice.dao.NoticeDAO;
import notice.dto.NoticeListModel;
import notice.dto.NoticeVO;
import notice.dto.Paging;

public class NoticeListHandler implements CommandHandler {
	private static final String FORM_VIEW = "/notice.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
/*
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String url = "/notice.jsp";
		NoticeDAO bDao = NoticeDAO.getInstance();
		List<NoticeVO> noticeList = bDao.selectAllBoards();
		req.setAttribute("noticeList", noticeList);
		return url;
	}
}

*/
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws SQLException, NamingException {

		String pageNumberString = req.getParameter("p"); // 브라우저에서 목록을 보면 p=null; 페이징 링크를 누르면 p=n;
		int pageNumber = 1;
		if (pageNumberString != null && pageNumberString.length() > 0) { // p값이 들어왔는지 안들어왔는지
			pageNumber = Integer.parseInt(pageNumberString); // 들어왔으면  String 타입의 변수를 int 타입의 변수로  바꿔서 넣는다.
		}
		Paging paging = new Paging(10, 10); // 나타낼 목록, 몇 페이지를 보여줄건지
		paging.setCurrentPageNo(pageNumber); // 현재 페이지 설정

		NoticeDAO nDao = NoticeDAO.getInstance();
		int totalBoardCount = nDao.selectCount(); // 전체 게시글의 수를 얻는다 
		
		List<NoticeVO> noticeList = null;
		if (totalBoardCount == 0) {
			paging.setStartPageNo(1);
			noticeList = new ArrayList<NoticeVO>();
		}
		paging.setNumberOfRecords(totalBoardCount);	// 전체 게시글의 수를 얻어와서
		paging.makePaging();
		int firstRow = (pageNumber - 1) * paging.getRecordsPerPage() + 1; // 계산
		int endRow = firstRow + paging.getRecordsPerPage() - 1;

		if (endRow > totalBoardCount) {
			endRow = totalBoardCount;
		}
		noticeList = nDao.select(firstRow, endRow); // 첫번째 열, 마지막 열
		NoticeListModel listModel = new NoticeListModel();
		listModel.setNoticeList(noticeList); // 가져온 목록
		listModel.setPaging(paging);		 // 페이징 정보
		req.setAttribute("listModel", listModel); // jsp로 전달


		res.setHeader("Pragma", "No-cache"); // 캐시 삭제하도록 설정 : 게시글을 추가했는데 캐시에 있는걸 보여주면 추가한게 안나오기떄문에
		res.setHeader("Cache-Control", "no-cache");
		res.addHeader("Cache-Control", "no-store");
		res.setDateHeader("Expires", 1L);
		return FORM_VIEW;
	}
}