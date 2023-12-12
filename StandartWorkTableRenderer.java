package manager;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class StandartWorkTableRenderer extends DefaultTableCellRenderer{

	private static final long serialVersionUID = -7439851191097158846L;
	
	
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		
		
		
		
		SQLOperations sqlOper = new SQLOperations();
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if(row == 0) cell.setBackground(new Color(195,245,196)); else cell.setBackground(new Color(255,255,255));
		
		switch(column){
			case 0: 
				setHorizontalAlignment(JLabel.CENTER);
				if(isSelected) {
					cell.setBackground(new Color(255,255,158));
					if(!hasFocus) {
						String []str= new String[2];
						str=sqlOper.getStandartWork((String)value);
						table.setValueAt(str[0], row, 1);
						table.setValueAt(str[1], row, 2);
					}
				}
			break; 
			case 1: break;
			case 2: setHorizontalAlignment(JLabel.RIGHT); break;
	
		}
		
		
		return cell;
	}

}
