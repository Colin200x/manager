package manager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Renderer  extends DefaultTableCellRenderer{

	private static final long serialVersionUID = 7165226871943090111L;
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		
		switch(column){
			case 0: 
				setHorizontalAlignment(JLabel.CENTER);
				break; 
			case 1: break;
			case 2: break;
			case 3: setHorizontalAlignment(JLabel.CENTER); break;
			case 4: break;
			case 5: setHorizontalAlignment(JLabel.CENTER); break;
			case 6: setHorizontalAlignment(JLabel.CENTER); break;
		}
		
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if((row % 2)>0)cell.setBackground(new Color(220,220,220));
		else cell.setBackground(new Color(255,255,224));
		
		setFont(new Font("Verdana", Font.PLAIN, 10));
		
		if(isSelected){
			setBackground(Color.LIGHT_GRAY);
			setFont(new Font("Verdana",Font.BOLD,12));
		}
			
		
		
        return cell;
	}
	

}
