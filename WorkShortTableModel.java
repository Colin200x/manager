package manager;

import javax.swing.table.DefaultTableModel;

public class WorkShortTableModel extends DefaultTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3882589708030506394L;
	
	String str="";
	
	public WorkShortTableModel() {
		setColumnIdentifiers(new String[]{"ID", "DESCRIPTION", "PRICE", "DATE", "STS"});
	}
	
	public DefaultTableModel getShortModel(Work[] devices) {
		setRowCount(0);
		for(int i=0; i<devices.length; i++) {
			if(devices[i]!=null)
			addRow(new String[] { devices[i].getID()+"",devices[i].getList(), devices[i].getPrice(), devices[i].getDateBegin(), devices[i].getStatus() });
		}
	return this;
	}//end getShortModel()
	
	public DefaultTableModel getShortModel(Work[] devices, WorkDetail[] details, SQLOperations op) {
		setRowCount(0);
		for(int i=0; i<devices.length; i++) {
			if(devices[i]!=null) {
				addRow(new String[] { devices[i].getID()+"",devices[i].getList(), devices[i].getPrice(), devices[i].getDateBegin(), devices[i].getStatus() } );
				for(int j=0; j < details.length; j++) {
					
					if(details[j]!=null && (devices[i].getID() == details[j].getWorkID())) {
						
						str = op.getStandardWorkByID(details[j].getDetail());
					
						addRow(new String[] { "", str, details[j].getPrice(), "",""} );
					}
				}
			}
		}
	return this;
	}//end getShortModel()
}//end class
