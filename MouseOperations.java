package manager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MouseOperations implements MouseListener{
	
	JPanel panel;
	
	public MouseOperations(JPanel pnl) {
		this.panel=pnl;
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		JTable tb = (JTable)e.getSource();
		int row = tb.getSelectedRow();
		Component []comps = panel.getComponents();
		
		//System.out.println(comps.length);
		
		JTextField customer = (JTextField)comps[2];
		JTextField phone = (JTextField)comps[8];
		JTextField unit = (JTextField)comps[5];
		JTextField idWork = (JTextField)comps[7];
		JTextField price = (JTextField)comps[10];
		JTextField date = (JTextField)comps[9];
		
		
		String str = tb.getValueAt(row, 4).toString();
		
		customer.setText(tb.getValueAt(row, 3).toString()); 
		phone.setText(""+str.charAt(0)+str.charAt(1)+str.charAt(2)+str.charAt(4)+str.charAt(5)+str.charAt(7)+str.charAt(8)+str.charAt(10)+str.charAt(11)+str.charAt(12));	 
		unit.setText(tb.getValueAt(row, 1).toString());		
		price.setText(tb.getValueAt(row, 5).toString());
		//workDetails.setText(tb.getValueAt(row, 2).toString());
		date.setText(tb.getValueAt(row, 6).toString());	
		idWork.setText(tb.getValueAt(row, 0).toString());
		String []str1 = tb.getValueAt(row, 2).toString().split(",");
		
		
		
		
		JScrollPane sp = (JScrollPane) comps[14];
		Component []spComps = sp.getComponents();
		JViewport view = (JViewport) spComps[0];
		JTable table = (JTable)view.getView();
		
		
		
		DefaultTableModel model = new DefaultTableModel(new String[] { "CODE", "DESCRIPTION", "PRICE" }, 0); 
		
		for(int i=0; i<5; i++) {
			if(i<str1.length) {
				model.addRow(new String[]{"",str1[i],""});
			}else model.addRow(new String[]{"","",""});
		}
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(new Font("Verdana",Font.PLAIN,10));
		table.setModel(model);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
