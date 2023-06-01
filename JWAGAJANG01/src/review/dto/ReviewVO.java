package review.dto;

import java.sql.Timestamp;

public class ReviewVO {
	private int review_code;
	private int md_code;
	private String user_id;
	private String user_name;
	private int review_rate;
	private String review_content;
	private Timestamp review_regdate;
	private Timestamp review_editdate;
	
	public int getReview_code() {
		return review_code;
	}
	public void setReview_code(int review_code) {
		this.review_code = review_code;
	}
	public int getMd_code() {
		return md_code;
	}
	public void setMd_code(int md_code) {
		this.md_code = md_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getReview_rate() {
		return review_rate;
	}
	public void setReview_rate(int review_rate) {
		this.review_rate = review_rate;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public Timestamp getReview_regdate() {
		return review_regdate;
	}
	public void setReview_regdate(Timestamp review_regdate) {
		this.review_regdate = review_regdate;
	}
	public Timestamp getReview_editdate() {
		return review_editdate;
	}
	public void setReview_editdate(Timestamp review_editdate) {
		this.review_editdate = review_editdate;
	}
}
