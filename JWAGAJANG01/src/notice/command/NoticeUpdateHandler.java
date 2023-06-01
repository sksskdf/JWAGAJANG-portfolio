package notice.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import notice.dao.NoticeDAO;
import notice.dto.NoticeVO;

public class NoticeUpdateHandler implements CommandHandler {

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
		String url = "/noticeUpdate.jsp";
		int notice_code = Integer.parseInt(req.getParameter("notice_code"));
		NoticeDAO bDao = NoticeDAO.getInstance();
		bDao.updateReadCount(notice_code); // 게시글의 조회수를 증가
		NoticeVO bVo = bDao.selectOneBoardByNum(notice_code);
		req.setAttribute("board", bVo);
		return url;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		NoticeVO bVo = new NoticeVO();
		String notice_label = req.getParameter("notice_label");
		int notice_code = Integer.parseInt(req.getParameter("notice_code"));
		bVo.setNotice_code(notice_code);
		if(notice_label.equals("공지")) {
			bVo.setNotice_label("공지");
		}
		else {
			bVo.setNotice_label("이벤트");
		}
		bVo.setNotice_title(req.getParameter("notice_title"));
		bVo.setNotice_content(req.getParameter("notice_content"));
		NoticeDAO bDao = NoticeDAO.getInstance();
		bDao.updateBoard(bVo);
		res.sendRedirect("noticeView.do?notice_code="+notice_code);
		return null;
	}
}