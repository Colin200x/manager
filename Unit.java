package manager;

public class Unit {

	private int unitID;
	private int customerID;
	public String unitModel;
	
	public Unit() {
		
	}
	
	public Unit(String model) {
		this.unitModel=model;
	}
	
	public Unit(int unitID, String model) {
		this.unitModel=model;
		this.unitID=unitID;
	}
	
	
	public String getUnitModel() { return unitModel; }
	
	public int getUnitID() { return unitID; }
	
	public int getCustomerID() { return customerID; }
	
	
	public void setID(int ID) { this.unitID=ID; }
	
	public void setModel(String model) { this.unitModel=model; }
	 
	public void setCustomerID(int ID) { this.customerID=ID; }

}