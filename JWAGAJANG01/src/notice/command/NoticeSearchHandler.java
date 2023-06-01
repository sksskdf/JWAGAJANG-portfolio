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


public class NoticeSearchHandler implements CommandHandler {
	private static final String FORM_VIEW = "/notice.jsp";

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
		
		// 옵션으로 들어온 인자를 사용하는 방법
		String searchoption_ = req.getParameter("searchoption"); // 입력도구인 req한테 searchoption을 이용해서  getParameter한다.
		String searchkeyword_ = req.getParameter("searchkeyword");
		
		// 사용자가 값을 전달하지 않았을 때 기본값을 넣는다.
		String searchoption = "notice_title";
		if(searchoption_ != null)
			searchoption = searchoption_;
		String searchkeyword = "";
		if(searchkeyword_ != null)
			searchkeyword = searchkeyword_;
	
		req.setAttribute("searchkeyword", searchkeyword);
		NoticeDAO nDao = NoticeDAO.getInstance();
		List<NoticeVO> list = null;

		
		// 페이징을 꼭 넣어줘야함! : notice.jsp에서도 그렇게 했기때문에
		String pageNumberString = req.getParameter("p"); // 브라우저에서 목록을 보면 p=null; 페이징 링크를 누르면 p=n;
		int pageNumber = 1;
		if (pageNumberString != null && pageNumberString.length() > 0) { // p값이 들어왔는지 안들어왔는지
			pageNumber = Integer.parseInt(pageNumberString); // 들어왔으면  String 타입의 변수를 int 타입의 변수로  바꿔서 넣는다.
		}
		
		
		Paging paging = new Paging(10, 10); // 나타낼 목록, 몇 페이지를 보여줄건지
		paging.setCurrentPageNo(pageNumber); // 현재 페이지 설정

		int totalBoardCount = nDao.searchselectCount(searchoption, searchkeyword); // 전체 게시글의 수를 얻는다 
		if (totalBoardCount == 0) {
			paging.setStartPageNo(1);
			list = new ArrayList<NoticeVO>();
		}	
		paging.setNumberOfRecords(totalBoardCount);	// 전체 게시글의 수를 얻어와서
		paging.makePaging();
		int firstRow = (pageNumber - 1) * paging.getRecordsPerPage() + 1; // 계산
		int endRow = firstRow + paging.getRecordsPerPage() - 1;

		if (endRow > totalBoardCount) {
			endRow = totalBoardCount;
		}
		
		NoticeListModel listModel = new NoticeListModel();
		if(searchoption_ == null) {
			list = nDao.select(firstRow, endRow); // 첫번째 열, 마지막 열 
		}else {
			list = nDao.search(searchoption, searchkeyword, firstRow, endRow);
		}
			
		listModel.setNoticeList(list); //  noticeList는 원래 목록, list는 search목록
		listModel.setPaging(paging);		 // 페이징 정보
		req.setAttribute("listModel", listModel); // jsp로 전달
		req.setAttribute("tbc", totalBoardCount);
		
		res.setHeader("Pragma", "No-cache"); // 캐시 삭제하도록 설정 : 게시글을 추가했는데 캐시에 있는걸 보여주면 추가한게 안나오기떄문에
		res.setHeader("Cache-Control", "no-cache");
		res.addHeader("Cache-Control", "no-store");
		res.setDateHeader("Expires", 1L);
		return FORM_VIEW;
	}
}