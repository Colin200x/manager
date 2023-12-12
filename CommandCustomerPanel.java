package manager;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CommandCustomerPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 583618839259443887L;
	
	private JTextField idField;
	private JTextField nameField;
	private JTextField surnameField;
	private JLabel phoneLabel;
	private JTextField phoneField;
	private JTextField phone2Field;
	private JTextField phone3Field;
	private JTextField mailField;
	private JTable unitTable;
	private JTable worksTable;

	private UnitShortTableModel unitModel = new UnitShortTableModel();
	private WorkShortTableModel workModel = new WorkShortTableModel();
	
	private SQLOperations op = new SQLOperations();
	public CommandCustomerPanel() {
		
		setLayout(null);
		setPreferredSize(new Dimension(480,500));
		
		JLabel idLabel = new JLabel("ID:");
		idLabel.setBounds(10, 10, 25, 20);
		add(idLabel);
		
		idField = new JTextField();
		idField.setEditable(false);
		idField.setFont(new Font("Verdana", Font.PLAIN, 10));
		idField.setHorizontalAlignment(SwingConstants.CENTER);
		idField.setBounds(40, 10, 50, 20);
		add(idField);
		idField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Verdana", Font.PLAIN, 10));
		nameField.setBounds(160, 10, 150, 20);
		add(nameField);
		nameField.setColumns(10);
		
		surnameField = new JTextField();
		surnameField.setFont(new Font("Verdana", Font.PLAIN, 10));
		surnameField.setBounds(320, 10, 150, 20);
		add(surnameField);
		surnameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Cliente:");
		lblNewLabel.setBounds(115, 10, 46, 20);
		add(lblNewLabel);
		
		phoneLabel = new JLabel("Contacts:");
		phoneLabel.setBounds(10, 40, 60, 20);
		add(phoneLabel);
		
		phoneField = new JTextField();
		phoneField.setEditable(false);
		phoneField.setFont(new Font("Verdana", Font.PLAIN, 10));
		phoneField.setHorizontalAlignment(SwingConstants.CENTER);
		phoneField.setBounds(70, 40, 80, 20);
		add(phoneField);
		phoneField.setColumns(10);
		
		phone2Field = new JTextField();
		phone2Field.setBounds(160, 40, 80, 20);
		add(phone2Field);
		phone2Field.setColumns(10);
		
		phone3Field = new JTextField();
		phone3Field.setFont(new Font("Verdana", Font.PLAIN, 10));
		phone3Field.setBounds(250, 40, 80, 20);
		add(phone3Field);
		phone3Field.setColumns(10);
		
		mailField = new JTextField();
		mailField.setFont(new Font("Verdana", Font.PLAIN, 10));
		mailField.setBounds(340, 40, 130, 20);
		add(mailField);
		mailField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 70, 460, 2);
		add(separator);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 80, 460, 100);
		add(scrollPane_1);
		
		unitTable = new JTable();
		unitTable.setFont(new Font("Verdana", Font.PLAIN, 10));
		unitTable.setShowGrid(false);
		unitTable.setModel(unitModel);
		unitTable.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 1) {
			      JTable tb = (JTable)e.getSource();
			      int row = tb.getSelectedRow();  
			    
			      Work[] work = op.getWorksList(tb.getValueAt(row, 0).toString());
			      
			      WorkDetail[] workDetails = op.getWorksDetailList(work);
			      
			      worksTable.setModel(workModel.getShortModel(work, workDetails, op));
			    }
			  }
			});
		
		unitTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		unitTable.getColumnModel().getColumn(0).setMinWidth(30);
		unitTable.getColumnModel().getColumn(0).setMaxWidth(30);
		unitTable.getColumnModel().getColumn(2).setPreferredWidth(130);
		unitTable.getColumnModel().getColumn(2).setMinWidth(30);
		unitTable.getColumnModel().getColumn(2).setMaxWidth(1000000);
		
		scrollPane_1.setViewportView(unitTable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 190, 460, 189);
		add(scrollPane);
		
		worksTable = new JTable();
		worksTable.setFont(new Font("Verdana", Font.PLAIN, 10));
		worksTable.setShowGrid(false);
		worksTable.setModel(workModel);
		
		worksTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		worksTable.getColumnModel().getColumn(0).setMinWidth(30);
		worksTable.getColumnModel().getColumn(0).setMaxWidth(30);
		worksTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		worksTable.getColumnModel().getColumn(2).setMinWidth(50);
		worksTable.getColumnModel().getColumn(2).setMaxWidth(50);
		worksTable.getColumnModel().getColumn(3).setPreferredWidth(90);
		worksTable.getColumnModel().getColumn(3).setMinWidth(90);
		worksTable.getColumnModel().getColumn(3).setMaxWidth(90);
		worksTable.getColumnModel().getColumn(4).setPreferredWidth(30);
		worksTable.getColumnModel().getColumn(4).setMinWidth(30);
		worksTable.getColumnModel().getColumn(4).setMaxWidth(30);
		scrollPane.setViewportView(worksTable);
		
		JButton addButton = new JButton("ADD");
		addButton.setEnabled(false);
		addButton.setBounds(10, 405, 89, 23);
		add(addButton);
		
		JButton updateButton = new JButton("UPDATE");
		updateButton.setEnabled(false);
		updateButton.setBounds(115, 405, 89, 23);
		add(updateButton);
		
		JButton deleteButton = new JButton("DELETE");
		deleteButton.setEnabled(false);
		deleteButton.setBounds(215, 405, 89, 23);
		add(deleteButton);
		
		JButton refreshButton = new JButton("REFRESH");
		refreshButton.setEnabled(false);
		refreshButton.setBounds(320, 405, 89, 23);
		add(refreshButton);
	}//end constructor
	
	public void init(Customer customer, JTable mainTable) {
		idField.setText(customer.getID()+"");
		nameField.setText(customer.getName());
		surnameField.setText(customer.getSurname());
		phoneField.setText(customer.getPhone());
		phone2Field.setText(customer.getPhone2());
		phone3Field.setText(customer.getPhone3());
		mailField.setText(customer.getEMail());
		
		Unit[] devices = op.getUnitList(customer.getID());
		unitTable.setModel(unitModel.getShortModel(devices));
		
		workModel.setRowCount(0);
		
	}//end init()
	
}//end class
