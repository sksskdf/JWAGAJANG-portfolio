package notice.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import notice.dao.NoticeDAO;
import notice.dto.NoticeVO;

public class NoticeWriteHandler implements CommandHandler {
	@Override
	
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return "/noticeWrite.jsp"; // form에 들어가는 jsp파일이 들어가야함
		}else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		NoticeVO bVo = new NoticeVO();
		
		String notice_label = req.getParameter("notice_label");
		if(notice_label.equals("공지")) {
			bVo.setNotice_label("공지");
		}
		else {
			bVo.setNotice_label("이벤트");
		}
		bVo.setNotice_title(req.getParameter("notice_title"));
		bVo.setNotice_content(req.getParameter("notice_content"));
		NoticeDAO bDao = NoticeDAO.getInstance();
		bDao.insertBoard(bVo);
		res.sendRedirect("noticeList.do");
		return null;
	}
} 
