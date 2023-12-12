package manager;

public class Work {

	private int ID;
	private String list, unitModel, workDescription, customerName, customerPhone;
	private String price;
	private String status;
	private String dateBegin;
	
	public Work(){
		
	}
	
	public Work(int ID, String ls, String p, String st, String dateB){
		this.ID=ID;
		this.list=ls;
		this.price=p;
		this.status=st;
		this.dateBegin=dateB;
	}
	/**
	 * 
	 * @param ID
	 * @param unitModel
	 * @param workDescription
	 * @param customerName
	 * @param customerPhone
	 * @param workPrice
	 * @param dateBegin
	 * @param workStatus
	 */
	public Work(int ID, String unitModel, String workDescription, String customerName, String customerPhone, String workPrice, String dateBegin, String workStatus){
		this.ID=ID;
		this.unitModel = unitModel;
		this.workDescription = workDescription;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.price = workPrice;
		this.status = workStatus;
		this.dateBegin=dateBegin;
	}
	
	public int getID() { return ID; }
	
	public String getList() { return list; }
	
	public String getPrice() {
		return price;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getDateBegin() {
		return dateBegin;
	}
	
	public void setID(int id) {
		this.ID=id;
	}
	public void setList(String lista) {
		this.list=lista;
	}
	public void setPrice(String prezzo) {
		this.price=prezzo;
	}
	public void setStatus(String st) {
		this.status=st;
	}
	public void setDateBegin(String date) {
		this.dateBegin=date;
	}
}
