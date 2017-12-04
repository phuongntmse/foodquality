package fpt.k9.foodquality.model;

public class RegisterPTS {
	private String registerID;
	private int registerType;
	private String registerUid;
	private String registerName;
	private String registerImage;
	private String registerTax;
	private String registerphone;
	private String registeradd;
	private String registermail;
	private String registeroweb;
	
	public RegisterPTS()
	{
	}
	public RegisterPTS(String registerID,int registerType, String registerUid,
			String registerName, String registerImage, String registerTax,
			String registerphone, String registeradd, String registermail,
			String registeroweb) {
		super();
		this.registerID = registerID;
		this.registerType = registerType;
		this.registerUid = registerUid;
		this.registerName = registerName;
		this.registerImage = registerImage;
		this.registerTax = registerTax;
		this.registerphone = registerphone;
		this.registeradd = registeradd;
		this.registermail = registermail;
		this.registeroweb = registeroweb;
	}
	public String getRegisterID() {
		return registerID;
	}
	public void setRegisterID(String registerID) {
		this.registerID = registerID;
	}
	public String getRegisterUid() {
		return registerUid;
	}
	public void setRegisterUid(String registerUid) {
		this.registerUid = registerUid;
	}
	public String getRegisterName() {
		return registerName;
	}
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
	public String getRegisterImage() {
		return registerImage;
	}
	public void setRegisterImage(String registerImage) {
		this.registerImage = registerImage;
	}
	public String getRegisterTax() {
		return registerTax;
	}
	public void setRegisterTax(String registerTax) {
		this.registerTax = registerTax;
	}
	public String getRegisterphone() {
		return registerphone;
	}
	public void setRegisterphone(String registerphone) {
		this.registerphone = registerphone;
	}
	public String getRegisteradd() {
		return registeradd;
	}
	public void setRegisteradd(String registeradd) {
		this.registeradd = registeradd;
	}
	public String getRegistermail() {
		return registermail;
	}
	public void setRegistermail(String registermail) {
		this.registermail = registermail;
	}
	public String getRegisteroweb() {
		return registeroweb;
	}
	public void setRegisteroweb(String registeroweb) {
		this.registeroweb = registeroweb;
	}
	public int getRegisterType() {
		return registerType;
	}
	public void setRegisterType(int registerType) {
		this.registerType = registerType;
	}
	
}
