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

import com.project.Boundary.UserDAO;
import com.project.Entity.User_Group2;
import javax.swing.JScrollPane;

public class UserGUI {

	private JFrame frame;
	private JTable table;
	
	private DefaultTableModel tm;
	//user DAO for CRUD operations
	private UserDAO uo = new UserDAO();
	private static User_Group2 u = new User_Group2();
	private ListSelectionListener lsl;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGUI window = new UserGUI();
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
		
		tm.addColumn("User ID");
		tm.addColumn("First Name");
		tm.addColumn("Last Name");
		tm.addColumn("Email");
		tm.addColumn("Address");
		tm.addColumn("Phone Number");
		
		ArrayList<User_Group2> ul = uo.getUsers();
		
		for (User_Group2 u: ul)	{
			System.out.println(u);
			tm.addRow(u.toVector());
		}
		
		table.setModel(tm);
		
		table.setRowSorter(new TableRowSorter(tm));

		table.getSelectionModel().addListSelectionListener(lsl);
	}
	

	/**
	 * Create the application.
	 */
	public UserGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 766, 496);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(408, 107, 321, 213);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	
		addTableData();
	}
}
