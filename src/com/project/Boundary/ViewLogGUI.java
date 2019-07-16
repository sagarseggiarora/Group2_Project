package com.project.Boundary;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.project.Controller.WordWrapCellRenderer;
import com.project.Entity.Logs_Group2;


public class ViewLogGUI {

	private JFrame frame;
	private static String ticketNum = "";
	private JTable table;
	ArrayList<Logs_Group2> logList;
	
	private DefaultTableModel tm;
	private ListSelectionListener lsl;
	
	private UserTicketDAO uto = new UserTicketDAO();
	private Logs_Group2 log  = new Logs_Group2();

	/**
	 * Launch the application.
	 */
	public static void ViewLog(String ticket) {
		ticketNum=ticket;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewLogGUI window = new ViewLogGUI();
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
		
		tm.addColumn("Log ID");
		tm.addColumn("Comments");
		tm.addColumn("Date");
		tm.addColumn("Submitted By");
		
		
		
		for (Logs_Group2 log: logList)	{
			tm.addRow(log.getVector());
		}
		
		table.setModel(tm);
		
		table.setRowSorter(new TableRowSorter(tm));

		table.getSelectionModel().addListSelectionListener(lsl);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(1).setMinWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		
		table.getColumnModel().getColumn(1).setCellRenderer(new WordWrapCellRenderer());
	}
	

	/**
	 * Create the application.
	 */
	public ViewLogGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 687, 418);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTicket = new JLabel("Ticket#");
		lblTicket.setBounds(253, 16, 163, 20);
		frame.getContentPane().add(lblTicket);
		
		lblTicket.setText("Ticket Number is "+ticketNum);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(53, 73, 465, 288);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		logList=uto.getLogs(ticketNum);
		addTableData();
		
	}

}
