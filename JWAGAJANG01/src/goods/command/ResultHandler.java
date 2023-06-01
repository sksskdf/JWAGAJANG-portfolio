package goods.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import goods.dao.GoodsDAO;
import goods.dto.GoodsVO;
import goods.dto.MdListModel;
import goods.dto.Paging;

public class ResultHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			return processForm(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception  {
		
		String searchkeyword_ = req.getParameter("schText");		
		// 사용자가 값을 전달하지 않았을 때 기본값을 넣는다.
		String searchkeyword = "";
		if(searchkeyword_ != null)
			searchkeyword = searchkeyword_;

		req.setAttribute("searchkeyword", searchkeyword);
		GoodsDAO gDao = GoodsDAO.getInstance();
		
		int searchCount = gDao.getSearchCount(searchkeyword);
		

		// 페이징
		String pageNumberString = req.getParameter("p");
		String category_main = req.getParameter("category_main");
		String category_sub = req.getParameter("category_sub");		// null
		String viewPage = "/result.jsp";
		int pageNumber = 1;
		if (pageNumberString != null && pageNumberString.length() > 0) {
			pageNumber = Integer.parseInt(pageNumberString);
		}
		
		Paging paging = new Paging(8, 10);
		paging.setCurrentPageNo(pageNumber);
		
		int totalMdCount = gDao.selectCount(category_main, category_sub);
		List<GoodsVO> mdList = null;
		
		if (totalMdCount == 0) {
			paging.setStartPageNo(1);
			mdList = new ArrayList<GoodsVO>();
		}
		paging.setNumberOfRecords(totalMdCount);
		paging.makePaging();
		
		int firstRow = (pageNumber - 1) * paging.getRecordsPerPage() + 1;
		int endRow = firstRow + paging.getRecordsPerPage() - 1;
		if (endRow > totalMdCount) {
			endRow = totalMdCount;
		}
		
		mdList = gDao.search(searchkeyword, firstRow, endRow);
		
		MdListModel listModel = new MdListModel();
		listModel.setMdList(mdList);
		listModel.setPaging(paging);
		req.setAttribute("listModel", listModel);
		req.setAttribute("searchCount", searchCount);
		
		res.setHeader("Pragma", "No-cache");
		res.setHeader("Cache-Control", "no-cache");
		res.addHeader("Cache-Control", "no-store");
		res.setDateHeader("Expires", 1L);
		
		req.setAttribute("category_main", category_main);
		return viewPage;
	}
}
