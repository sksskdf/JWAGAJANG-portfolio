package cart.dto;

public class CartVO {
	private String md_code;
	private String md_name;
	private String img_main;
	private int md_price;
	private int md_dc;
	private int md_count;
	
	public CartVO () {}
	
	public CartVO(String md_code, String md_name, String img_main, int md_price, int md_dc, int md_count) {
		super();
		this.md_code = md_code;
		this.md_name = md_name;
		this.img_main = img_main;
		this.md_price = md_price;
		this.md_dc = md_dc;
		this.md_count = md_count;
	}

	public String getMd_code() {
		return md_code;
	}

	public void setMd_code(String md_code) {
		this.md_code = md_code;
	}

	public String getMd_name() {
		return md_name;
	}

	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}

	public String getImg_main() {
		return img_main;
	}

	public void setImg_main(String img_main) {
		this.img_main = img_main;
	}

	public int getMd_price() {
		return md_price;
	}

	public void setMd_price(int md_price) {
		this.md_price = md_price;
	}

	public int getMd_dc() {
		return md_dc;
	}

	public void setMd_dc(int md_dc) {
		this.md_dc = md_dc;
	}

	public int getMd_count() {
		return md_count;
	}

	public void setMd_count(int md_count) {
		this.md_count = md_count;
	}

	@Override
	public String toString() {
		return "CartVO [md_code=" + md_code + ", md_name=" + md_name + ", img_main=" + img_main + ", md_price="
				+ md_price + ", md_dc=" + md_dc + ", md_count=" + md_count + "]";
	}
	
	
}