package manager;

public class StandardWorks {
	private int ID;
	private String name;
	private int price;
	
	public StandardWorks() {
	}
	public StandardWorks(String work, int price) {
		this.name=work;
		this.price=price;
	}
	
	public int getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	
	public void setID(int Id) {
		this.ID=Id;
	}
	public void setName(String nm) {
		this.name=nm;
	}
	public void setPrice(int price) {
		this.price=price;
	}
}
