package product.command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.org.glassfish.gmbal.Description;

import common.command.CommandHandler;
import product.dao.ProductDAO;
import product.dto.ProductVO;
import product.service.ProductService;
import product.service.ProductServiceImpl;

public class ProductWriteHandler implements CommandHandler {
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return "productWrite.jsp";	// view page를 반환
		}
		else if(request.getMethod().equalsIgnoreCase("POST")) {
//			request.setCharacterEncoding("UTF-8");
			ServletContext context = request.getServletContext();
			String path = context.getRealPath("img");
			String encType = "UTF-8";
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			int sizeLimit = 20 * 1024 * 1024;
			
			MultipartRequest multi = new MultipartRequest(request, path, sizeLimit,	encType, 
					new DefaultFileRenamePolicy());
		
			String md_name = multi.getParameter("md_name");
			int md_price = Integer.parseInt(multi.getParameter("md_price"));
			int md_dc = Integer.parseInt(multi.getParameter("md_dc"));
			int md_stock = Integer.parseInt(multi.getParameter("md_stock"));
			String img_main = multi.getFilesystemName("img_main");
			String img_detail = multi.getFilesystemName("img_detail");
			String category_main = multi.getParameter("category_main");
			String category_sub = multi.getParameter("category_sub");
			String category_main_name = null;
			int category_main_ = Integer.parseInt(category_main);
			if(category_main_ == 100) {
				category_main_name = "채소,과일";
			}else if(category_main_ == 200) {
				category_main_name = "쌀,견과류";
			}else if(category_main_ == 300) {
				category_main_name = "수산,해산";
			}else if(category_main_ == 400) {
				category_main_name = "정육,계란";
			}
			
			ProductVO pVo = new ProductVO();
			pVo.setMd_name(md_name);
			pVo.setMd_price(md_price);
			pVo.setMd_dc(md_dc);
			pVo.setMd_stock(md_stock);
			pVo.setImg_main(img_main);
			pVo.setImg_detail(img_detail);
			pVo.setCategory_main(category_main);
			pVo.setCategory_sub(category_sub);
			pVo.setCategory_main_name(category_main_name);
			
			
			ProductDAO pDao = ProductDAO.getInstance();
			pDao.insertProduct(pVo);
			
			
			response.sendRedirect("productList.do?p=1&id="+id);
			return null;	// redirect를 할 경우 view page를 null로 반환
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
}