package com.project.Boundary;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.project.Boundary.UserTicketDAO;
import com.project.Controller.WordWrapCellRenderer;
import com.project.Entity.Tickets_Group2;
import com.project.Entity.User_Group2;
import javax.swing.JScrollPane;

public class ViewTicketsGUI {

	private JFrame frame;
	private JTable table;
	
	private DefaultTableModel tm;
	
	private UserTicketDAO uto = new UserTicketDAO();
	private static Tickets_Group2 t = new Tickets_Group2();
	private ListSelectionListener lsl;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	
	
	private void addTableData()	{
		
		table.getSelectionModel().removeListSelectionListener(lsl);
		
		tm = new DefaultTableModel();
		
		tm.addColumn("Ticket Number");
		tm.addColumn("Email ID");
		tm.addColumn("Issue");
		
		ArrayList<Tickets_Group2> tl = uto.getTickets();
		
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 13, 408, 227);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	
		addTableData();
	}
}
