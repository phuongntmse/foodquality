package fpt.k9.foodquality.model;

public class Seller {
	private String sid;
	private String suid;
	private String sinfor;
	private String simage;
	private String stax_code;
	private String sphone;
	private String sadd;
	private String smail;
	private String sweb;
	private int sstatus;
	private String sqrimage;
	
	//Constructor
	public Seller()
	{
	}
	
	public Seller(String sid, String suid, String sinfor, String simage,
			String stax_code, String sphone, String sadd, String smail,
			String sweb, int sstatus) {
		super();
		this.sid = sid;
		this.suid = suid;
		this.sinfor = sinfor;
		this.simage = simage;
		this.stax_code = stax_code;
		this.sphone = sphone;
		this.sadd = sadd;
		this.smail = smail;
		this.sweb = sweb;
		this.sstatus = sstatus;
	}

	//Getter and Setter
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSinfor() {
		return sinfor;
	}

	public void setSinfor(String sinfor) {
		this.sinfor = sinfor;
	}

	public String getSuid() {
		return suid;
	}

	public void setSuid(String suid) {
		this.suid = suid;
	}

	public String getSimage() {
		return simage;
	}

	public void setSimage(String simage) {
		this.simage = simage;
	}

	public String getStax_code() {
		return stax_code;
	}

	public void setStax_code(String stax_code) {
		this.stax_code = stax_code;
	}

	public String getSphone() {
		return sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public String getSadd() {
		return sadd;
	}

	public void setSadd(String sadd) {
		this.sadd = sadd;
	}

	public String getSmail() {
		return smail;
	}

	public void setSmail(String smail) {
		this.smail = smail;
	}

	public String getSweb() {
		return sweb;
	}

	public void setSweb(String sweb) {
		this.sweb = sweb;
	}

	public int getSstatus() {
		return sstatus;
	}

	public void setSstatus(int sstatus) {
		this.sstatus = sstatus;
	}

	public String getSqrimage() {
		return sqrimage;
	}

	public void setSqrimage(String sqrimage) {
		this.sqrimage = sqrimage;
	}
	
}
