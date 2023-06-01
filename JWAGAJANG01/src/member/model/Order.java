package member.model;

import java.sql.Date;

public class Order {
	private String mdpic = null;
	private String mdname = null;
	private Date mdorderdate = null;
	private String mdprice = null;
	private String mdreview = null;
	private Integer mdcode = null;
	private String order_name = null;
	private String mobile = null;
	private String address = null;
	private String address2 = null;
	private String orderrequest = null;
	private String user_id = null;
	
	
	public Order() {
	}
	
	
	
	public Order(String mdpic, String mdname, Date mdorderdate, String mdprice) {
		super();
		this.mdpic = mdpic;
		this.mdname = mdname;
		this.mdorderdate = mdorderdate;
		this.mdprice = mdprice;
	}
	
	
	public Order(String mdpic, String mdname, Date mdorderdate, String mdprice,int mdcode) {
		super();
		this.mdpic = mdpic;
		this.mdname = mdname;
		this.mdorderdate = mdorderdate;
		this.mdprice = mdprice;
		this.mdcode = mdcode;
	}

	
	
	public String getOrder_name() {
		return order_name;
	}



	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getAddress2() {
		return address2;
	}



	public void setAddress2(String address2) {
		this.address2 = address2;
	}



	public String getOrderrequest() {
		return orderrequest;
	}



	public void setOrderrequest(String orderrequest) {
		this.orderrequest = orderrequest;
	}



	public String getMdpic() {
		return mdpic;
	}



	public void setMdpic(String mdpic) {
		this.mdpic = mdpic;
	}



	public String getMdname() {
		return mdname;
	}



	public void setMdname(String mdname) {
		this.mdname = mdname;
	}



	public Date getMdorderdate() {
		return mdorderdate;
	}



	public void setMdorderdate(Date mdorderdate) {
		this.mdorderdate = mdorderdate;
	}



	public String getMdprice() {
		return mdprice;
	}



	public void setMdprice(String mdprice) {
		this.mdprice = mdprice;
	}



	public String getMdreview() {
		return mdreview;
	}



	public void setMdreview(String mdreview) {
		this.mdreview = mdreview;
	}

	public Integer getMdcode() {
		return mdcode;
	}

	public void setMdcode(Integer mdcode) {
		this.mdcode = mdcode;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Order [mdpic=" + mdpic + ", mdname=" + mdname + ", mdorderdate=" + mdorderdate + ", mdprice=" + mdprice
				+ ", mdreview=" + mdreview + "]";
	}



	
	
	
}
