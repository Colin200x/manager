package manager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RendererWorks extends DefaultTableCellRenderer{

	private static final long serialVersionUID = 7165226871943090111L;
	
	public Component getTableCellRendererComponent(	JTable table, 
													Object value, 
													boolean isSelected, 
													boolean hasFocus, 
													int row, 
													int column){
		
		switch(column){
			case 0: 
				setHorizontalAlignment(JLabel.CENTER);
			break; 
			case 1: break;
			case 2: break;
			case 3: break;
			case 4: setHorizontalAlignment(JLabel.CENTER); break;
			case 5: setHorizontalAlignment(JLabel.RIGHT); break;
			case 6: setHorizontalAlignment(JLabel.CENTER); break;
			case 7: setHorizontalAlignment(JLabel.CENTER); break;
			case 8: setHorizontalAlignment(JLabel.CENTER); break;
		}
		
		
		
		
		
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		String cellValue = (String) table.getValueAt(row, table.getColumnModel().getColumnIndex("STAT"));
		
		if(cellValue.equals("O")) cell.setBackground(new Color(255,255,158));
		else if(cellValue.equals("C")) cell.setBackground(new Color(166,255,170));
		else if(cellValue.equals("P")) cell.setBackground(Color.LIGHT_GRAY);
		setFont(new Font("Verdana", Font.PLAIN, 10));
		if(isSelected){
			cell.setBackground(Color.WHITE);
			setFont(new Font("Verdana", Font.BOLD, 10));
		}
		
        return cell;
	}

}//end class
