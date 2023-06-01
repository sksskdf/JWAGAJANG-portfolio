package qna.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import qna.dao.QnaDAO;
import qna.dto.QnaVO;

public class QnaViewHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String url = "/qnaView.jsp";
		int qna_code = Integer.parseInt(req.getParameter("qna_code"));
		QnaDAO qDao = QnaDAO.getInstance();
		qDao.updateReadCount(qna_code); // 게시글의 조회수를 증가
		QnaVO qVo = qDao.selectOneBoardByNum(qna_code);
		req.setAttribute("board", qVo);

		return url;
	}
}