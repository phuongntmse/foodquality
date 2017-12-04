package fpt.k9.foodquality.model;

import java.sql.Date;

public class Rate {
	private String rateid;
	private String rateuid;
	private int ratepoint;
	private String ratepid;
	private Date ratedate;
	
	//Constructor
	public Rate()
	{
	}
		
	public Rate(String rateid, String rateuid, int ratepoint, String ratepid,
			Date ratedate) {
		super();
		this.rateid = rateid;
		this.rateuid = rateuid;
		this.ratepoint = ratepoint;
		this.ratepid = ratepid;
		this.ratedate = ratedate;
	}


	//Getter and Setter
	public String getRateid() {
		return rateid;
	}

	public void setRateid(String rateid) {
		this.rateid = rateid;
	}

	public String getRateuid() {
		return rateuid;
	}

	public void setRateuid(String rateuid) {
		this.rateuid = rateuid;
	}

	public int getRatepoint() {
		return ratepoint;
	}

	public void setRatepoint(int ratepoint) {
		this.ratepoint = ratepoint;
	}

	public String getRatepid() {
		return ratepid;
	}

	public void setRatepid(String ratepid) {
		this.ratepid = ratepid;
	}

	public Date getRatedate() {
		return ratedate;
	}

	public void setRatedate(Date ratedate) {
		this.ratedate = ratedate;
	}
	
}
