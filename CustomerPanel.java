package manager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;





public class CustomerPanel extends JPanel {

	private static final long serialVersionUID = -6890686038131659683L;
	JLabel totalLb = new JLabel();
	Customer customer;
	CommandCustomerPanel commandCustomerPanel = new CommandCustomerPanel();  
	
	public CustomerPanel(){
		setLayout(new BorderLayout());
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		titlePanel.add(new JLabel("CUSTOMERS:"));
		titlePanel.setBackground(Color.CYAN);
		titlePanel.add(totalLb);
		
		JPanel customerDetailsPanel = new JPanel();

		customerDetailsPanel.add(new JLabel("CUSTMOMERS DETAIL:"));
		JTextArea customerDetailsArea = new JTextArea(5,30);
		customerDetailsPanel.add(customerDetailsArea);
		
			
		SQLOperations op = new SQLOperations();
		
		JTable customersTable = new JTable();
		customersTable.setShowGrid(false);
		DefaultTableModel customerModel = new SQLOperations().getCustomerList();
		customersTable.setModel(customerModel);
		totalLb.setText(customerModel.getRowCount()+"");
		
		for(int i=0; i<7; i++)customersTable.getColumnModel().getColumn(i).setCellRenderer(new Renderer());
		customersTable.getColumn("COMMAND").setCellRenderer( new ButtonRenderer() );
		customersTable.getColumn("COMMAND").setCellEditor( new ButtonEditor(new JCheckBox()));
		customersTable.setRowHeight(30);
		
		customersTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		customersTable.getColumnModel().getColumn(0).setMinWidth(40);
		customersTable.getColumnModel().getColumn(0).setMaxWidth(40);
		
		
		
		
		
		customersTable.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 1) {
			      JTable tb = (JTable)e.getSource();
			      int row = tb.getSelectedRow();  
			      customerDetailsArea.setText(op.getUnitList(tb.getValueAt(row, 0).toString()));
			      
			      customer = op.searchCustomer(tb.getValueAt(row, 3).toString(), 0);
			      commandCustomerPanel.init(customer, customersTable);
			    }
			  }
			});
		
		
		JScrollPane sp = new JScrollPane(customersTable);
		
		//sp.setPreferredSize(new Dimension(600,400));
		
		/*
		 * JPanel commandPanel = new JPanel(new GridLayout(10,1));
		 * commandPanel.setPreferredSize(new Dimension(500,400));
		
			JTextField customerNameField = new JTextField("name",10);
			JTextField customerSurnameField = new JTextField("surname",10);
			JTextField customerPhoneField = new JTextField("phone",10);
			JTextField customerEMailField = new JTextField("email",10);
			JTextField customerPhone2Field = new JTextField("12345",10);
			JTextField customerPhone3Field = new JTextField("54321",10);
			totalLb = new JLabel("TOTAL CUSTOMERS: "+SQLOperations.customerCount);
			JButton addCustomerBt = new JButton("ADD CUSTOMER");
			JButton refreshCustomerBt = new JButton("REFRESH");
			
			commandPanel.add(customerNameField); 
			commandPanel.add(customerSurnameField);
			commandPanel.add(customerPhoneField);
			commandPanel.add(customerEMailField);
			commandPanel.add(customerPhone2Field);
			commandPanel.add(customerPhone3Field);
			commandPanel.add(addCustomerBt);
			commandPanel.add(refreshCustomerBt);
			commandPanel.add(totalLb);
			
			refreshCustomerBt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					customersTable.setModel(op.getCustomerList());
					
				}
			});
			
			addCustomerBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		customer[0]=customerNameField.getText();
        		customer[1]=customerSurnameField.getText();
        		customer[2]=customerPhoneField.getText();
        		customer[3]=customerEMailField.getText();
        		customer[4]=customerPhone2Field.getText();
        		customer[5]=customerPhone3Field.getText();
				
				String temp = op.insertNewCustomer(customer);
				if(temp !="") JOptionPane.showMessageDialog(new JFrame(), temp, "QUERY ERROR!", JOptionPane.ERROR_MESSAGE);
				else customersTable.setModel(op.getCustomerList());
			}
		});
		 */
		add(sp, BorderLayout.CENTER);
		add(commandCustomerPanel, BorderLayout.EAST);
		add(titlePanel, BorderLayout.NORTH);
		add(customerDetailsPanel, BorderLayout.SOUTH);
		
	}//end constructor 	

}
