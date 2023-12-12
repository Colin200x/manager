package manager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RendererSingleWorkTable extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -5244659974597800562L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if((row % 2)>0)cell.setBackground(new Color(220,220,220));
		else cell.setBackground(new Color(255,255,224));
			
		
		
        return cell;
	}
}
