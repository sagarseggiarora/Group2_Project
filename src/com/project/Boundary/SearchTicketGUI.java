package com.project.Boundary;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.project.Controller.Validations;
import com.project.Controller.WordWrapCellRenderer;
import com.project.Entity.Tickets_Group2;

import javax.swing.JButton;
import java.awt.ScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Point;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class SearchTicketGUI {

	private JFrame frame;
	private int searchNum = 0;
	private String searchEmail = "";
	private JTextField txtSearchNum;
	private JTextField txtSearchEmail;
	private JTable table;
	private static String userName = "";
	
	int i = 0;
	private UserTicketDAO uto = new UserTicketDAO();
	private static Validations v = new Validations();
	private DefaultTableModel tm;
	private ListSelectionListener lsl;
	private JLabel lblResult;

	/**
	 * Launch the application.
	 */
	public static void SearchTicket(String user) {
		userName=user;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchTicketGUI window = new SearchTicketGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * Setting the table model to show the result 
	 */
	
	private void addTableData()	{
		
		ArrayList<Tickets_Group2> tl = getSearchedTicket();
		
		if(tl.isEmpty()) {
			lblResult.setForeground(Color.red);
			lblResult.setText("No ticket found!");
			tm = new DefaultTableModel();
			table.setModel(tm);
		} 
		else {
			lblResult.setForeground(Color.green);
			lblResult.setText("Ticket found!");
			
			table.getSelectionModel().removeListSelectionListener(lsl);
			
			tm = new DefaultTableModel() {
				@Override
			    public boolean isCellEditable(int row, int column) {
			       return false;
			    }
			};
			
			tm.addColumn("Ticket Number");
			tm.addColumn("Email ID");
			tm.addColumn("Issue");
			tm.addColumn("Status");
			
			for (Tickets_Group2 t: tl)	{
				tm.addRow(t.getVector());
			}
			
			table.setModel(tm);
			
			table.setRowSorter(new TableRowSorter(tm));

			table.getSelectionModel().addListSelectionListener(lsl);
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getColumnModel().getColumn(0).setPreferredWidth(85);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setMinWidth(150);
			
			table.getColumnModel().getColumn(2).setCellRenderer(new WordWrapCellRenderer());
		}
		
	}
	
	public ArrayList<Tickets_Group2> getSearchedTicket() {
		if(i==1) {
			return uto.getTicketbyNumber(searchNum);
		} else if(i==2) {
			return uto.getTicketbyEmail(searchEmail);
		}
		return uto.getTickets();
	}

	/**
	 * Create the application.
	 */
	public SearchTicketGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 837, 483);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(133, 142, 462, 249);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		        	int currId = (int) table.getValueAt(table.getSelectedRow(),0); 
		   		    ViewSelectedTicketGUI vsg = new ViewSelectedTicketGUI();
		   		    vsg.ViewSelected(currId,userName);
		        }
		    }
		});
		
		txtSearchNum = new JTextField();
		txtSearchNum.setToolTipText("Enter the ticket number");
		txtSearchNum.setBounds(188, 13, 139, 22);
		frame.getContentPane().add(txtSearchNum);
		txtSearchNum.setColumns(10);
		
		lblResult = new JLabel("");
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblResult.setBounds(219, 97, 314, 37);
		frame.getContentPane().add(lblResult);
		
		/*
		 * Searching the ticket by ticket number
		 */
		
		JButton btnSearchByNum = new JButton("Search by Ticket Number");
		btnSearchByNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(v.isNotEmpty(txtSearchNum.getText()) && v.validateTicketNum(txtSearchNum.getText())) {
					searchNum = Integer.parseInt(txtSearchNum.getText());
					i=1;
					addTableData();
				} else {
					lblResult.setForeground(Color.red);
					lblResult.setText("Please enter a valid Ticket Number");
					tm = new DefaultTableModel();
					table.setModel(tm);
				}
			}
		});
		btnSearchByNum.setBounds(356, 12, 177, 25);
		frame.getContentPane().add(btnSearchByNum);
		
		txtSearchEmail = new JTextField();
		txtSearchEmail.setToolTipText("Enter the email address");
		txtSearchEmail.setColumns(10);
		txtSearchEmail.setBounds(188, 62, 139, 22);
		frame.getContentPane().add(txtSearchEmail);

		/*
		 * Searching the ticket by email
		 */
		
		JButton btnSearchByEmail = new JButton("Search by Email");
		btnSearchByEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(v.isNotEmpty(txtSearchEmail.getText()) && v.validateEmail(txtSearchEmail.getText())) {
					searchEmail = txtSearchEmail.getText();
					i=2;
					addTableData();
				} else {
					lblResult.setForeground(Color.red);
					lblResult.setText("Please enter a valid Email ID");
					tm = new DefaultTableModel();
					table.setModel(tm);
				}
			}
		});
		btnSearchByEmail.setBounds(356, 61, 177, 25);
		frame.getContentPane().add(btnSearchByEmail);
		
		JButton button = new JButton("<<GoBack");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				DashboardGUI dg = new DashboardGUI();
				dg.Dashboard(userName);
			}
		});
		button.setBounds(133, 400, 109, 23);
		frame.getContentPane().add(button);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LoginGUI lg = new LoginGUI();
				lg.main(null);
			}
		});
		btnLogout.setBounds(480, 400, 115, 23);
		frame.getContentPane().add(btnLogout);
		
		JButton btnRefresh = new JButton(new ImageIcon(getClass().getClassLoader().getResource("Images/refresh.png")));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addTableData();
			}
		});
		btnRefresh.setToolTipText("Refresh table");
		btnRefresh.setForeground(Color.BLACK);
		btnRefresh.setBorderPainted(false);
		btnRefresh.setBounds(562, 97, 32, 32);
		frame.getContentPane().add(btnRefresh);
		
	}
}
