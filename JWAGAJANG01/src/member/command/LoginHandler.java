package member.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import common.command.CommandHandler;
import member.model.Member;
import member.service.LoginService;

public class LoginHandler implements CommandHandler {
	//화면에 출력할 jsp파일의 경로를 String으로 return
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//eqaulsIgnoreCase는 대소문자를 구별하지 않는 문자열비교 메서드.
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		}
		else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		}
		return null;
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return "/member/login.jsp";
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException, NamingException {
		HttpSession session = req.getSession();
		String viewPage = null;
		String id = req.getParameter("id");
		String password = req.getParameter("pwd");
		//싱글톤패턴의 객체를 사용하기 위해서 getInstance메서드를 사용.
		LoginService loginService = LoginService.getInstance();
		Member member = loginService.login(id, password);
		

		if(member != null) {
			session.setAttribute("member", member);
			session.setAttribute("id", id);
			session.setAttribute("grade", 1);
			res.sendRedirect("/index.do");
			
			System.out.println("로그인 성공");
		}//로그인실패시
		else {
			viewPage = "/member/login.jsp";
			System.out.println("로그인 실패");
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter writer;
			writer = res.getWriter();
			writer.println("<script>alert('아이디 혹은 비밀번호를 확인해주세요');</script>"); 
			writer.println("<script>location.href=\"/login.do\";</script>");
			writer.close();
		}
		
		return viewPage;
	}
}
