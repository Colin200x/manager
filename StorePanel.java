package manager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class StorePanel extends JPanel{

	private static final long serialVersionUID = 5855248889007759621L;

	public StorePanel(){
		setLayout(new BorderLayout());
		JPanel titlePanel = new JPanel();
		titlePanel.add(new JLabel("STORE"));
		titlePanel.setBackground(Color.ORANGE);
		add(titlePanel, BorderLayout.NORTH);
		
		DefaultTableModel model = new DefaultTableModel();
    	model.setColumnIdentifiers(new String[]{ "MODEL","LCD","ALTOPARLANTE","MAIN CAMERA","FRONT CAMERA","USB","BATTERIA","COVER BATT"});
    	model.addRow(new Object[]{"Galaxy J3 2017", "4", "x", "2", "x", "5", "2", null});
    	model.addRow(new Object[]{"Galaxy J5 2017", "4", "x", "2", "x", "5", "2", null});
    	model.addRow(new Object[]{"Galaxy J7 2017", "4", "x", "2", "x", "5", "2", null});
    	model.addRow(new Object[]{"Galaxy A3 2017", "4", "x", "2", "x", "5", "2", null});
    	model.addRow(new Object[]{"Galaxy A5 2017", "4", "x", "2", "x", "5", "2", null});
    	
		JTable tb = new JTable(model);
		
		tb.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
				System.out.println(tb.getValueAt(tb.getSelectedRow(), tb.getSelectedColumn()));
				
				
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JTree tree_1 = new JTree();
		tree_1.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("CELLULLARI") {
				{
					add(new DefaultMutableTreeNode("APPLE"));
					add(new DefaultMutableTreeNode("SAMSUNG"));
					add(new DefaultMutableTreeNode("HUAWEI"));
					add(new DefaultMutableTreeNode("XIAOMI"));
					add(new DefaultMutableTreeNode("OPPO"));
					add(new DefaultMutableTreeNode("LG"));
					add(new DefaultMutableTreeNode("MOTOROLA"));
				}
			}
		));
		tree_1.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				tree_1.getLastSelectedPathComponent();
				if(tree_1.getLastSelectedPathComponent().toString()=="APPLE") 
					tb.setModel(getTableModel(1));
				
				System.out.println(tree_1.getLastSelectedPathComponent());
				
			}
		});
		
		
		JScrollPane sp = new JScrollPane(tb);
		
		add(tree_1, BorderLayout.WEST);
		add(sp, BorderLayout.CENTER);
		
		System.out.println("store created");
	}//end constructor

	public DefaultTableModel getTableModel(int i){
		
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[]{ "MODEL","LCD","ALTOPARLANTE","MAIN CAMERA","FRONT CAMERA","USB","BATTERIA","COVER BATT"});
		
		model.addRow(new Object[]{"IPHONE 6", "4", "x", "2", "x", "5", "2", null});
    	model.addRow(new Object[]{"IPHONE 6S", "4", "x", "2", "x", "5", "2", null});
    	model.addRow(new Object[]{"IPHONE 6S+", "4", "x", "2", "x", "5", "2", null});
    	model.addRow(new Object[]{"IPHONE 7", "4", "x", "2", "x", "5", "2", null});
    	model.addRow(new Object[]{"IPHONE 8", "4", "x", "2", "x", "5", "2", null});
		
		return model;
	}
	
	public void load(){
		System.out.println("store loaded");
		
	}//end load
}
