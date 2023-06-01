package main.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import product.dao.ProductDAO;
import product.dto.ProductVO;

public class MainHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")||
		    req.getMethod().equalsIgnoreCase("POST")) {
			return processForm(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		ProductDAO pDao = ProductDAO.getInstance();
		List<ProductVO> bestList=pDao.selectBestProduct();
		List<ProductVO> newList=pDao.selectNewProduct();
		req.setAttribute("bestList", bestList);
		req.setAttribute("newList", newList);
		return "main.jsp";
	}
	

}
