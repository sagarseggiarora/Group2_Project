package com.project.Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DashboardGUI {
	

	private JFrame frame;
	private static String userName = "";

	/**
	 * Launch the application.
	 */
	public static void Dashboard(String user) {
		
		userName=user;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardGUI window = new DashboardGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DashboardGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 809, 411);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*
		 * Setting corresponding frames for all the buttons
		 */
		
		
		JButton btnCreateTicketExisting = new JButton("Create Ticket for \r\nExisting Customer");
		btnCreateTicketExisting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				ExistingCustGui eg = new ExistingCustGui();
				eg.Existing(userName);
			}
		});
		btnCreateTicketExisting.setBounds(31, 58, 313, 59);
		frame.getContentPane().add(btnCreateTicketExisting);
		
		JButton btnCreateTicketNew = new JButton("Create Ticket for \r\nNew Customer");
		btnCreateTicketNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				CreateNewGUI cg = new CreateNewGUI();
				cg.CreateNew(userName);
			}
		});
		btnCreateTicketNew.setBounds(422, 58, 321, 59);
		frame.getContentPane().add(btnCreateTicketNew);
		
		JButton btnViewAllTickets = new JButton("View All Tickets");
		btnViewAllTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ViewTicketsGUI vg = new ViewTicketsGUI();
				vg.ViewTickets(userName);
			}
		});
		btnViewAllTickets.setBounds(230, 157, 321, 59);
		frame.getContentPane().add(btnViewAllTickets);
		
		JButton btnSearch = new JButton("Search Ticket");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				SearchTicketGUI sg = new SearchTicketGUI();
				sg.SearchTicket(userName);
			}
		});
		btnSearch.setBounds(230, 252, 321, 59);
		frame.getContentPane().add(btnSearch);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				LoginGUI lg1 = new LoginGUI();
				lg1.main(null);
			}
		});
		btnLogout.setBounds(24, 327, 121, 23);
		frame.getContentPane().add(btnLogout);
		
		JButton btnStats = new JButton("STATS");
		btnStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				StatsGUI sg1 = new StatsGUI();
				sg1.Stats(userName);
			}
		});
		btnStats.setBounds(636, 326, 121, 23);
		frame.getContentPane().add(btnStats);
	}
}
