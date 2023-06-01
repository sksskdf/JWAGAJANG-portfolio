package notice.dto;

import java.sql.Timestamp;

public class NoticeVO {
	private int notice_code;
	private String notice_label;
	private String notice_title;
	private Timestamp notice_regdate;
	private Timestamp notice_editdate;
	private String notice_content;
	private int notice_count;
	
	public NoticeVO() {}
	
	public NoticeVO(int notice_code, String notice_label, String notice_title, Timestamp notice_regdate, int notice_count) {
		super();
		this.notice_code = notice_code;
		this.notice_label = notice_label;
		this.notice_title = notice_title;
		this.notice_regdate = notice_regdate;
		this.notice_count = notice_count;
	}
	public NoticeVO(int notice_code, String notice_label, String notice_title, Timestamp notice_regdate,
			Timestamp notice_editdate, String notice_content, int notice_count) {
		super();
		this.notice_code = notice_code;
		this.notice_label = notice_label;
		this.notice_title = notice_title;
		this.notice_regdate = notice_regdate;
		this.notice_editdate = notice_editdate;
		this.notice_content = notice_content;
		this.notice_count = notice_count;
	}
	public int getNotice_code() {
		return notice_code;
	}
	public void setNotice_code(int notice_code) {
		this.notice_code = notice_code;
	}
	public String getNotice_label() {
		return notice_label;
	}
	public void setNotice_label(String notice_label) {
		this.notice_label = notice_label;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public Timestamp getNotice_regdate() {
		return notice_regdate;
	}
	public void setNotice_regdate(Timestamp notice_regdate) {
		this.notice_regdate = notice_regdate;
	}
	public Timestamp getNotice_editdate() {
		return notice_editdate;
	}
	public void setNotice_editdate(Timestamp notice_editdate) {
		this.notice_editdate = notice_editdate;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public int getNotice_count() {
		return notice_count;
	}
	public void setNotice_count(int notice_count) {
		this.notice_count = notice_count;
	}

}