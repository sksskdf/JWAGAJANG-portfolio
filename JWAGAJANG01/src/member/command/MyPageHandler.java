package member.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import member.model.Member;
import member.service.MemberService;
import member.service.MemberServiceImpl;

public class MyPageHandler implements CommandHandler{
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}
		else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		}
		
		return null;
	}

	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return "/member/mypage.jsp";
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		String mobile = req.getParameter("mobile");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String addDetail = req.getParameter("addDetail");
		Member member = new Member(id,pwd,mobile,email,address,addDetail);
		MemberService memberservice = MemberServiceImpl.getInstance();
		memberservice.update(member);
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter writer;
		writer = res.getWriter();
		writer.println("<script>alert('회원정보가 수정되었습니다!');</script>"); 
		writer.println("<script>location.href=\"index.do?id="+id+"\";</script>");
		writer.close();
		return null;
	}

}
