package member.command;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.command.CommandHandler;
import member.model.Order;
import member.service.FavService;
import member.service.OrderService;

public class FavListHandler implements CommandHandler {
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		String page = req.getParameter("p");
		String del = req.getParameter("del");
		System.out.println(del);
		
		FavService fs = FavService.getInstance();
		List<Order> favlist = fs.getFavList(id,page);
		int count = fs.getCount(id);
		
		if(del != null) {
			int mdcode = Integer.parseInt(del);
			fs.del(id, mdcode);
			PrintWriter writer;
			res.setContentType("text/html; charset=UTF-8");
			writer = res.getWriter();
			writer.println("<script>alert('해당 상품이 찜목록에서 삭제되었습니다.');</script>"); 
			writer.println("<script>location.href=\"/favlist.do?p=1&id="+id+"\";</script>");
			writer.close();
		}
		
		
		req.setAttribute("favlist", favlist);
		req.setAttribute("count", count);
		
		return "member/favlist.jsp";
	}
	
}
	
