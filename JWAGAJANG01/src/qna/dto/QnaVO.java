package qna.dto;

import java.sql.Timestamp;

public class QnaVO {
	private int qna_code;
	private String qna_label;
	private String qna_title;
	private Timestamp qna_regdate;
	private Timestamp qna_editdate;
	private String qna_content;
	private int qna_count;
	private String qna_reply;
	private String user_id;
	
	public QnaVO(int qna_code, String qna_label, String qna_title, String user_id, Timestamp qna_regdate, int qna_count) {
	this.qna_code = qna_code;
	this.qna_label = qna_label;
	this.qna_title = qna_title;
	this.user_id = user_id;
	this.qna_regdate = qna_regdate;
	this.qna_count = qna_count;
	
	}
	public QnaVO() {
		// TODO 자동 생성된 생성자 스텁
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getQna_code() {
		return qna_code;
	}
	public void setQna_code(int qna_code) {
		this.qna_code = qna_code;
	}
	public String getQna_label() {
		return qna_label;
	}
	public void setQna_label(String qna_label) {
		this.qna_label = qna_label;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public Timestamp getQna_regdate() {
		return qna_regdate;
	}
	public void setQna_regdate(Timestamp qna_regdate) {
		this.qna_regdate = qna_regdate;
	}
	public Timestamp getQna_editdate() {
		return qna_editdate;
	}
	public void setQna_editdate(Timestamp qna_editdate) {
		this.qna_editdate = qna_editdate;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public int getQna_count() {
		return qna_count;
	}
	public void setQna_count(int qna_count) {
		this.qna_count = qna_count;
	}
	public String getQna_reply() {
		return qna_reply;
	}
	public void setQna_reply(String qna_reply) {
		this.qna_reply = qna_reply;
	}
	@Override
	public String toString() {
		return "QnaVO [qna_code=" + qna_code + ", qna_label=" + qna_label + ", qna_title=" + qna_title
				+ ", qna_regdate=" + qna_regdate + ", qna_editdate=" + qna_editdate + ", qna_content=" + qna_content
				+ ", qna_count=" + qna_count + ", qna_reply=" + qna_reply + ", user_id=" + user_id + "]";
	}
	
	
}