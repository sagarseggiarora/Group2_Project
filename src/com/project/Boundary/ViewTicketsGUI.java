package com.project.Boundary;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ViewTicketsGUI {

	private JFrame frame;
	private JTable table;
	ArrayList<Tickets_Group2> tl;
	
	private DefaultTableModel tm;
	
	private UserTicketDAO uto = new UserTicketDAO();
	private static Tickets_Group2 t = new Tickets_Group2();
	private ListSelectionListener lsl;
	
	/**
	 * Launch the application.
	 */
	public static void ViewTickets() {
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
		
		tm = new DefaultTableModel();
		
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
		frame.setBounds(100, 100, 600, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		lsl = new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				//Get the column value of the first column of the selected row
				int currId = (int) table.getValueAt(table.getSelectedRow(),0);

				ViewSelectedTicketGUI vsg = new ViewSelectedTicketGUI();
				vsg.ViewSelected(currId);
			}
			};
			
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(53, 73, 465, 288);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JLabel lblFilterBy = new JLabel("Filter by");
		lblFilterBy.setBounds(53, 16, 69, 20);
		frame.getContentPane().add(lblFilterBy);
		
		/*
		 * Get all the tickets and display them in the table 
		 */
		
		tl = uto.getTickets();
		addTableData();
		
		JComboBox comboStatus = new JComboBox();
		comboStatus.setBounds(137, 13, 69, 26);
		frame.getContentPane().add(comboStatus);
		comboStatus.addItem("Select");
		comboStatus.addItem("Open");
		comboStatus.addItem("Close");
		
		/*
		 * Add open/close filter for the table to view tickets with open/close status  
		 */
		
		comboStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(comboStatus.getSelectedItem()=="Open") {
					
					tl = uto.getOpenTickets();
					addTableData();
				}
				else if(comboStatus.getSelectedItem()=="Close") {
					
					tl = uto.getCloseTickets();
					addTableData();
				}
				
			}
		});
		
		
		
	
	
	}
}
