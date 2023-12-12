package manager;

import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableModel;

public class SmallWorkList extends JTable{

	public SmallWorkList(){
		JTable table = new JTable(5, 3);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "CAMBIO OS", "20"},
				{"", "", "20"},
				{"", "", ""},
				{null, null, null},
				{null, null, null}
			},
			new String[] { "CODE", "DESCRIPTION", "PRICE" }
			)
		);
		System.out.print("new table wad created");
	}//end constructor
	
}//end class
