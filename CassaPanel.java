package manager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class CassaPanel extends JPanel {
	static int []res = new int[10];
	int day,total;
	
	public CassaPanel(){
		setLayout(new BorderLayout());
		JPanel titlePanel = new JPanel();
		titlePanel.add(new JLabel("CASSA"));
		titlePanel.setBackground(Color.ORANGE);

		add(titlePanel, BorderLayout.NORTH);
		System.out.println("CASSA LOADED");
	}//end constructor
	
	public void init(){
		SQLOperations sqlOper = new SQLOperations();		
		String []cols= new String[]{
				"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};   
			String [][]rowData = new String[10][31];
			
			String[] ary = sqlOper.getAmountsAndDate().split(",");

			JTable table = new JTable(rowData, cols);
			
			for(int i=1; i<(ary.length); i+=2){
				day=Integer.parseInt(ary[i])-1;
				if(table.getValueAt(0, day)==null) table.setValueAt(ary[i-1], 0, day);
				else if(table.getValueAt(1, day)==null)table.setValueAt(ary[i-1], 1, day);
				else if(table.getValueAt(2, day)==null)table.setValueAt(ary[i-1], 2, day);
				total+=Integer.parseInt(ary[i-1]);
			}

			
			table.setShowHorizontalLines(false);
			table.setBackground(Color.LIGHT_GRAY);
			
			
			JScrollPane sp = new JScrollPane(table);
			add(sp, BorderLayout.CENTER);
			add(new JLabel("TOTAL: "+total), BorderLayout.SOUTH);
	}

}//end class
