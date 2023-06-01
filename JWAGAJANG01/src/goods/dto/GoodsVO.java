package goods.dto;

import java.sql.Timestamp;

public class GoodsVO {
	private Integer md_code;
	private String md_name;
	private Integer md_price;
	private Integer md_dc;
	private Integer md_stock;
	private String img_main;
	private String img_detail;
	private String category_main;
	private String category_sub;
	private Integer md_ordercnt;
	
	private Integer review_code;
	private String user_id;
	private String user_name;
	private Integer review_rate;
	private String review_content; 
	private Timestamp review_regdate;
	private String category_main_name;
	
	
	public GoodsVO(){
		
	}
	public GoodsVO(Integer md_code, String md_name, Integer md_price, Integer md_dc, String img_main,
			String category_main, String category_sub, String category_main_name) {
		super();
		this.md_code = md_code;
		this.md_name = md_name;
		this.md_price = md_price;
		this.md_dc = md_dc;
		this.img_main = img_main;
		this.category_main = category_main;
		this.category_sub = category_sub;
		this.category_main_name = category_main_name;
	}
	
	public String getCategory_main_name() {
		return category_main_name;
	}
	public void setCategory_main_name(String category_main_name) {
		this.category_main_name = category_main_name;
	}
	public Integer getMd_code() {
		return md_code;
	}
	public void setMd_code(Integer md_code) {
		this.md_code = md_code;
	}
	public String getMd_name() {
		return md_name;
	}
	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}
	public Integer getMd_price() {
		return md_price;
	}
	public void setMd_price(Integer md_price) {
		this.md_price = md_price;
	}
	public Integer getMd_dc() {
		return md_dc;
	}
	public void setMd_dc(Integer md_dc) {
		this.md_dc = md_dc;
	}
	public Integer getMd_stock() {
		return md_stock;
	}
	public void setMd_stock(Integer md_stock) {
		this.md_stock = md_stock;
	}
	public Integer getMd_ordercnt() {
		return md_ordercnt;
	}
	public void setMd_ordercnt(Integer md_ordercnt) {
		this.md_ordercnt = md_ordercnt;
	}
	public String getImg_main() {
		return img_main;
	}
	public void setImg_main(String img_main) {
		this.img_main = img_main;
	}
	public String getImg_detail() {
		return img_detail;
	}
	public void setImg_detail(String img_detail) {
		this.img_detail = img_detail;
	}
	public String getCategory_main() {
		return category_main;
	}
	public void setCategory_main(String category_main) {
		this.category_main = category_main;
	}
	public String getCategory_sub() {
		return category_sub;
	}
	public void setCategory_sub(String category_sub) {
		this.category_sub = category_sub;
	}
	public Integer getReview_code() {
		return review_code;
	}
	public void setReview_code(Integer review_code) {
		this.review_code = review_code;
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
	public Integer getReview_rate() {
		return review_rate;
	}
	public void setReview_rate(Integer review_rate) {
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
	@Override
	public String toString() {
		return "GoodsVO [md_code=" + md_code + ", md_name=" + md_name + ", md_price=" + md_price + ", md_dc=" + md_dc
				+ ", img_main=" + img_main + ", img_detail=" + img_detail
				+ ", category_main=" + category_main + ", category_sub=" + category_sub + ", review_code=" + review_code
				+ ", user_id=" + user_id + ", review_rate=" + review_rate + ", review_content=" + review_content
				+ ", review_regdate=" + review_regdate + ", category_main_name=" + category_main_name + "]";
	}
	
	
}
