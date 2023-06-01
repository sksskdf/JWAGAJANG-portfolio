package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import notice.dao.NoticeDAO;
import notice.dto.NoticeVO;

public class NoticeViewHandler implements CommandHandler {

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
		String url = "/noticeView.jsp";
		int notice_code = Integer.parseInt(req.getParameter("notice_code"));
		NoticeDAO bDao = NoticeDAO.getInstance();
		bDao.updateReadCount(notice_code); // 게시글의 조회수를 증가
		NoticeVO bVo = bDao.selectOneBoardByNum(notice_code);
		req.setAttribute("board", bVo);
		return url;
	}
}