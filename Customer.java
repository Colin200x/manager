package manager;

public class Customer {
	
	static String name, surname, email, phone, phone2, phone3;
	static private int idCustomer;
	
	public Customer(){
		
	}
	
	public int getID(){
		return idCustomer;
	}
	public String getName(){
		return name;
	}
	public String getSurname(){
		return surname;
	}
	public String getEMail(){
		return email;
	}
	public String getPhone(){
		return phone;
	}
	public String getPhone2(){
		return phone2;
	}
	public String getPhone3(){
		return phone3;
	}
	
	
	public void setID(int i){
		Customer.idCustomer=i;
	}
	public void setName(String str){
		Customer.name=str;
	}
	public void setSurname(String str){
		Customer.surname=str;
	}
	public void setPhone(String str){
		Customer.phone=str;
	}
	public void setEMail(String str){
		Customer.email=str;
	}
	public void setPhone2(String str){
		Customer.phone2=str;
	}
	public void setPhone3(String str){
		Customer.phone3=str;
	}
	
	
	
}//end class
