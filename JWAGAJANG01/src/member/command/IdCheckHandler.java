package member.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import member.service.MemberService;
import member.service.MemberServiceImpl;



public class IdCheckHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		if(req.getMethod().equalsIgnoreCase("POST")) {
			System.out.println("IdCheckHandler 실행");
			return processSubmit(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		MemberService service = MemberServiceImpl.getInstance();
		String check = service.idCheck(id);
		req.setAttribute("result", check);
		System.out.println("process submit 실행");
		System.out.println(service.idCheck(id));
		return "/member/idCheck.jsp";
	}
}
