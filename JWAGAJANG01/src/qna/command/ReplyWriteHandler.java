package qna.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import qna.dao.QnaDAO;
import qna.dto.QnaVO;

public class ReplyWriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String url = "/qnaView.do?qna_code=\"+qna_code+\"&p=\"+p";
		int qna_code = Integer.parseInt(req.getParameter("qna_code"));
		QnaDAO qDao = QnaDAO.getInstance();
		qDao.updateReadCount(qna_code); // 게시글의 조회수를 증가
		QnaVO qVo = qDao.selectOneBoardByNum(qna_code);
		req.setAttribute("board", qVo);
		return url;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		QnaVO qVo = new QnaVO();
		int p = Integer.parseInt(req.getParameter("p"));
		int qna_code = Integer.parseInt(req.getParameter("qna_code"));
		String type = req.getParameter("type");
		if(type.equals("register")) {
			qVo.setQna_label("답변완료");
		} else {
			qVo.setQna_label("미완료");
		}
		qVo.setQna_code(qna_code);
		qVo.setQna_reply(req.getParameter("qna_reply"));
		QnaDAO qDao = QnaDAO.getInstance();
		qDao.updateReply(qVo);
		res.sendRedirect("qnaView.do?qna_code="+qna_code+"&p="+p);
		return null;
	}
}