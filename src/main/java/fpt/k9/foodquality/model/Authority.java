package fpt.k9.foodquality.model;

public class Authority {
	private String aname;
	private String aaddress;
	private int asid;
	private int atid;
	private int aproid;
	
	//Constructor
	public Authority()
	{
		
	}
	public Authority(String aname, String aaddress, int asid, int atid, int aproid) {
		super();
		this.aname = aname;
		this.aaddress = aaddress;
		this.asid = asid;
		this.atid = atid;
		this.aproid = aproid;
	}
	
	//Getter and Setter
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAaddress() {
		return aaddress;
	}
	public void setAaddress(String aaddress) {
		this.aaddress = aaddress;
	}
	public int getAsid() {
		return asid;
	}
	public void setAsid(int asid) {
		this.asid = asid;
	}
	public int getAtid() {
		return atid;
	}
	public void setAtid(int atid) {
		this.atid = atid;
	}
	public int getAproid() {
		return aproid;
	}
	public void setAproid(int aproid) {
		this.aproid = aproid;
	}
	
	

}
