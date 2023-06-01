package member.model;

public class Fav {
	private int mdcode;
	private String userid;
	
	public Fav() {
	}

	public Fav(int mdcode, String userid) {
		super();
		this.mdcode = mdcode;
		this.userid = userid;
	}

	public int getMdcode() {
		return mdcode;
	}

	public void setMdcode(int mdcode) {
		this.mdcode = mdcode;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "Fav [mdcode=" + mdcode + ", userid=" + userid + "]";
	}
	
	
	
	
}
