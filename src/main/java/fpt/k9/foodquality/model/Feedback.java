package fpt.k9.foodquality.model;

import java.sql.Date;

public class Feedback {
	private String fid;
	private String fuid;
	private String fpid;
	private int ftype;
	private String fcontent;
	private Date fdate;
	
	//add to display
	private String fusername;
	private String fuseravatar;
	
	//Constructor
	public Feedback()
	{
	}
	public Feedback(String fid, String fuid,String fpid, int ftype, String fcontent, Date fdate) {
		super();
		this.fid = fid;
		this.fuid = fuid;
		this.fpid = fpid;
		this.ftype = ftype;
		this.fcontent = fcontent;
		this.fdate = fdate;
	}

	//Setter and Getter
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getFuid() {
		return fuid;
	}
	public void setFuid(String fuid) {
		this.fuid = fuid;
	}
	public String getFpid() {
		return fpid;
	}
	public void setFpid(String fpid) {
		this.fpid = fpid;
	}
	public int getFtype() {
		return ftype;
	}
	public void setFtype(int ftype) {
		this.ftype = ftype;
	}
	public String getFcontent() {
		return fcontent;
	}
	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}
	public Date getFdate() {
		return fdate;
	}
	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}
	public String getFusername() {
		return fusername;
	}
	public void setFusername(String fusername) {
		this.fusername = fusername;
	}
	public String getFuseravatar() {
		return fuseravatar;
	}
	public void setFuseravatar(String fuseravatar) {
		this.fuseravatar = fuseravatar;
	}
	
}
