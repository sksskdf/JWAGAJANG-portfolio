package cart.command;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.dto.CartVO;
import common.command.CommandHandler;

public class CartSelectDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("POST")) {
				return processForm(req, res);
			} else if (req.getMethod().equalsIgnoreCase("GET")){
				return "cart.jsp";
			} else {
				res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return null;
			}
		}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		String[] chkarr=null;
		ArrayList<CartVO> cartList = (ArrayList<CartVO>) session.getAttribute("cartList");
		
		Enumeration<String> paramNames = req.getParameterNames();
		   while(paramNames.hasMoreElements()) {
		       String name = paramNames.nextElement().toString();
		       String[] strList = req.getParameterValues(name);
		       chkarr = strList;
		   }

		for(Iterator<CartVO> itr = cartList.iterator(); itr.hasNext();) {
			CartVO value = itr.next();
			for (int i = 0; i < chkarr.length; i++) {
			if(value.getMd_code().equals(chkarr[i])) itr.remove();
			}
		}
		
		return "cart.jsp";
	}
		    
}


