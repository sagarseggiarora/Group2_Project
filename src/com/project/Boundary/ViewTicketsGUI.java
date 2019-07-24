package com.project.Boundary;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.project.Boundary.UserTicketDAO;
import com.project.Controller.WordWrapCellRenderer;
import com.project.Entity.Tickets_Group2;
import com.project.Entity.User_Group2;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class ViewTicketsGUI {

	private JFrame frame;
	private static String userName = "";
	private JTable table;
	ArrayList<Tickets_Group2> tl;
	
	private DefaultTableModel tm;
	
	private UserTicketDAO uto = new UserTicketDAO();
	private static Tickets_Group2 t = new Tickets_Group2();
	private ListSelectionListener lsl;
	
	/**
	 * Launch the application.
	 */
	public static void ViewTickets(String user) {
		userName=user;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTicketsGUI window = new ViewTicketsGUI();
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
		tm.addColumn("Date & Time");
		tm.addColumn("Added By");
		
		
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
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		
		table.getColumnModel().getColumn(2).setCellRenderer(new WordWrapCellRenderer());
	}
	

	/**
	 * Create the application.
	 */
	public ViewTicketsGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 848, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(97, 102, 647, 288);
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
		
		JLabel lblFilterBy = new JLabel("Filter by");
		lblFilterBy.setBounds(312, 42, 69, 20);
		frame.getContentPane().add(lblFilterBy);
		
		/*
		 * Get all the tickets and display them in the table 
		 */
		
		tl = uto.getTickets();
		addTableData();
		
		JComboBox comboStatus = new JComboBox();
		comboStatus.setBounds(379, 39, 121, 26);
		frame.getContentPane().add(comboStatus);
		comboStatus.addItem("All tickets");
		comboStatus.addItem("Open Tickets");
		comboStatus.addItem("Closed Tickets");
		
		JButton button = new JButton("<<GoBack");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				DashboardGUI dg = new DashboardGUI();
				dg.Dashboard(userName);
			}
		});
		button.setBounds(97, 416, 102, 23);
		frame.getContentPane().add(button);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LoginGUI lg = new LoginGUI();
				lg.main(null);
			}
		});
		btnLogout.setBounds(642, 416, 102, 23);
		frame.getContentPane().add(btnLogout);
		
		//JButton btnRefresh = new JButton("Refresh");
		JButton btnRefresh = new JButton(new ImageIcon(getClass().getClassLoader().getResource("Images/refresh.png")));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tl = uto.getTickets();
				addTableData();
			}
		});
		btnRefresh.setToolTipText("Refresh table");
		btnRefresh.setForeground(Color.BLACK);
		btnRefresh.setBounds(712, 39, 32, 32);
		btnRefresh.setBorderPainted(false);
		frame.getContentPane().add(btnRefresh);
		
		/*
		 * Add open/close filter for the table to view tickets with open/close status  
		 */
		
		comboStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(comboStatus.getSelectedItem()=="Open Tickets") {
					
					tl = uto.getOpenTickets();
					addTableData();
				}
				else if(comboStatus.getSelectedItem()=="Closed Tickets") {
					
					tl = uto.getCloseTickets();
					addTableData();
				} 
				else {
					tl = uto.getTickets();
					addTableData();
				}
				
			}
		});
		
		
		
	
	
	}
}
