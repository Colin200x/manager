package manager;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;


public class SQLOperations {
	
	static final String USERNAME = "root";
    static final String PASSWORD = "12345";
    static final String CONN_STRING = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";
    static String query, valuesDati, selectPart, fromPart, amount, resStr, name;
    static int customerCount=0;
    static boolean res;
    
    /**
     * Generate table model for customers<p>
     * Populate this table from db<p>        
     * @return void
     */
    public SQLOperations() {
    	
    	System.out.println("SQLOperations is initilized: " + ++Manager6.sqlOPerationCount);
    	
    }
    public DefaultTableModel getCustomerList(){
    	DefaultTableModel model = new DefaultTableModel();
    	model.setColumnIdentifiers(new String[]{ "ID","NAME","SURNAME","PHONE","E-MAIL","PHONE 1","PHONE 2", "COMMAND"});
    	String []customer = new String[7];
    	
    	try {
            Connection conn;
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            query = "SELECT * FROM test.customers";
            ResultSet rs = stmt.executeQuery(query);
            //rs.beforeFirst();
            
            while (rs.next()) {
            		customer[0]=rs.getString("idCustomer");
            		customer[1]=rs.getString("customerName");
            		customer[2]=rs.getString("customerSurname");
            		customer[3]=rs.getString("customerPhone");
            		customer[4]=rs.getString("customerMail");
            		customer[5]=rs.getString("customerPhone2");
            		customer[6]=rs.getString("customerPhone3");
            		
            		model.addRow(customer);
            		customerCount++;
                		
            }
            System.out.println(this + " -> " + "customer list ok");
            rs.close(); stmt.close(); conn.close();
            
        }
        catch (Exception er) {System.err.println("customer list error > "+ er);}
    	return model; 
    }
    
    public DefaultTableModel getWorksList(){
    	DefaultTableModel model = new DefaultTableModel();
    	
    	
    	model.setColumnIdentifiers(new String[]{ "ID W","UNIT","WORK DETAILS","CUSTOMER","PHONE","PRICE","DATA","STAT", "COMMANDS"});
    	
    	Object []work = new Object[9];
    	try {
            Connection conn;
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            //selectPart="SELECT id_work, units.unit_model, work_list, customerName, customerSurname, customerPhone, work_price, DATE_FORMAT(date_time_begin, '%d/%m/%Y'), work_status";
         	//fromPart = "FROM works INNER JOIN customers ON works.id_customer= customers.idCustomer INNER JOIN units ON works.id_unit = units.unit_id WHERE (work_status='O' || work_status='C' || work_status='P')";
            
         	selectPart="SELECT id_work, units.unit_model, work_list, customerName, customerSurname, customerPhone, work_price, DATE_FORMAT(date_time_begin, '%d/%m/%Y'), work_status FROM works INNER JOIN customers ON works.id_customer= customers.idCustomer INNER JOIN units ON works.id_unit = units.unit_id WHERE (work_status='O' || work_status='C') ORDER BY id_work DESC";
            ResultSet rs = stmt.executeQuery(selectPart);
            //rs.beforeFirst();
            
            while (rs.next()) {
            	String wID = rs.getString("id_work");
            	resStr = rs.getString("work_list")+", ";            	
            	WorkDetail[] detArr = getWorksDetailList(Integer.parseInt(wID));            	
            	for(int i=0; i<detArr.length; i++) {            		
            		if(detArr[i]!=null){
            			query = detArr[i].getDetail();
            			if(isNumeric(query)) resStr += getStandardWorkByID(query)+", ";
            			else resStr += detArr[i].getDetail()+", ";
            		}
            	}
            	
            	work[0]=wID;
            	work[1]=rs.getString("unit_model");
            	work[2]=resStr;
            	work[3]=rs.getString("customerName")+" "+(rs.getString("customerSurname")==null?"":rs.getString("customerSurname"));
            	work[4]=formatPhoneString(rs.getString("customerPhone"));
            	work[5]=rs.getString("work_price");
            	work[6]=rs.getString(8);
            	work[7]=rs.getString("work_status");
            	work[8]= "CMD";
            	
            	model.addRow(work);
            } rs.close();
            System.out.println(this + " -> " + "works list ok");
            stmt.close(); conn.close(); 
        }
        catch (Exception er) {System.err.println(this+" -> "+er);}
    	return model;
    }//end getWorksList()
    
    public Work[] getWorksList(String unitID) {
    	Work[] abc = new Work[50];
    	int i=0;
    	try {
            Connection conn;
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            query = "SELECT id_work, work_list, work_price, work_status, DATE_FORMAT(date_time_begin, '%d/%m/%Y') FROM test.works where id_unit = "+unitID;
            
            ResultSet rs = stmt.executeQuery(query);
                      
            while(rs.next()){
               	abc[i] = new Work(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
         	   	i++;
            }           
            rs.close(); stmt.close(); conn.close();
        }
        catch (Exception er) {
        	name = new Object(){}.getClass().getEnclosingMethod().getName();
        	System.err.println(name + " >> "+ er);
        }	   
    	
    	return abc;
    }//end getWorksList()
    
    
    public WorkDetail getWorkDetail(int workID) {
    	WorkDetail wd = new WorkDetail();
    	try {
            Connection conn;
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            query = "SELECT work_detail, work_detail_price FROM work_details where work_id = " + workID + " limit 1";
            
            ResultSet rs = stmt.executeQuery(query);
                      
            if(rs.first()) wd = new WorkDetail(0, rs.getString(1), rs.getString(2), workID);
         	
            
            rs.close(); stmt.close(); conn.close();
        }
        catch (Exception er) {
        	name = new Object(){}.getClass().getEnclosingMethod().getName();
        	System.err.println(name + " >> "+ er);
        }
    	return wd;
    }//end WorkDetail
    
    /**
     * 
     * @param workID integer
     * @return array[10] of WorkDetail objects
     */
    public WorkDetail[] getWorksDetailList(int workID) {
    	WorkDetail[] abc = new WorkDetail[10];
    	int i=0;
    	try {
            Connection conn;
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            query = "SELECT work_detail, work_detail_price FROM work_details where work_id = "+workID;
            
            ResultSet rs = stmt.executeQuery(query);
                      
            while(rs.next()){
               	abc[i] = new WorkDetail(0, rs.getString(1), rs.getString(2), workID);
         	   	i++;
            }           
            rs.close(); stmt.close(); conn.close();
        }
        catch (Exception er) {
        	name = new Object(){}.getClass().getEnclosingMethod().getName();
        	System.err.println(name +" int "+ er);
        }
    	
    	
    	return abc;
    }//end getWorksDetailsList()
    
    public WorkDetail[] getWorksDetailList(Work[] work) {
    	WorkDetail[] abc = new WorkDetail[10];
    	int i=0;
    	try {
            Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs=null;
            for(int j=0; j<work.length; j++) {
            	if(work[j]!=null){
            		query = "SELECT work_detail, work_detail_price FROM work_details where work_id = "+work[j].getID();
                	rs = stmt.executeQuery(query);
                	while(rs.next()){
                       	abc[i] = new WorkDetail(0, rs.getString(1), rs.getString(2), work[j].getID());
                 	   	i++;
                    }
                	rs.close();
            	}	
            }
             stmt.close(); conn.close();
        }
        catch (Exception er) {
        	name = new Object(){}.getClass().getEnclosingMethod().getName();
        	System.err.println(name +" [] "+ er);
        }
    	return abc;
    }//end getWorksDetailsList()
    
    /**
     * @param values is String[6]
     * @return empty String or error message
     */
    public String insertNewCustomer(String values[]){
    	
    	try {
            Connection conn;
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
             
            valuesDati = "VALUES ('"+values[0]+"', '"+values[1]+"', '"+values[2]+"', '"+values[3]+"', '"+values[4]+"', '"+values[5]+"')";
            query = "INSERT INTO customers (customerName, customerSurname, customerPhone, customerMail, customerPhone2, customerPhone3) "+valuesDati;
            stmt.executeUpdate(query);            
            stmt.close(); conn.close();            
        }
    	      catch (Exception er) {    
        	return er.getMessage();
        }
    	return "";
    }

    public static boolean isNumeric(String str)  
    {  
      try  
      {  
        int d = Integer.parseInt(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }
    
    /**
     * @param values is String[6]
     * @return object Customer()
     */
    public Customer insertNewCustomer(String name, String surname, String phone, String mail, String phone2, String phone3){
    	Customer customer = new Customer();
    	try {
            Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
             
            valuesDati = "VALUES ('"+name+"', '"+ surname +"', '"+ phone +"', '"+ mail +"', '"+ phone2 +"', '"+ phone3 +"')";
            query = "INSERT INTO customers (customerName, customerSurname, customerPhone, customerMail, customerPhone2, customerPhone3) "+valuesDati;
            stmt.executeUpdate(query);
            
            customer=searchCustomer(phone, 0);
            
            stmt.close(); conn.close();            
        }
    	      catch (Exception er) {System.out.println(er.getMessage()); }
    	return customer;
    }
    
    /**
     * @param
     * @return <b>BOOLEAN</b> If customer exists <i>TRUE</i> otherwise <i>FALSE</i>
     */
    public boolean searchCustomer(String phone){
    	try {
            Connection conn;
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            query = "Select * from customers where customerPhone='"+phone+"'";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.first()) res=true; else res=false;
            rs.close(); stmt.close(); conn.close();
        }
        catch (Exception er) {System.err.println(er);}
    	return res;
    }//end searchCustomer()
    
    /**
     * 
     * @param str customer string
     * @param par int value (0 - search by phone, 1 - search by name)
     * @return Customer object with all fields.
     */
    public Customer searchCustomer(String str, int par){
    	Customer res = null; 
    	try {
            Connection conn;
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            switch(par) {
            	case 0: query = "Select * from customers where customerPhone='"+str+"'"; break;
            	case 1: query = "Select * from customers where customerName='"+str+"'"; break;
            }
            
            
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
            	res = new Customer();
            	res.setID(rs.getInt("idCustomer"));
            	res.setName(rs.getString("customerName"));
            	res.setSurname(rs.getString("customerSurname"));
            	res.setPhone(rs.getString("customerPhone"));
            	res.setEMail(rs.getString("customerMail"));
            	res.setPhone2(rs.getString("customerPhone2"));
            	res.setPhone3(rs.getString("customerPhone3"));
            }
            rs.close(); stmt.close(); conn.close();
        }
        catch (Exception er) {System.err.println("\t- "+er + "customer not found");}
    	return res;
    }//end searchCustomer()
    
    public boolean searchUnit(String id) {
    	try {
            Connection conn;
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            query = "SELECT * FROM units where unit_id='"+id+"'";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.first()) res=true; else res=false;
            rs.close(); stmt.close(); conn.close();
        }
        catch (Exception er) {System.err.println(er);}
    	return res;
    }//end searchUnit(String id)
    
    
    
    public Unit insertNewUnit(String model, int customer_id, String details) {
    	Unit unit = new Unit();
    
    	try {
            Connection conn;
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            valuesDati = "VALUES ('"+ model +"', '" + customer_id + "', '" + details + "')";
            query = "INSERT INTO units (unit_model, customer_id, unit_details) "+valuesDati; 
            stmt.executeUpdate(query);            
            ResultSet rs = stmt.executeQuery("SELECT * FROM units ORDER BY unit_id DESC LIMIT 1");
            if(rs.next()) {
            	unit.setID(rs.getInt(1));
            	unit.setModel(rs.getString(2));
            	unit.setCustomerID(rs.getInt(3));
            }
            rs.close(); stmt.close(); conn.close();
        }
    	catch (Exception er) { System.out.println(er.getMessage()); }
    	return unit;
    }//end insertNewUnit(String[] tokens)
    
    /**
     * @param values String Array work[7]</br> 
     * [0] - customer phone</br>
     * [1] - customer name</br>
     * [2] - customer surname</br>
     * [3] - unit</br>
     * [4] - <i>(int)</i>work price</br>
     * [5] - work details</br>
     * [6] - work begin date</br>
     * @deprecated
     * This method is no longer acceptable to compute time between versions.
     * @param machine instance
     * @return computed time
     */
    public void insertNewWork(String []values){
    	int idCustomer=0;
    	try {
            Connection conn;
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs=null;
           
            if(searchCustomer(values[0])){
            	System.out.println("customer exist, insert unit and work");
            	idCustomer = searchCustomer(values[0], 0).getID();
            	
            	stmt.executeUpdate("INSERT INTO units (unit_model, customer_id) VALUES ('"+ values[3] +"', "+idCustomer+");");
                rs = stmt.executeQuery("SELECT unit_id FROM units ORDER BY unit_id DESC LIMIT 1");
                if(rs.next()) System.out.println("\t\tnew unit id: " + rs.getInt(1));
                
                query="INSERT INTO works (id_unit, id_customer, work_list, work_price, work_status, date_time_begin)"; 
                valuesDati="VALUES ("+rs.getInt(1)+","+idCustomer+", '"+values[5]+"',"+values[4]+",'O','"+values[6]+"')";
                stmt.executeUpdate(query +" "+valuesDati); 
            }
            else {
            	query="INSERT INTO customers (customerName, customerSurname ,customerPhone) " + "VALUES ('"+values[1]+"', '"+ values[2] +"' ,'"+ values[0] +"')";
            	stmt.executeUpdate(query);
            	
                rs = stmt.executeQuery("SELECT idCustomer FROM customers ORDER BY idCustomer DESC LIMIT 1");
                if(rs.next()) 
                	{
                		System.out.println("\t\tnew customer id: " + rs.getInt(1));
                		idCustomer=rs.getInt(1);
                	}
                
                stmt.executeUpdate("INSERT INTO units (unit_model, customer_id) VALUES ('"+ values[3] +"', "+idCustomer+");");
                rs = stmt.executeQuery("SELECT unit_id FROM units ORDER BY unit_id DESC LIMIT 1");
                if(rs.next()) System.out.println("\t\tnew unit id: " + rs.getInt(1));
                
                query="INSERT INTO works (id_unit, id_customer, work_list, work_price, work_status, date_time_begin)"; 
                valuesDati="VALUES ("+rs.getInt(1)+","+idCustomer+", '"+values[5]+"',"+values[4]+",'O','"+values[6]+"')";
                stmt.executeUpdate(query +" "+valuesDati); 
            }
            System.out.println(this + " -> " + "new work created");
            rs.close(); stmt.close(); conn.close();
        }
        catch (Exception er) {System.err.println(er);}
    }//end insertNewWork()
    
    
    public void insertNewWork(String list, String price, String date, int unitID, int customerID) {
    	try {
            Connection conn;
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String[] str = list.split(",");
            String[] prices = price.split(",");
            
            query="INSERT INTO works (id_unit, id_customer, work_list, work_price, work_status, date_time_begin)"; 
            valuesDati="VALUES ("+ unitID +","+ customerID +",'" + str[0] + "'," + prices[prices.length-1] + ",'O','"+ date +"')";
            stmt.executeUpdate(query +" "+valuesDati);
            if(str.length>1) {
            	for(int i=1; i<str.length;i++) {
            		ResultSet rs = stmt.executeQuery("SELECT id_work FROM works ORDER BY id_work DESC LIMIT 1");
            		if(rs.next()) {
            			query="INSERT INTO work_details (work_detail, work_detail_price, work_id) "
            					+ "VALUES ('"+ str[i] +"', " + prices[i] + ", "+ rs.getInt(1) +")";
            			stmt.executeUpdate(query);
            		}        		
            		rs.close();
            	}
            }
             stmt.close(); conn.close();
            
    	}catch (Exception er) {System.err.println(this+" -> "+er);}
    	
    }//end insertNewWork(String list, String price, String date, int unitID, int customerID)
    
    /**
     * @param 
     * String array tokens[9]</br>
     * [0] - customer ID</br>
     * [1] - customer phone</br>
     * [2] - customer name</br>
     * [3] - customer surname</br>
     * [4] - unit ID</br>
     * [5] - unit</br>
     * [6] - <i>(int)</i>work prices</br>
     * [7] - work details</br>
     * [8] - work begin date</br></br>
     */
    public void insertWork(String[] tokens) {
    	Unit unit;
    	if(Integer.parseInt(tokens[0])==0) {
    		System.out.println(">> Inseret new customer. Insert new unit. Insert new work.");
    		
    		Customer customer = insertNewCustomer(tokens[2], tokens[3], tokens[1], "", "", "");
    		
    		if(customer!=null) {
    			
    			unit = insertNewUnit(tokens[5], customer.getID(), "serial");
    			insertNewWork(tokens[7], tokens[6], tokens[8], unit.getUnitID(), unit.getCustomerID());
    		}
    		else System.out.println(">> customer does not exist!"); 		
    	
    	}else if(Integer.parseInt(tokens[4])==0) {
    		System.out.println(">> Customer exist. Insert new unit. Insert new work.");
    		unit = insertNewUnit(tokens[5], Integer.parseInt(tokens[0]), "");
    		insertNewWork(tokens[7], tokens[6], tokens[8], unit.getUnitID(), unit.getCustomerID());
    	}else insertNewWork(tokens[7], tokens[6], tokens[8], Integer.parseInt(tokens[4]),Integer.parseInt(tokens[0]));
    	
    }//end insertWork()
    
    public boolean payWork(String idWork){
       	try {
               Connection conn;
               conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
               Statement stmt = (Statement) conn.createStatement();
               query = "UPDATE works SET work_status='P' WHERE id_work="+idWork;
               if(stmt.execute(query)){
               	System.out.println(this + " -> "+ "work was paid");
               	res=true;
               }
               else res=false;           
               stmt.close(); conn.close();
           }
           catch (Exception er) {
        	   name = new Object(){}.getClass().getEnclosingMethod().getName();
        	   System.err.println(name + " > " + er);
           }
       	return res;
       }//end payWork()
    
 
   public boolean deleteWork(String idWork){
    	try {
            Connection conn;
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            query = "DELETE FROM works WHERE id_work="+idWork;
            if(stmt.execute(query)){
            	System.out.println(this + " -> "+ "work was deleted");
            	res=true;
            }
            else res=false;
            
            stmt.close(); conn.close();
        }
        catch (Exception er) {System.err.println(er);}
    	
    	return res;
    }//end deleteWork()
    
   public boolean closeWork(String idWork){
   	try {
           Connection conn;
           conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
           Statement stmt = (Statement) conn.createStatement();
           query = "UPDATE works SET work_status='C' WHERE id_work="+idWork;
           if(stmt.execute(query)){
           	System.out.println(this + " -> "+ "work was closed");
           	res=true;
           }
           else res=false;           
           stmt.close(); conn.close();
       }
       catch (Exception er) {System.err.println(er);}
   	return res;
   }//end closeWork()
   
   /**
    * 
    * @param str - id customer
    * @return String of units
    */
   public String getUnitList(String str){
	   try {
           Connection conn;
           conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
           Statement stmt = (Statement) conn.createStatement();
           query = "SELECT * FROM test.units where customer_id="+str;
           ResultSet rs = stmt.executeQuery(query);
           
           while(rs.next()){
        	   str+=" -> "+rs.getInt(1);
        	   str+=" "+rs.getString(2)+"\n";
           }           
           rs.close(); stmt.close(); conn.close();
       }
       catch (Exception er) {System.err.println(er);}
	   
	   return str;
   }//end getUnitList()
   
   /**
    * 
    * @param customer (int)ID
    * @return Unit[50] array
    */
   public Unit []getUnitList(int ID){
	   Unit[] abc = new Unit[50];
	   int i=0;
	   try {
           Connection conn;
           conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
           Statement stmt = (Statement) conn.createStatement();
           query = "SELECT * FROM units where customer_id="+ID;
           ResultSet rs = stmt.executeQuery(query);
                     
           while(rs.next()){
        	   abc[i] = new Unit(rs.getInt(1),rs.getString(2));
        	   i++;
           }           
           rs.close(); stmt.close(); conn.close();
       }
       catch (Exception er) {System.err.println(er);}	   
	   return abc;
   }//end getUnitList()
   
   public String getAmountsAndDate(){
   	try {
           Connection conn;
           conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
           Statement stmt = (Statement) conn.createStatement();
           query = "SELECT work_price, date_time_begin FROM test.works where (work_status='C' || work_status='P')";
           ResultSet rs = stmt.executeQuery(query);
           amount = new String();
           while (rs.next()) {
			
        	char[] ch = new char[2];
        	
            for (int i = 0; i < 2; i++) ch[i] = rs.getString(2).charAt(i+8);
            
			amount+=rs.getInt(1)+","+ch[0]+ch[1]+",";
			
           }
           
           
           
           rs.close(); stmt.close(); conn.close();
       }
       catch (Exception er) {System.err.println(er);}
   	return amount;
   }//end getAmountsAndDAte

   /**
    * 
    * @param str phone number
    * @return string form XXX-XX-XX-XXX
    */
   public String formatPhoneString(String str){	   
	   resStr=	""+
			   	str.charAt(0)+
			    str.charAt(1)+
			   	str.charAt(2)+"-"+
			   	str.charAt(3)+
			   	str.charAt(4)+"-"+
			   	str.charAt(5)+
			   	str.charAt(6)+"-"+
			   	str.charAt(7)+
			   	str.charAt(8)+
			   	str.charAt(9);		   	
	   return resStr;
   }//end formatPhoneString()
   
   
   /**
    * @param str work details to do
    * @param price work price
    * @return <i>TRUE</i> otherwise <i>FALSE</i>
    */
   public boolean updateWork(String id, String str, String price) {
	   try {
           Connection conn;
           conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
           Statement stmt = (Statement) conn.createStatement();
           query="UPDATE test.works SET work_list = '"+str+"', work_price = '"+Integer.parseInt(price)+"' WHERE (`id_work` = '"+Integer.parseInt(id)+"')";
           if(stmt.execute(query)){
           	System.out.println(this + " -> "+ "work was updated");
           	res=true;
           }
           else res=false;
           
           stmt.close(); conn.close();
       }
       catch (Exception er) {System.err.println("ERROR: "+er);}
   	
   	return res;
   }//end updateWork()
   
   public String[] getStandartWork(String id) {
	   String []standardWork = new String[2];
	   try {
           	Connection conn;
           	conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
           	Statement stmt = (Statement) conn.createStatement();
           	query = "SELECT * FROM standart_works where id_standart_work="+id;
           	ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
            	standardWork[0]=rs.getString("description");
               	standardWork[1]=rs.getString("price");
            }else {
            	standardWork[0]="";
            	standardWork[1]="";
            }
           	
           	rs.close(); stmt.close(); conn.close();
       }
       catch (Exception er) {System.err.println(er);}
	   
	   return standardWork;
   }//end getStandartWork
   
   public String getStandardWorkList() {
	   valuesDati = selectPart = "";
	   
	   int i=1;
	   try {
          	Connection conn;
          	conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
          	Statement stmt = (Statement) conn.createStatement();
          	query = "SELECT * FROM standart_works";
          	ResultSet rs = stmt.executeQuery(query);
           while(rs.next()) {
        	   if((i % 3)==0) selectPart=" \n "; else selectPart="";
        		   valuesDati+=" "+rs.getInt(1);
            	   valuesDati+= ">" + rs.getString(2)+selectPart;
            	   //valuesDati+=rs.getInt(3)+selectPart;   
        	      i++;
           }
          	rs.close(); stmt.close(); conn.close();
      }
      catch (Exception er) {
    	  	name = new Object(){}.getClass().getEnclosingMethod().getName();
   	   		System.err.println(name + " > " + er);
      }
	   //System.out.print(valuesDati);
	   return valuesDati;
   }
   
   
   /**
    * 
    * @param idWork
    * @return String standart work name
    */
   public String getStandardWorkByID(String id) {
	   resStr=id;
	   try {
          	Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
          	Statement stmt = (Statement) conn.createStatement();
          	ResultSet rs;
          	if(isNumeric(id)) {
              	rs = stmt.executeQuery("SELECT * FROM standart_works where id_standart_work="+id);
              	if(rs.next()) resStr = rs.getString(2);
              rs.close();
          	}
          	stmt.close(); conn.close();
	   }
	   catch (Exception er) {
		    name = new Object(){}.getClass().getEnclosingMethod().getName();
		   System.err.println(name + " > " + er);
	   }
	   
	   return resStr;
   }//end getStandardWorkByID
   
   
}//class end
