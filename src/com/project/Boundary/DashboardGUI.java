package com.project.Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DashboardGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void Dashboard() {
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
				ExistingCustGui eg = new ExistingCustGui();
				eg.Existing();
			}
		});
		btnCreateTicketExisting.setBounds(31, 58, 313, 59);
		frame.getContentPane().add(btnCreateTicketExisting);
		
		JButton btnCreateTicketNew = new JButton("Create Ticket for \r\nNew Customer");
		btnCreateTicketNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateNewGUI cg = new CreateNewGUI();
				cg.CreateNew();
			}
		});
		btnCreateTicketNew.setBounds(422, 58, 321, 59);
		frame.getContentPane().add(btnCreateTicketNew);
		
		JButton btnViewAllTickets = new JButton("View All Tickets");
		btnViewAllTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewTicketsGUI vg = new ViewTicketsGUI();
				vg.ViewTickets();
			}
		});
		btnViewAllTickets.setBounds(230, 157, 321, 59);
		frame.getContentPane().add(btnViewAllTickets);
		
		JButton btnSearch = new JButton("Search Ticket");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchTicketGUI sg = new SearchTicketGUI();
				sg.SearchTicket();
			}
		});
		btnSearch.setBounds(230, 252, 321, 59);
		frame.getContentPane().add(btnSearch);
	}
	
}
