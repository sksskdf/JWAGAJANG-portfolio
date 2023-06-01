package qna.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import qna.dao.QnaDAO;
import qna.dto.Paging;
import qna.dto.QnaListModel;
import qna.dto.QnaVO;


public class QnaSearchHandler implements CommandHandler {
	private static final String FORM_VIEW = "/qnaList.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			return processForm(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws SQLException, NamingException  {
		
		String searchkeyword_ = req.getParameter("searchkeyword");		
		// 사용자가 값을 전달하지 않았을 때 기본값을 넣는다.
		String searchkeyword = "";
		if(searchkeyword_ != null)
			searchkeyword = searchkeyword_;

		req.setAttribute("searchkeyword", searchkeyword);
		QnaDAO qDao = QnaDAO.getInstance();
		List<QnaVO> list = null;

		// 페이징
		String pageNumberString = req.getParameter("p"); // 브라우저에서 목록을 보면 p=null; 페이징 링크를 누르면 p=n;
		int pageNumber = 1;
		if (pageNumberString != null && pageNumberString.length() > 0) { // p값이 들어왔는지 안들어왔는지
			pageNumber = Integer.parseInt(pageNumberString); // 들어왔으면  String 타입의 변수를 int 타입의 변수로  바꿔서 넣는다.
		}
		
		Paging paging = new Paging(10, 10); // 나타낼 목록, 몇 페이지를 보여줄건지
		paging.setCurrentPageNo(pageNumber); // 현재 페이지 설정

		int totalBoardCount = qDao.searchselectCount(searchkeyword); // 전체 게시글의 수를 얻는다 
		if (totalBoardCount == 0) {
			paging.setStartPageNo(1);
			list = new ArrayList<QnaVO>();
		}	
		paging.setNumberOfRecords(totalBoardCount);	// 전체 게시글의 수를 얻어와서
		paging.makePaging();
		int firstRow = (pageNumber - 1) * paging.getRecordsPerPage() + 1; // 계산
		int endRow = firstRow + paging.getRecordsPerPage() - 1;

		if (endRow > totalBoardCount) {
			endRow = totalBoardCount;
		}
		
		QnaListModel qnalistModel = new QnaListModel();
		list = qDao.search(searchkeyword, firstRow, endRow);
		
		qnalistModel.setQnaList(list); //  qnaList는 원래 목록, list는 search목록
		qnalistModel.setPaging(paging);		 // 페이징 정보
		req.setAttribute("qnalistModel", qnalistModel); // jsp로 전달
		req.setAttribute("tbc", totalBoardCount);
		
		res.setHeader("Pragma", "No-cache"); // 캐시 삭제하도록 설정 : 게시글을 추가했는데 캐시에 있는걸 보여주면 추가한게 안나오기떄문에
		res.setHeader("Cache-Control", "no-cache");
		res.addHeader("Cache-Control", "no-store");
		res.setDateHeader("Expires", 1L);
		return FORM_VIEW;
	}
}