package fpt.k9.foodquality.model;

public class History {
	private String huid;
	private String hcontent;
	private int hcount;
	
	public History() {
		this.huid = "";
		this.hcontent = "";
		this.hcount = 0;
	}
	
	public History(String huid, String hcontent, int hcount) {
		super();
		this.huid = huid;
		this.hcontent = hcontent;
		this.hcount = hcount;
	}

	public String getHuid() {
		return huid;
	}
	public void setHuid(String huid) {
		this.huid = huid;
	}
	public String getHcontent() {
		return hcontent;
	}
	public void setHcontent(String hcontent) {
		this.hcontent = hcontent;
	}

	public int getHcount() {
		return hcount;
	}

	public void setHcount(int hcount) {
		this.hcount = hcount;
	}
}
