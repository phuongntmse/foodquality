package fpt.k9.foodquality.model;

import java.sql.Date;

public class Report {
	private String rid;
	private String rcontent;
	private String ruid;
	private String rrefid;
	private int rtype;
	private Date rdate;
	
	//Constructor
	public Report()
	{
	}
	
	public Report(String rid, String rcontent, String ruid, String rrefid,
			int rtype, Date rdate) {
		super();
		this.rid = rid;
		this.rcontent = rcontent;
		this.ruid = ruid;
		this.rrefid = rrefid;
		this.rtype = rtype;
		this.rdate = rdate;
	}
	
	//Getter and Setter
	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getRcontent() {
		return rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	public String getRuid() {
		return ruid;
	}

	public void setRuid(String ruid) {
		this.ruid = ruid;
	}

	public String getRrefid() {
		return rrefid;
	}

	public void setRrefid(String rrefid) {
		this.rrefid = rrefid;
	}

	public int getRtype() {
		return rtype;
	}

	public void setRtype(int rtype) {
		this.rtype = rtype;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	
}
