package CSCI5308.GroupFormationTool.Survey;

public class Survey {
	private long sId;
	private String sTitle;
	private String isPublished;

	public Survey(){
		//default constructor
	}
	public Survey(String sTitle, String isPublished) {
		this.sTitle = sTitle;
		this.isPublished = isPublished;
	}

	public long getSId() {
		return sId;
	}
	public void setSId(long sId) {
		this.sId = sId;
	}
	public String getSTitle() {
		return sTitle;
	}
	public void setSTitle(String sTitle) {
		this.sTitle = sTitle;
	}
	public String getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(String isPublished) {
		this.isPublished = isPublished;
	}

}

