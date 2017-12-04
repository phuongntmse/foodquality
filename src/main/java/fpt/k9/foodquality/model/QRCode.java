package fpt.k9.foodquality.model;

public class QRCode {
	private String qid;
	private String qrefid;
	private String qimage;
	
	//Constructor
	public QRCode()
	{
	}

	public QRCode(String qid, String qrefID, String qimage) {
		super();
		this.qid = qid;
		this.qrefid = qrefID;
		this.qimage = qimage;
	}


	//Getter and Setter
	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getQrefID() {
		return qrefid;
	}

	public void setQrefID(String qrefID) {
		this.qrefid = qrefID;
	}

	public String getQimage() {
		return qimage;
	}

	public void setQimage(String qimage) {
		this.qimage = qimage;
	}
	
}
