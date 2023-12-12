package manager;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.CardLayout;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Manager6 {
	public static int sqlOPerationCount=0;
	private JFrame frame;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//javax.swing.plaf.basic.
		
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		//FlatLightLaf.setup();
		
		System.out.println("01. PROGRAM STARTING");
		
		/*JWindow window = new JWindow();
	    window.getContentPane().add(new JLabel("Loading", SwingConstants.CENTER));
	    window.setBounds(500, 150, 300, 200);
	    window.setVisible(true);
	    try {
	      Thread.sleep(3000);
	    } catch (InterruptedException e) {
	    }
	    window.setVisible(false);
	    */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager6 window = new Manager6();
					/*try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (Exception e) {
						e.printStackTrace();
					} */
					window.frame.setVisible(true);
					window.frame.addKeyListener(new KeyListener() {
						
						@Override
						public void keyTyped(KeyEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void keyReleased(KeyEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void keyPressed(KeyEvent e) {
							System.out.println("key pressed "+ e.getKeyCode() + " " + e.getKeyChar());
							
						}
					});
					window.frame.setFocusable(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}//end PUBLIC STATIC VOID MAIN	

	/**
	 * Create the application.
	 */
	public Manager6() {
		JWindow window = new JWindow();
	    window.getContentPane().add(new JLabel("Loading", SwingConstants.CENTER));
	    window.setBounds(500, 150, 300, 200);
	    window.setVisible(true);
	    try {
	      Thread.sleep(1000);
	      initialize();
	    } catch (InterruptedException e) {
	    }
	    window.setVisible(false);
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	JButton []buttons= new JButton[5];
	CardLayout cl = new CardLayout(0,0);
	JPanel panel = new JPanel();
	JPanel logPanel = new JPanel();
	
	CassaPanel cassaPanel = new CassaPanel();
	StorePanel storePanel = new StorePanel();
	WelcomePanel welcomePanel = new WelcomePanel();
	
	private void initialize() {
		
		JButton customersButton = new JButton("CUSTOMERS");				customersButton.setBackground(Color.LIGHT_GRAY);
		JButton worksButton = new JButton("WORKS");						worksButton.setBackground(Color.LIGHT_GRAY);
		JButton cassaButton = new JButton("CASSA");						cassaButton.setBackground(Color.LIGHT_GRAY);
		JButton addCustomerButton = new JButton("NEW CUSTOMER");		addCustomerButton.setBackground(Color.LIGHT_GRAY);	
		JButton addUnitButton = new JButton("NEW UNIT");				addUnitButton.setBackground(Color.LIGHT_GRAY);			
		JButton addWorkButton = new JButton("NEW WORK");				addWorkButton.setBackground(Color.LIGHT_GRAY);		
		JButton storeButton = new JButton("STORE");						storeButton.setBackground(Color.LIGHT_GRAY);		

		buttons = new JButton[]{ customersButton, worksButton, cassaButton, addCustomerButton, addUnitButton, addWorkButton, storeButton}; 
		
		frame = new JFrame();
		frame.setBounds(0, 30, 1300, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("MANAGER");
		
//-------------------------------------------------------------------------------------------------------MENU----------------
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnCustomers = new JMenu("CUSTOMERS");
		menuBar.add(mnCustomers);
		
		JToolBar toolBar = new JToolBar();
		menuBar.add(toolBar);
		
//-------------------------------------------------------------------------------------------------------PANELS--------------
		
		logPanel.setLayout(new GridLayout());
		logPanel.setBackground(Color.GRAY);
		logPanel.add(new JLabel("LOG PANEL"));
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.getContentPane().add(logPanel, BorderLayout.SOUTH);
		panel.setLayout(cl);
		
		panel.add(welcomePanel, "welcome");
		panel.add(new CustomerPanel(), "CUSTOMERS");
		panel.add(new WorkPanel(), "WORKS");
		panel.add(cassaPanel, "CASSA");
		panel.add(storePanel, "STORE");
		
		for(int i=0; i<buttons.length; i++) toolBar.add(buttons[i]);
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		toolBar.add(new JLabel(" "+date.format(new Date())));
		
		customersButton.addActionListener(new ActiveButton());
		worksButton.addActionListener(new ActiveButton());
		cassaButton.addActionListener(new ActiveButton());
		addCustomerButton.addActionListener(new ActiveButton());
		addUnitButton.addActionListener(new ActiveButton());
		addWorkButton.addActionListener(new ActiveButton());
		storeButton.addActionListener(new ActiveButton());
		
	}//end init
	
	public class ActiveButton implements ActionListener{
		
		JButton tempBt;
		
		public void actionPerformed(ActionEvent e) {
		
			tempBt = (JButton)e.getSource();	
			for(int i=0; i<buttons.length; i++){
				if(buttons[i].getText()==tempBt.getText()){
					tempBt.setBackground(Color.WHITE);
					cl.show(panel, tempBt.getText());
				}
				else buttons[i].setBackground(Color.LIGHT_GRAY);
			}
			
			switch(tempBt.getText()){
				case "CASSA": cassaPanel.init(); break;
				case "STORE": storePanel.load(); break;
			}	
		}
		
	}//end class ActiveButton
	
}//end Manager 6
