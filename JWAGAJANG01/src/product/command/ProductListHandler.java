package product.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.command.CommandHandler;
import product.dao.ProductDAO;
import product.dto.Paging;
import product.dto.ProductListModel;
import product.dto.ProductVO;


public class ProductListHandler implements CommandHandler {
	private static final String FORM_VIEW = "/productList.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}else {

			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	

	/*public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getMethod().equalsIgnoreCase("GET") || 
				request.getMethod().equalsIgnoreCase("POST")) {
			ProductDAO pDao = ProductDAO.getInstance();
			List<ProductVO> productList = pDao.selectAllProducts();
			request.setAttribute("productList", productList);
			return "productList.jsp";	// 제품목록 페이지를 반환
		}
		else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}*/

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws SQLException, NamingException {
		String pageNumberString = req.getParameter("p"); // 브라우저에서 목록을 보면 p=null; 페이징 링크를 누르면 p=n;
		String searchkeyword = req.getParameter("searchkeyword");
		String del = req.getParameter("del");
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		String order = req.getParameter("order");
		
		int pageNumber = 1;
		if (pageNumberString != null && pageNumberString.length() > 0) { // p값이 들어왔는지 안들어왔는지
			pageNumber = Integer.parseInt(pageNumberString); // 들어왔으면  String 타입의 변수를 int 타입의 변수로  바꿔서 넣는다.
		}
		Paging paging = new Paging(10, 10); // 나타낼 목록, 몇 페이지를 보여줄건지
		paging.setCurrentPageNo(pageNumber); // 현재 페이지 설정

		ProductDAO bDao = ProductDAO.getInstance();
		int totalBoardCount = bDao.selectCount(order); // 전체 게시글의 수를 얻는다 
		List<ProductVO> productList = null;
		if (totalBoardCount == 0) {
			paging.setStartPageNo(1);
			productList = new ArrayList<ProductVO>();
		}
		paging.setNumberOfRecords(totalBoardCount);	// 전체 게시글의 수를 얻어와서
		paging.makePaging();
		int firstRow = (pageNumber - 1) * paging.getRecordsPerPage() + 1; // 계산
		int endRow = firstRow + paging.getRecordsPerPage() - 1;

		if (endRow > totalBoardCount) {
			endRow = totalBoardCount;
		}
		//삭제
		if(del != null) {
			try {
				int delint = Integer.parseInt(del);
				bDao.delete(delint);
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter writer;
				writer = res.getWriter();
				writer.println("<script>alert('해당 상품이 삭제되었습니다.');</script>"); 
				writer.println("<script>location.href=\"productList.do?p=1&id='"+id+"'\";</script>"); 
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		productList = bDao.sort(firstRow, endRow , order); // 첫번째 열, 마지막 열
		ProductListModel listModel = new ProductListModel();
		listModel.setNoticeList(productList); // 가져온 목록
		listModel.setPaging(paging);		 // 페이징 정보
		req.setAttribute("productList", productList);
		req.setAttribute("listModel", listModel); // jsp로 전달
		
		
		res.setHeader("Pragma", "No-cache"); // 캐시 삭제하도록 설정 : 게시글을 추가했는데 캐시에 있는걸 보여주면 추가한게 안나오기떄문에
		res.setHeader("Cache-Control", "no-cache");
		res.addHeader("Cache-Control", "no-store");
		res.setDateHeader("Expires", 1L);
		return "/productListFilter.jsp";
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws SQLException, NamingException {
		String pageNumberString = req.getParameter("p"); // 브라우저에서 목록을 보면 p=null; 페이징 링크를 누르면 p=n;
		String del = req.getParameter("del");
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");

		int pageNumber = 1;
		if (pageNumberString != null && pageNumberString.length() > 0) { // p값이 들어왔는지 안들어왔는지
			pageNumber = Integer.parseInt(pageNumberString); // 들어왔으면  String 타입의 변수를 int 타입의 변수로  바꿔서 넣는다.
		}
		Paging paging = new Paging(10, 10); // 나타낼 목록, 몇 페이지를 보여줄건지
		paging.setCurrentPageNo(pageNumber); // 현재 페이지 설정

		ProductDAO bDao = ProductDAO.getInstance();
		int totalBoardCount = bDao.selectCount(); // 전체 게시글의 수를 얻는다 
		List<ProductVO> productList = null;
		if (totalBoardCount == 0) {
			paging.setStartPageNo(1);
			productList = new ArrayList<ProductVO>();
		}
		paging.setNumberOfRecords(totalBoardCount);	// 전체 게시글의 수를 얻어와서
		paging.makePaging();
		int firstRow = (pageNumber - 1) * paging.getRecordsPerPage() + 1; // 계산
		int endRow = firstRow + paging.getRecordsPerPage() - 1;

		if (endRow > totalBoardCount) {
			endRow = totalBoardCount;
		}
		//삭제
		if(del != null) {
			try {
				int delint = Integer.parseInt(del);
				bDao.delete(delint);
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter writer;
				writer = res.getWriter();
				writer.println("<script>alert('해당 상품이 삭제되었습니다.');</script>"); 
				writer.println("<script>location.href=\"productList.do?p=1&id='"+id+"'\";</script>"); 
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		productList = bDao.select(firstRow, endRow); // 첫번째 열, 마지막 열
		ProductListModel listModel = new ProductListModel();
		listModel.setNoticeList(productList); // 가져온 목록
		listModel.setPaging(paging);		 // 페이징 정보
		req.setAttribute("productList", productList);
		req.setAttribute("listModel", listModel); // jsp로 전달
		
				
		res.setHeader("Pragma", "No-cache"); // 캐시 삭제하도록 설정 : 게시글을 추가했는데 캐시에 있는걸 보여주면 추가한게 안나오기떄문에
		res.setHeader("Cache-Control", "no-cache");
		res.addHeader("Cache-Control", "no-store");
		res.setDateHeader("Expires", 1L);
		return FORM_VIEW;
	}
}
