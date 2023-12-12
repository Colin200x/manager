package manager;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

class PanelRenderer extends JPanel implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7226722358453682631L;

	public PanelRenderer() {
		setOpaque(true);
		setLayout( new GridLayout() );
		add( new JButton( "A" ) );
		
	}

	
		
		
	
	

	public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column ) {
		
		return this;
	}
}//end class