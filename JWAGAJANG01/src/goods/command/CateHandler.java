package goods.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import goods.dao.GoodsDAO;
import goods.dto.GoodsVO;
import goods.dto.MdListModel;
import goods.dto.Paging;
import review.dao.ReviewDAO;

public class CateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws SQLException {
		String category_main = req.getParameter("category_main");
		String category_sub = req.getParameter("category_sub");
		String order = req.getParameter("order");
		String viewPage = "/cate.jsp";
		GoodsDAO gDao = GoodsDAO.getInstance();
		List<GoodsVO> mdList = null;
		req.setAttribute("category_main", category_main);
		int pageNumber = 1;
		String pageNumberString = req.getParameter("p");
		if (pageNumberString != null && pageNumberString.length() > 0) {
			pageNumber = Integer.parseInt(pageNumberString);
		}
		Paging paging = new Paging(8, 10);
		paging.setCurrentPageNo(pageNumber);
		
		int totalMdCount = gDao.selectCount(category_main, category_sub);
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
		try {
			mdList = gDao.sortMd(category_main, category_sub, order, firstRow, endRow, false);
			
			MdListModel listModel = new MdListModel();
			listModel.setMdList(mdList);
			listModel.setPaging(paging);
			req.setAttribute("listModel", listModel);
			
			res.setHeader("Pragma", "No-cache");
			res.setHeader("Cache-Control", "no-cache");
			res.addHeader("Cache-Control", "no-store");
			res.setDateHeader("Expires", 1L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewPage;
	}

}
