package manager;

import javax.swing.table.DefaultTableModel;

public class UnitShortTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6208431723177508590L;

	public UnitShortTableModel() {
		setColumnIdentifiers(new String[]{"ID", "UNIT", "DETAILS"});
	}
	
	public DefaultTableModel getShortModel(Unit[] devices) {
		setRowCount(0);
		for(int i=0; i<devices.length; i++) {
			if(devices[i]!=null)addRow(new String[] {devices[i].getUnitID()+"",devices[i].getUnitModel(),"details"});
		}
		
	return this;
	}
}
