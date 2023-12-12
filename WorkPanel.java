package manager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;





public class WorkPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WorkPanel(){
		
		setLayout(new BorderLayout());
		JPanel titlePanel = new JPanel();
		JLabel openWorkLable1 = new JLabel("APERTO"); openWorkLable1.setOpaque(true); openWorkLable1.setBackground(new Color(255,255,158));
		JLabel closeWorkLable1 = new JLabel("CHIUSO"); closeWorkLable1.setOpaque(true); closeWorkLable1.setBackground(new Color(166,255,170));
		titlePanel.add(new JLabel("LISTA LAVORI"));
		//titlePanel.add(openWorkLable1); 
		//titlePanel.add(closeWorkLable1);
		
		
		titlePanel.setBackground(Color.ORANGE);
		SQLOperations sqlOper = new SQLOperations();		
		
		JPanel worksPanel = new JPanel(new BorderLayout());
				
		JTable table = new JTable();
		DefaultTableModel worksTableModel = sqlOper.getWorksList();
		
		table.setModel(worksTableModel);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		setTableColumnWidth(table);
		setRowHeight(table);
			
		for(int i=0; i<9; i++)table.getColumnModel().getColumn(i).setCellRenderer(new RendererWorks());
		
		WorkDetailsPanel workDetailsPanel = new WorkDetailsPanel(table, sqlOper);
		
		table.addMouseListener(new MouseOperations(workDetailsPanel));
		
		JScrollPane sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(800,400));
						
		worksPanel.add(sp, BorderLayout.CENTER);
		worksPanel.add(workDetailsPanel, BorderLayout.SOUTH);
		
		add(titlePanel, BorderLayout.NORTH);
		add(worksPanel, BorderLayout.CENTER);
		
		add(new FormNewWork3(table, sqlOper), BorderLayout.EAST);
		
	}//end constructor 	
	
	public void emptyFields(JPanel fields){
		Component []comps = fields.getComponents();
		for(int i=0; i<comps.length; i++){
			if((comps[i] instanceof JTextField)) {
		        JTextField myTextField = (JTextField) comps[i];   
		        myTextField.setBackground(Color.WHITE);
		        if(myTextField.getName()!="date") myTextField.setText("");
		      }
			else if((comps[i] instanceof JTextArea)){
				JTextArea myTextArea = (JTextArea) comps[i];
		        myTextArea.setText("");
			}
		}
	}//end emptyFields()
	
	public void setTableColumnWidth(JTable table){
		TableColumn column=null;
		for(int i=0; i<table.getColumnCount(); i++){
			column= table.getColumnModel().getColumn(i);
			switch(i){
				case 0: column.setMaxWidth(45); //id work	
				break;
				case 1: column.setMinWidth(150);
				break;
				case 2: 
					column.setMinWidth(300);
					//column.setMaxWidth(300);
				break;
				case 3: 
					column.setMinWidth(100);
					column.setMaxWidth(100);
				break;
				case 4: 
					column.setMinWidth(90);
					column.setMaxWidth(90);
				break;
				case 5: column.setMaxWidth(45);
				break;
				case 6: column.setMinWidth(75); column.setMaxWidth(75);  
				break;
				case 7: 
					column.setMaxWidth(35);
					column.setMinWidth(35);
				break;
				case 8: column.setMaxWidth(35);
				break;
			}
		}
	}//end setTableColumnWidth()

	public void setRowHeight(JTable table){
		for(int i=0; i<table.getRowCount();i++){
			table.setRowHeight(i, 25);
		}
	}
}//end class
