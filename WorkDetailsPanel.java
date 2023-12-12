package manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WorkDetailsPanel extends JPanel{
	private static final long serialVersionUID = -2969031818044474844L;
	private JTextField idCustomer;
	private JTextField txtNoname;
	private JLabel lblNewLabel_1;
	private JTextField idUnit;
	private JTextField txtUnitname;
	private JLabel lblNewLabel_2;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JButton closeWorkButton, payWorkButton;
	private JTable table;
	
	public WorkDetailsPanel(JTable mainTable, SQLOperations sqlOper){
		
		setBorder(new TitledBorder(null, "WORK DETAILS", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		setLayout(null);
		setPreferredSize(new Dimension(770,130));
		setBackground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("CUSTOMER");
		lblNewLabel.setBounds(10, 20, 65, 20);
		add(lblNewLabel);//0
		
		idCustomer = new JTextField();
		idCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		idCustomer.setEditable(false);
		idCustomer.setBackground(new Color(255,255,158));
		idCustomer.setText("");
		idCustomer.setBounds(80, 20, 55, 20);
		add(idCustomer);//1
		idCustomer.setColumns(10);
		
		txtNoname = new JTextField();
		txtNoname.setBackground(new Color(255,255,158));
		txtNoname.setEditable(false);
		txtNoname.setBounds(140, 20, 150, 20);
		add(txtNoname);//2
		txtNoname.setColumns(10);
		
		lblNewLabel_1 = new JLabel("UNIT");
		lblNewLabel_1.setBackground(Color.GRAY);
		lblNewLabel_1.setBounds(10, 50, 90, 20);
		add(lblNewLabel_1);//3
		
		idUnit = new JTextField();
		idUnit.setHorizontalAlignment(SwingConstants.CENTER);
		idUnit.setEditable(false);
		idUnit.setBackground(new Color(255,255,158));
		idUnit.setText("");
		idUnit.setBounds(80, 50, 55, 20);
		add(idUnit);//4
		idUnit.setColumns(10);
		
		txtUnitname = new JTextField();
		txtUnitname.setEditable(false);
		txtUnitname.setBackground(new Color(255,255,158));
		txtUnitname.setBounds(140, 50, 235, 20);
		add(txtUnitname);//5
		txtUnitname.setColumns(10);
		
		lblNewLabel_2 = new JLabel("WORK");
		lblNewLabel_2.setBounds(10, 80, 46, 20);
		add(lblNewLabel_2);//6
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setText("");
		textField_2.setBackground(new Color(255,255,158));
		textField_2.setEditable(false);
		textField_2.setBounds(80, 80, 55, 20);
		add(textField_2);//7
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBackground(new Color(255,255,158));
		textField_3.setBounds(295, 20, 80, 20);
		add(textField_3);//8
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setEditable(false);
		textField_4.setBackground(new Color(255,255,158));
		textField_4.setBounds(140, 80, 86, 20);
		add(textField_4);//9
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_5.setBackground(Color.YELLOW);
		textField_5.setBounds(230, 80, 86, 20);
		add(textField_5);//10
		textField_5.setColumns(10);
		
		payWorkButton = new JButton("PAGA");
		payWorkButton.setBounds(810, 80, 75, 23);
		add(payWorkButton);//12
		payWorkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getRowCount()>0){
					
					String id = mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString();
					
					String[] options = {"Yes", "No"};
					int answ = JOptionPane.showOptionDialog(null, "PAY WORK?? ["+id+"]", "CLOSE", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,	options, options[0]);
					if(answ==0){
						sqlOper.payWork(id);
					}
					mainTable.setModel(sqlOper.getWorksList());
					for(int i=0; i<9; i++)mainTable.getColumnModel().getColumn(i).setCellRenderer(new RendererWorks());
					for(int i=0; i<mainTable.getRowCount();i++) mainTable.setRowHeight(i, 20);
					setTableColumnWidth(mainTable);
				}else {
					JOptionPane.showMessageDialog(null, "SELEZIONARE UN LAVORO", "ERRORE [001]", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}	
		});
		
		closeWorkButton = new JButton("CHIUDI");
		closeWorkButton.setBounds(810, 50, 75, 23);
		add(closeWorkButton);//12
		closeWorkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getRowCount()>0){
					
					String id = mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString();
					
					String[] options = {"Yes", "No"};
					int answ = JOptionPane.showOptionDialog(null, "CLOSE WORK?? ["+id+"]", "CLOSE", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,	options, options[0]);
					if(answ==0){
						sqlOper.closeWork(id);
					}
					mainTable.setModel(sqlOper.getWorksList());
					for(int i=0; i<9; i++)mainTable.getColumnModel().getColumn(i).setCellRenderer(new RendererWorks());
					for(int i=0; i<mainTable.getRowCount();i++) mainTable.setRowHeight(i, 20);
					setTableColumnWidth(mainTable);
				}else {
					JOptionPane.showMessageDialog(null, "SELEZIONARE UN LAVORO", "ERRORE [001]", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}	
		});
		
		JButton saveButton = new JButton("SALVA");
		saveButton.setBounds(810, 20, 75, 23);
		add(saveButton);//11
		
		
		
		DefaultTableModel model = new DefaultTableModel(new String[] { "CODE", "DESCRIPTION", "PRICE" }, 0);		
		table = new JTable(model);
		TableColumn column=null;
		for(int i=0; i<table.getColumnCount(); i++){
			column= table.getColumnModel().getColumn(i);
			switch(i){
				case 0: column.setMaxWidth(45); //id work	
				break;
				case 1: column.setMinWidth(250);
				break;
				case 2: 
					column.setMinWidth(50);
					column.setMaxWidth(50);
				break;
			}
		}
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(380, 20, 425, 103);
		add(scrollPane);//13
		
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str="";
				if(table.getRowCount()>0){
					
					for(int i=0; i<5; i++) if(table.getValueAt(i, 1)!="")str+=table.getValueAt(i, 1)+","; 	
					sqlOper.updateWork(textField_2.getText(),str, textField_5.getText());
					
					mainTable.setModel(sqlOper.getWorksList());
					for(int i=0; i<9; i++)mainTable.getColumnModel().getColumn(i).setCellRenderer(new RendererWorks());
					for(int i=0; i<mainTable.getRowCount();i++) mainTable.setRowHeight(i, 20);
					setTableColumnWidth(mainTable);
				}else {
					JOptionPane.showMessageDialog(null, "SELEZIONARE UN LAVORO", "ERRORE [001]", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
	}//end constructor
	
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
					column.setMinWidth(250);
					column.setMaxWidth(250);
				break;
				case 3: 
					column.setMinWidth(150);
					column.setMaxWidth(150);
				break;
				case 4: 
					column.setMinWidth(90);
					column.setMaxWidth(90);
				break;
				case 5: column.setMaxWidth(45);
				break;
				//case 6: column.setMaxWidth(45); break;
				case 7: 
					column.setMaxWidth(35);
					column.setMinWidth(35);
				break;
				case 8: column.setMaxWidth(35);
				break;
			}
		}
	}//end setTableColumnWidth()
	
}//end class
