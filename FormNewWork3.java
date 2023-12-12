package manager;




import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FormNewWork3 extends JPanel implements ItemListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1974606360366362937L;
	private JTextField customerPhone;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField unitName;
	private JTextField dateBeginField;
	private JTextField priceField;
	private JComboBox<String> unitVariants;
	private JTable table;
	private final int cols=10;
	private final int rows=10;
	
	private JCheckBox changeOSBox, puliziaPcBox, flashBox, frpBox;
	private SQLOperations sqlOper ;
	
	private DefaultTableModel model = new DefaultTableModel(cols, rows);
	private DefaultComboBoxModel<String> boxModel;
	
	private Customer customer;
	private int tempSum=0;
	
	public FormNewWork3(JTable mainTable, SQLOperations sqlOper) {
		this.sqlOper=sqlOper;
		
		setLayout(null);
		setPreferredSize(new Dimension(390,700));
		
		JLabel lblNewLabel = new JLabel("CELL:");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 10, 50, 20);
		add(lblNewLabel);
		
		customerPhone = new JTextField();
		customerPhone.setFont(new Font("Verdana", Font.PLAIN, 10));
		customerPhone.setText("");
		customerPhone.setBackground(new Color(248,230,230));
		customerPhone.setBounds(60, 10, 310, 20);
		add(customerPhone);
		customerPhone.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {

				JTextField tempField = (JTextField)e.getSource();
				customer = sqlOper.searchCustomer(tempField.getText(),0);
				Unit []units;
				boxModel = new DefaultComboBoxModel<String>();
				
				if(customer !=null){
					tempField.setBackground(new Color(166,255,170));
					nameField.setText(customer.getName());
					surnameField.setText(customer.getSurname());	
					units = sqlOper.getUnitList(customer.getID());
					boxModel.addElement("select unit");
					for(int i=0; i<units.length; i++) 
						if(units[i]!=null) boxModel.addElement(units[i].getUnitID()+" "+units[i].getUnitModel());	
					unitVariants.setEnabled(true);
					unitVariants.setModel(boxModel);
				} else {
					System.out.println("NO PHONE");
					tempField.setBackground(new Color(248,230,230));
					//emptyFields();
				}	
			}

			@Override
			public void focusGained(FocusEvent e) { }
		});

		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(10, 40, 50, 20);
		add(lblNewLabel_1);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Verdana", Font.PLAIN, 10));
		nameField.setBackground(new Color(248,230,230));
		nameField.setText("");
		nameField.setBounds(60, 40, 100, 20);
		add(nameField);
		nameField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				JTextField nameF = (JTextField) e.getSource();
				System.out.println(nameF.getText());
				
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		/*nameField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {

				JTextField tempField = (JTextField)e.getSource();
				customer = sqlOper.searchCustomer(tempField.getText(),1);
				Unit []units;
				boxModel = new DefaultComboBoxModel<String>();
				
				if(customer !=null){
					tempField.setBackground(new Color(166,255,170));
					customerPhone.setText(customer.getPhone());
					surnameField.setText(customer.getSurname());	
					units = sqlOper.getUnitList(customer.getID());
					boxModel.addElement("select unit");
					for(int i=0; i<units.length; i++) if(units[i]!=null) boxModel.addElement(units[i].getUnitID()+" "+units[i].getUnitModel());	
					unitVariants.setEnabled(true);
					unitVariants.setModel(boxModel);
				} else {
					System.out.println("ANY NAME");
					tempField.setBackground(new Color(248,230,230));
					//emptyFields();
				}	
			}

			@Override
			public void focusGained(FocusEvent e) { }
		});
		*/
		
		JLabel lblNewLabel_2 = new JLabel("Unit\u00E0:");
		lblNewLabel_2.setBounds(10, 70, 46, 20);
		add(lblNewLabel_2);
		
		unitName = new JTextField();
		unitName.setFont(new Font("Verdana", Font.PLAIN, 10));
		unitName.setBounds(60, 70, 310, 20);
		add(unitName);

		
		unitVariants = new JComboBox<String>();
		unitVariants.setFont(new Font("Verdana", Font.PLAIN, 10));
		unitVariants.setEnabled(false);
		unitVariants.setBounds(60, 92, 310, 22);
		
		add(unitVariants);
		
		JLabel lblNewLabel_3 = new JLabel("Data:");
		lblNewLabel_3.setBounds(10, 120, 50, 20);
		add(lblNewLabel_3);
		
		dateBeginField = new JTextField();
		dateBeginField.setName("data");
		dateBeginField.setFont(new Font("Verdana", Font.PLAIN, 10));
		SimpleDateFormat data = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		dateBeginField.setText(data.format(new Date()));
		dateBeginField.setBounds(60, 120, 115, 20);
		add(dateBeginField);
		
		JLabel lblNewLabel_4 = new JLabel("Prezzo tot:");
		lblNewLabel_4.setBounds(195, 120, 65, 20);
		add(lblNewLabel_4);
		
		priceField = new JTextField("0");
		priceField.setName("price");
		priceField.setFont(new Font("Verdana", Font.PLAIN, 10));
		
		priceField.setHorizontalAlignment(SwingConstants.RIGHT);
		priceField.setBounds(260, 120, 110, 20);
		add(priceField);
		
		changeOSBox = new JCheckBox("Cambio OS");
		changeOSBox.addItemListener(this);
		changeOSBox.setFont(new Font("Verdana", Font.PLAIN, 10));
		changeOSBox.setBounds(10, 375, 90, 23);
		add(changeOSBox);
		
		puliziaPcBox = new JCheckBox("Pulizia PC");
		puliziaPcBox.setFont(new Font("Verdana", Font.PLAIN, 10));
		puliziaPcBox.setBounds(102, 375, 90, 23);
		puliziaPcBox.addItemListener(this);
		add(puliziaPcBox);
		
		flashBox = new JCheckBox("Flash");
		flashBox.setFont(new Font("Verdana", Font.PLAIN, 10));
		flashBox.setBounds(194, 375, 60, 23);
		flashBox.addItemListener(this);
		add(flashBox);
		
		frpBox = new JCheckBox("FRP");
		frpBox.setFont(new Font("Verdana", Font.PLAIN, 10));
		frpBox.setBounds(256, 375, 60, 23);
		frpBox.addItemListener(this);
		add(frpBox);
		
		JButton salvaButton = new JButton("ADD", new ImageIcon("add15.png"));
		salvaButton.setBackground(Color.WHITE);
		salvaButton.setBounds(10, 402, 95, 23);
		add(salvaButton);
		salvaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String workDetails="", device1="", prices="";
				
				workDetails+=table.getValueAt(0, 1)+",";
				for(int i=1; i<10; i++) {
					String str = (String)table.getValueAt(i, 0);
					if(str!=null) {
						if(Integer.parseInt(str)>0)	workDetails+=table.getValueAt(i, 0)+",";
						else if(Integer.parseInt(str)==0)workDetails+=table.getValueAt(i, 1)+",";
						prices += (String)table.getValueAt(i, 2)+","; 
					}
				}				
				device1 = unitName.getText();
				prices+=priceField.getText();
				/*
					[0] - customer ID
			    	[1] - customer phone
			    	[2] - customer name
			    	[3] - customer surname
			    	[4] - unit ID
			    	[5] - unit
			    	[6] - (int)work prices
			    	[7] - work details
			    	[8] - work begin date
			    */
				String[] work = new String[9];
					if(customer==null)work[0]="0";else work[0] = customer.getID()+"";
					work[1] = customerPhone.getText();
					work[2] = nameField.getText();
					work[3] = surnameField.getText();
					if(device1.equals("") && unitVariants.getSelectedItem()!=null) {
						String[] tempString=unitVariants.getSelectedItem().toString().split(" ", 2);
						work[4] = tempString[0];
						work[5] = tempString[1];
					}else{
						work[4]=0+"";
						work[5]= device1;
					}
					work[6] = prices;
					work[7] = workDetails;
					work[8] = dateBeginField.getText(); 
				
				//for(int i=0; i<work.length; i++)System.out.println(work[i]);
				sqlOper.insertWork(work);
				
				mainTable.setModel(sqlOper.getWorksList());
				for(int i=0; i<9; i++)mainTable.getColumnModel().getColumn(i).setCellRenderer(new RendererWorks());
				for(int i=0; i<mainTable.getRowCount();i++) mainTable.setRowHeight(i, 20);
				setTableColumnWidth(mainTable);
				
				emptyFields();
				
			}
		});
	
		JButton svuotaButton = new JButton("EMPTY", new ImageIcon("empty.png"));
		svuotaButton.setBackground(Color.WHITE);
		svuotaButton.setBounds(109, 402, 100, 23);
		add(svuotaButton);
		svuotaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				emptyFields();
				
			}
		});
		
		JButton listButton = new JButton("LISTA");
		listButton.setBounds(214, 402, 100, 23);
		listButton.setEnabled(false);
		add(listButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 150, 360, 190);
		add(scrollPane);
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] { "ID", "Descrizione", "Prezzo" });
		for(int i=0; i<cols; i++) model.addRow(new String[]{null, null, null});
		table = new JTable();
		table.setModel(model);
		
		
		table.getColumnModel().getColumn(0).setCellRenderer(new StandartWorkTableRenderer());
		table.getColumnModel().getColumn(1).setCellRenderer(new StandartWorkTableRenderer());
		table.getColumnModel().getColumn(2).setCellRenderer(new StandartWorkTableRenderer());
		
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(0).setMinWidth(30);
		table.getColumnModel().getColumn(0).setMaxWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setMinWidth(50);
		table.getColumnModel().getColumn(2).setMaxWidth(50);
		scrollPane.setViewportView(table);
		
		JCheckBox lav0Box = new JCheckBox("lavoro 0");
		lav0Box.setBounds(10, 347, 80, 23);
		add(lav0Box);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("lavoro 1");
		chckbxNewCheckBox_1.setBounds(102, 347, 90, 23);
		add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("lavoro 2");
		chckbxNewCheckBox_2.setBounds(194, 347, 60, 23);
		add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("lavoro 3");
		chckbxNewCheckBox_3.setBounds(256, 347, 97, 23);
		add(chckbxNewCheckBox_3);
		
		JLabel lblNewLabel_5 = new JLabel("Cognome:");
		lblNewLabel_5.setBounds(170, 40, 60, 20);
		add(lblNewLabel_5);
		
		surnameField = new JTextField();
		surnameField.setBounds(230, 40, 140, 20);
		add(surnameField);
		surnameField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		
		JTextArea lbl123 = new JTextArea(sqlOper.getStandardWorkList());
		lbl123.setFont(new Font("Verdana", Font.ITALIC, 10));
		panel.add(lbl123);
		panel.setBounds(10, 430, 360, 150);
		add(panel);
	}//end constructor

	@Override
	public void itemStateChanged(ItemEvent e) {
		tempSum = Integer.parseInt(priceField.getText());		
        Object source = e.getItemSelectable();
        
        
        
        if (e.getStateChange() == ItemEvent.SELECTED) {
        	if(source == changeOSBox) {
        		table.setModel(addRowToTable(model,new String[] {"1","Cambio OS","20"}));
        		tempSum+=20;
        	}
        	if(source == puliziaPcBox) {
        		table.setModel(addRowToTable(model,new String[] {"2","Pulizia PC","30"}));
        		tempSum+=30;
        	}
        	if(source == flashBox) {
        		table.setModel(addRowToTable(model,new String[] {"3","Flash cell","20"}));
        		tempSum+=20;
        	}
        	if(source == frpBox) {
        		table.setModel(addRowToTable(model,new String[] {"4","FRP Device","20"}));
        		tempSum+=20;
        	}
        }
     
        if (e.getStateChange() == ItemEvent.DESELECTED) {
        	if(source == changeOSBox) {
        		table.setModel(cutRowFromTable(model, "1"));
        		if(tempSum>0)tempSum-=20;
        		
        	}
        	if(source == puliziaPcBox) {
        		table.setModel(cutRowFromTable(model, "2"));
        		if(tempSum>0)tempSum-=30;
        	}
        	if(source == flashBox) {
        		table.setModel(cutRowFromTable(model, "3"));
        		if(tempSum>0)tempSum-=20;
        	}
        	if(source == frpBox) {
        		table.setModel(cutRowFromTable(model, "4"));
        		if(tempSum>0)tempSum-=20;
        	}
        }
        priceField.setText(tempSum+"");
    	
	}//end itemStateChange()
	
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
	
	public DefaultTableModel addRowToTable(DefaultTableModel md, String[] str) {
		
		for(int i=0; i<cols; i++) {
			if((String)md.getValueAt(i, 0)==null) {
				md.setValueAt(str[0], i, 0);
				md.setValueAt(str[1], i, 1);
				md.setValueAt(str[2], i, 2);
				break;
			}
		}
		return md;
	}//end setTableModel()
	
	public DefaultTableModel cutRowFromTable(DefaultTableModel md, String id) {
		//String str="";
		for(int i=0; i<md.getRowCount(); i++) {
			
			if((String)md.getValueAt(i, 0)==id) {
				md.removeRow(i);
				md.addRow(new String[] {null,null,null});
			}
		}
		
		
		return md;
	}//end setTableModel()
	
	public void emptyFields(){
		
		Component []comps = this.getComponents();
		for(int i=0; i<comps.length; i++){
			if((comps[i] instanceof JTextField)) {		
				JTextField myTextField = (JTextField) comps[i];				
		        myTextField.setBackground(Color.WHITE);	        
		        if(myTextField.getName()!="data") myTextField.setText("");
		        if(myTextField.getName()=="price") myTextField.setText("0");
		      }
			if((comps[i] instanceof JCheckBox)) {
				JCheckBox tempBox = (JCheckBox)comps[i];
				tempBox.setSelected(false);
			}
			if((comps[i] instanceof JComboBox)) {
				JComboBox<String> tempBox = (JComboBox<String>)comps[i];
				tempBox.setModel(new DefaultComboBoxModel<String>());
				tempBox.setEnabled(false);
			}
			if((comps[i] instanceof JTable)) {
				JScrollPane sp = (JScrollPane) comps[i];
				Component []spComps = sp.getComponents();
				JViewport view = (JViewport) spComps[0];
				JTable tb = (JTable)view.getView();
							
				tb.setModel(model);
			}
		}
	}//end emptyFields()
}//end class
