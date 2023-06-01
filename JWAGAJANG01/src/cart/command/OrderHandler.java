package cart.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.service.BuyService;
import common.command.CommandHandler;
import member.model.Order;

public class OrderHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET") || req.getMethod().equalsIgnoreCase("POST")) {
			return proBuy(req, res);
		}
		/*else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processPost(req, res);
		}*/
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	/*private String processGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		if(id == null) { //로그인 검사
			System.out.println("로그인 실패");
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter writer;
			writer = res.getWriter();
			writer.println("<script>alert('로그인 후 이용해주세요.');</script>"); 
			writer.println("<script>location.href=\"/login.do\";</script>");
			writer.close();
		}
		String md_code = req.getParameter("md_code");
		
		int md_code_ = Integer.parseInt(md_code);
		BuyService buyservice = BuyService.getInstance();
		String mdname = buyservice.get_mdname(md_code_);
		Order orderinfo = buyservice.get_orderInfo(id);
		
		req.setAttribute("md_name", mdname);
		req.setAttribute("orderinfo", orderinfo);
		return "order.jsp";
	}*/

	private String proBuy(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		String code = null;
		
		if(id == null) { //로그인 검사
			System.out.println("로그인 실패");
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter writer;
			writer = res.getWriter();
			writer.println("<script>alert('로그인 후 이용해주세요.');</script>"); 
			writer.println("<script>location.href=\"/login.do\";</script>");
			writer.close();
		}
		
		Enumeration<String> paramNames = req.getParameterNames();
		   while(paramNames.hasMoreElements()) {
		       String name = paramNames.nextElement().toString();
		       String strList = req.getParameter(name);
		       code = strList;
		   }
		String md_code[] = code.split(",");
		
		int md_code_[] = new int[md_code.length];
		ArrayList<String> nameList = new ArrayList<String>();
		
		for(int i=0; i<md_code.length; i++) {
			md_code_[i] = Integer.parseInt(md_code[i]);
		}
		BuyService buyservice = BuyService.getInstance();
		
		for(int i=0; i<md_code_.length; i++) {
			String mdname = buyservice.get_mdname(md_code_[i]);
			nameList.add(i, mdname);
		}
		Order orderinfo = buyservice.get_orderInfo(id);
		
		req.setAttribute("md_names", nameList);
		req.setAttribute("orderinfo", orderinfo);
		req.setAttribute("md_codes", md_code_);
		
		return "order.jsp";
	}

}
