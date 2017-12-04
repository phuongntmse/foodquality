package fpt.k9.foodquality.model;

import java.sql.Date;;

public class RegisterProduct{
	//Attribute
	private String rpid;
	private String rpname;
	private String rptype;
	private Date rpedate;
	private Date rpmdate;
	private int rpprice;
	private String rpimage;
	private String rpproid;
	private String rpsid;
	private String rptid;
	
	//Constructor
	public RegisterProduct() {
	}

	public RegisterProduct(String rpid, String rpname, String rptype,
			Date rpedate, Date rpmdate, int rpprice, String rpimage,
			String rpproid, String rpsid, String rptid) {
		super();
		this.rpid = rpid;
		this.rpname = rpname;
		this.rptype = rptype;
		this.rpedate = rpedate;
		this.rpmdate = rpmdate;
		this.rpprice = rpprice;
		this.rpimage = rpimage;
		this.rpproid = rpproid;
		this.rpsid = rpsid;
		this.rptid = rptid;
	}

	public String getRpid() {
		return rpid;
	}

	public void setRpid(String rpid) {
		this.rpid = rpid;
	}

	public String getRpname() {
		return rpname;
	}

	public void setRpname(String rpname) {
		this.rpname = rpname;
	}

	public String getRptype() {
		return rptype;
	}

	public void setRptype(String rptype) {
		this.rptype = rptype;
	}

	public Date getRpedate() {
		return rpedate;
	}

	public void setRpedate(Date rpedate) {
		this.rpedate = rpedate;
	}

	public Date getRpmdate() {
		return rpmdate;
	}

	public void setRpmdate(Date rpmdate) {
		this.rpmdate = rpmdate;
	}

	public int getRpprice() {
		return rpprice;
	}

	public void setRpprice(int rpprice) {
		this.rpprice = rpprice;
	}

	public String getRpimage() {
		return rpimage;
	}

	public void setRpimage(String rpimage) {
		this.rpimage = rpimage;
	}

	public String getRpproid() {
		return rpproid;
	}

	public void setRpproid(String rpproid) {
		this.rpproid = rpproid;
	}

	public String getRpsid() {
		return rpsid;
	}

	public void setRpsid(String rpsid) {
		this.rpsid = rpsid;
	}

	public String getRptid() {
		return rptid;
	}

	public void setRptid(String rptid) {
		this.rptid = rptid;
	}	
	
	
}