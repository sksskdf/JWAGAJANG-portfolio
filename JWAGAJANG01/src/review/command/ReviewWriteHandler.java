package review.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import goods.dao.GoodsDAO;
import review.dao.ReviewDAO;
import review.dto.ReviewVO;

public class ReviewWriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int md_code = Integer.parseInt(req.getParameter("md_code"));
		String user_id = req.getParameter("user_id");
		int review_rate = Integer.parseInt(req.getParameter("review_rate"));
		String review_content = req.getParameter("review_content");
		
		
		ReviewVO rVo = new ReviewVO();
		rVo.setMd_code(md_code);
		rVo.setUser_id(user_id);
		rVo.setReview_rate(review_rate);
		rVo.setReview_content(review_content);
		
		
		
		ReviewDAO rDao = ReviewDAO.getInstance();
		int review_code = rDao.insertReview(rVo);
		
		GoodsDAO gDao = GoodsDAO.getInstance();
		gDao.updateReviewCount(md_code, 1);
		
		ReviewVO added = rDao.getReview(review_code);
		req.setAttribute("review", added);
		
		
		System.out.println(review_rate);
		return "goodsReview.jsp";
	}
}
