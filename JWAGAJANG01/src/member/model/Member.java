package member.model;

import java.sql.Timestamp;

public class Member {
	private String id;
	private String pwd;
	private String mobile;
	private String email;
	private String address;
	private String adddetail;
	private int grade;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	
	public Member() { }
	
	public Member(String id,String pwd,String mobile,String email) {
		this.id = id;
		this.pwd = pwd;
		this.mobile = mobile;
		this.email = email;
	}
	
	public Member(String id, String pwd, String email, String mobile, int grade) {
		this.id = id;
		this.pwd = pwd;
		this.mobile = mobile;
		this.email = email;
		this.grade = grade;
	}

	public Member(String id, String pwd, String mobile, String email, String address, String adddetail
			) {
		this.id = id;
		this.pwd = pwd;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.adddetail = adddetail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAdddetail() {
		return adddetail;
	}

	public void setAdddetail(String adddetail) {
		this.adddetail = adddetail;
	}


	
	
}
