package fpt.k9.foodquality.model;

import java.sql.Date;;

public class Product{
	//Attribute
	private String pid;
	private String pname;
	private String ptype;
	private Date pedate;
	private Date pmdate;
	private int pprice;
	private String pimage;
	private int pstatus;
	private String pproid;
	private String psid;
	private String ptid;
	
	//Constructor
	public Product() {
	}	
	
	public Product(String pid, String pname, String ptype, Date pedate,
			Date pmdate, int pprice, String pimage, int pstatus, String pproid,
			String psid, String ptid) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.ptype = ptype;
		this.pedate = pedate;
		this.pmdate = pmdate;
		this.pprice = pprice;
		this.pimage = pimage;
		this.pstatus = pstatus;
		this.pproid = pproid;
		this.psid = psid;
		this.ptid = ptid;
	}



	//Getter and Setter 
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}


	public String getPtype() {
		return ptype;
	}


	public void setPtype(String ptype) {
		this.ptype = ptype;
	}


	public Date getPedate() {
		return pedate;
	}

	public void setPedate(Date pedate) {
		this.pedate = pedate;
	}

	public int getPstatus() {
		return pstatus;
	}

	public void setPstatus(int pstatus) {
		this.pstatus = pstatus;
	}

	public String getPproid() {
		return pproid;
	}

	public void setPproid(String pproid) {
		this.pproid = pproid;
	}

	public String getPsid() {
		return psid;
	}

	public void setPsid(String psid) {
		this.psid = psid;
	}

	public String getPtid() {
		return ptid;
	}

	public void setPtid(String ptid) {
		this.ptid = ptid;
	}

	public Date getPmdate() {
		return pmdate;
	}


	public void setPmdate(Date pmdate) {
		this.pmdate = pmdate;
	}


	public int getPprice() {
		return pprice;
	}


	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public String getPimage() {
		return pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	
	//Other methods
	
}