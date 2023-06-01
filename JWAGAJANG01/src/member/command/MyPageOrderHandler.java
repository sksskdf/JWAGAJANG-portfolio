package member.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.command.CommandHandler;
import member.model.Member;
import member.model.Order;
import member.service.MemberService;
import member.service.MemberServiceImpl;
import member.service.OrderService;
import util.DBManager;

public class MyPageOrderHandler implements CommandHandler{
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		String page = req.getParameter("p");
		System.out.println(session.getAttribute("id")); //session에 저장된 id값 확인 OK
		
		
		OrderService os = OrderService.getInstance();
		List<Order> orderlist = os.getOrderList(id,page);
		int count = os.getCount(id);
		
		req.setAttribute("orderlist", orderlist);
		req.setAttribute("count", count);
		
		
	
		return "/member/mypageorder.jsp";
	}

}
