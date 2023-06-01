package qna.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.command.CommandHandler;
import qna.dao.QnaDAO;
import qna.dto.QnaVO;

public class QnaWriteHandler implements CommandHandler {
	@Override
	
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return "/qnaWrite.jsp"; // form에 들어가는 jsp파일이 들어가야함
		}else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		QnaVO qVo = new QnaVO();
		
		qVo.setQna_title(req.getParameter("qna_title"));
		qVo.setQna_content(req.getParameter("qna_content"));
		HttpSession session = req.getSession();
		String sessionid = (String)session.getAttribute("id");
		QnaDAO qDao = QnaDAO.getInstance();
		qDao.insertBoard(qVo,sessionid);
		res.sendRedirect("qnaList.do");
		return null;
	}
} 
