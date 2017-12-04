package fpt.k9.foodquality.model;
public class User
{
	//Attribute
	private String uid;
	private String uname;
	private String uacc;
	private String upass;
	private String umail;
	private String uadd;
	private String uphone;
	private String uimage;
	private int urole;
	
	//Constructor
	public User()
	{
	}
	
	public User(String uid, String uname, String uacc, String upass,
			String umail, String uadd, String uphone, String uimage, int urole) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.uacc = uacc;
		this.upass = upass;
		this.umail = umail;
		this.uadd = uadd;
		this.uphone = uphone;
		this.uimage = uimage;
		this.urole = urole;
	}

	//Getter and Setter
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUacc() {
		return uacc;
	}

	public void setUacc(String uacc) {
		this.uacc = uacc;
	}

	public String getUpass() {
		return upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}

	public String getUmail() {
		return umail;
	}

	public void setUmail(String umail) {
		this.umail = umail;
	}

	public String getUadd() {
		return uadd;
	}

	public void setUadd(String uadd) {
		this.uadd = uadd;
	}
	
	public String getUphone() {
		return uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	
	public String getUimage() {
		return uimage;
	}

	public void setUimage(String uimage) {
		this.uimage = uimage;
	}

	public int getUrole() {
		return urole;
	}

	public void setUrole(int urole) {
		this.urole = urole;
	}
	
}