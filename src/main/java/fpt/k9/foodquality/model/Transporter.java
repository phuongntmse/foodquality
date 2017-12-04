package fpt.k9.foodquality.model;

public class Transporter{
	private String tid;
	private String tuid;
	private String tinfor;
	private String timage;
	private String ttax_code;
	private String tphone;
	private String tadd;
	private String tmail;
	private String tweb;
	private int tstatus;
	private String tqrimage;
	
	//Constructor
	public Transporter()
	{
	}
	
	public Transporter(String tid, String tuid, String tinfor, String timage,
			String ttax_code, String tphone, String tadd, String tmail,
			String tweb, int tstatus) {
		super();
		this.tid = tid;
		this.tuid = tuid;
		this.tinfor = tinfor;
		this.timage = timage;
		this.ttax_code = ttax_code;
		this.tphone = tphone;
		this.tadd = tadd;
		this.tmail = tmail;
		this.tweb = tweb;
		this.tstatus = tstatus;
	}

	//Getter and Setter
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTuid() {
		return tuid;
	}

	public void setTuid(String tuid) {
		this.tuid = tuid;
	}

	public String getTinfor() {
		return tinfor;
	}

	public void setTinfor(String tinfor) {
		this.tinfor = tinfor;
	}

	public String getTimage() {
		return timage;
	}

	public void setTimage(String timage) {
		this.timage = timage;
	}
	public String getTtax_code() {
		return ttax_code;
	}
	public void setTtax_code(String ttax_code) {
		this.ttax_code = ttax_code;
	}
	public String getTphone() {
		return tphone;
	}
	public void setTphone(String tphone) {
		this.tphone = tphone;
	}
	public String getTadd() {
		return tadd;
	}
	public void setTadd(String tadd) {
		this.tadd = tadd;
	}
	public String getTmail() {
		return tmail;
	}
	public void setTmail(String tmail) {
		this.tmail = tmail;
	}
	public String getTweb() {
		return tweb;
	}
	public void setTweb(String tweb) {
		this.tweb = tweb;
	}
	public int getTstatus() {
		return tstatus;
	}
	public void setTstatus(int tstatus) {
		this.tstatus = tstatus;
	}

	public String getTqrimage() {
		return tqrimage;
	}

	public void setTqrimage(String tqrimage) {
		this.tqrimage = tqrimage;
	}	
	
	
}