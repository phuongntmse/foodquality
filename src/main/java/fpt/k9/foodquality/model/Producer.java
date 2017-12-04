package fpt.k9.foodquality.model;

public class Producer {
	private String proid;
	private String prouid;
	private String proinfo;
	private String proimage;
	private String protax_code;
	private String prophone;
	private String proadd;
	private String promail;
	private String proweb;
	private int prostatus;
	private String proqrimage;
	//Constructor
	public Producer()
	{
	}
	
	public Producer(String proid, String prouid, String proinfo,
			String proimage, String protax_code, String prophone,
			String proadd, String promail, String proweb, int prostatus) {
		super();
		this.proid = proid;
		this.prouid = prouid;
		this.proinfo = proinfo;
		this.proimage = proimage;
		this.protax_code = protax_code;
		this.prophone = prophone;
		this.proadd = proadd;
		this.promail = promail;
		this.proweb = proweb;
		this.prostatus = prostatus;
	}

	//Getter and setter
	public String getProid() {
		return proid;
	}
	public void setProid(String proid) {
		this.proid = proid;
	}
	public String getProinfo() {
		return proinfo;
	}
	public void setProinfo(String proinfo) {
		this.proinfo = proinfo;
	}
	public String getProuid() {
		return prouid;
	}
	public void setProuid(String prouid) {
		this.prouid = prouid;
	}

	public String getProimage() {
		return proimage;
	}

	public void setProimage(String proimage) {
		this.proimage = proimage;
	}

	public String getProtax_code() {
		return protax_code;
	}

	public void setProtax_code(String protax_code) {
		this.protax_code = protax_code;
	}

	public String getProphone() {
		return prophone;
	}

	public void setProphone(String prophone) {
		this.prophone = prophone;
	}

	public String getProadd() {
		return proadd;
	}

	public void setProadd(String proadd) {
		this.proadd = proadd;
	}

	public String getPromail() {
		return promail;
	}

	public void setPromail(String promail) {
		this.promail = promail;
	}

	public String getProweb() {
		return proweb;
	}

	public void setProweb(String proweb) {
		this.proweb = proweb;
	}

	public int getProstatus() {
		return prostatus;
	}

	public void setProstatus(int prostatus) {
		this.prostatus = prostatus;
	}

	public String getProqrimage() {
		return proqrimage;
	}

	public void setProqrimage(String proqrimage) {
		this.proqrimage = proqrimage;
	}
	
}
