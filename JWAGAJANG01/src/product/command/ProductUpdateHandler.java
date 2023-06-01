package product.command;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.command.CommandHandler;
import product.dao.ProductDAO;
import product.dto.ProductVO;

public class ProductUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			// GET 메소드가 동작
			String md_code = request.getParameter("mdcode");
			ProductDAO pDao = ProductDAO.getInstance();
			ProductVO pVo = pDao.selectProductByCode(Integer.parseInt(md_code));
			
			request.setAttribute("product", pVo);
			return "productUpdate.jsp";	// 제품수정 page
		}
		else if(request.getMethod().equalsIgnoreCase("POST")) {
			// POST 메소드가 동작
			// 기존 이미지 -> 새로운 이미지 변경 : 기존 이미지 파일을 삭제 -> 고려되어 있지 않음
			// 기존 이미지를 그대로 유지
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			request.setCharacterEncoding("UTF-8");
			ServletContext context = request.getServletContext();
			String path = context.getRealPath("img");
			String encType = "UTF-8";
			int sizeLimit = 20 * 1024 * 1024;
			// 새로운 이미지 파일은 자동으로 저장이 됨
			MultipartRequest multi = new MultipartRequest(request, path, sizeLimit,	encType, 
					new DefaultFileRenamePolicy());

			String md_code = multi.getParameter("md_code");
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
			
			if (img_main == null) {	// 새로운 이미지로 대체하지 않음
				img_main = multi.getParameter("nonmakeImg");	// 기존 이미지를 유지
			} else {	// 변경됨
				String oldUrl = multi.getParameter("nonmakeImg");	// 이전 파일의 이름
				// oldUrl 파일을 삭제
				File oldFile = new File(path + File.separator + oldUrl);
				if(oldFile.exists()) {
					oldFile.delete();	// 파일을 삭제
				}
			}
			
			ProductVO pVo = new ProductVO();
			pVo.setMd_code(Integer.parseInt(md_code));
			pVo.setMd_name(md_name);
			pVo.setMd_price(md_price);
			pVo.setMd_dc(md_dc);
			pVo.setImg_main(img_main);
			pVo.setImg_detail(img_detail);
			pVo.setCategory_main(category_main);
			pVo.setCategory_sub(category_sub);
			pVo.setCategory_main_name(category_main_name);
			
			ProductDAO pDao = ProductDAO.getInstance();
			pDao.updateProduct(pVo);
			response.sendRedirect("productList.do?p=1&id="+id); // 브라우저
			return null;
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
}