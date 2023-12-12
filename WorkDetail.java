package manager;

public class WorkDetail {

	private int idDetail;
	private String detail;
	private String detailPrice;
	private int workID;
	
	public WorkDetail() {
		
	}
	/**
	 * 
	 * @param description
	 * @param price
	 */
	public WorkDetail(String description, String price) {
		this.detail = description;
		this.detailPrice = price;
	}
	
	public WorkDetail(int workDetailID, String workDetail, String price, int workID) {
		this.idDetail = workDetailID;
		this.detail = workDetail;
		this.detailPrice = price;
		this.workID = workID;
	}
	
	public int getID() {
		return idDetail;
	}
	public String getDetail() {
		return detail;
	}
	public String getPrice() {
		return detailPrice;
	}
	public int getWorkID() {
		return workID;
	}
	
	public void setID(int ID) {
		this.idDetail = ID;
	}
	public void setDetail(String det) {
		this.detail = det;
	}
	public void setPrice(String p) {
		this.detailPrice = p;
	}
	public void setWorkID(int wID) {
		this.workID = wID;
	}

}
