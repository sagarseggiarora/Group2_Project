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
	public static void main(String[] args) {
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
		
		JButton btnCreateTicketExisting = new JButton("Create Ticket for \r\nExisting Customer");
		btnCreateTicketExisting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//create ticket for Existing page
			}
		});
		btnCreateTicketExisting.setBounds(209, 35, 354, 59);
		frame.getContentPane().add(btnCreateTicketExisting);
		
		JButton btnCreateTicketNew = new JButton("Create Ticket for \r\nNew Customer");
		btnCreateTicketNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateNewGUI.main(null);
			}
		});
		btnCreateTicketNew.setBounds(209, 130, 354, 59);
		frame.getContentPane().add(btnCreateTicketNew);
		
		JButton btnViewAllTickets = new JButton("View All Tickets");
		btnViewAllTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewTicketsGUI.main(null);
			}
		});
		btnViewAllTickets.setBounds(209, 229, 354, 59);
		frame.getContentPane().add(btnViewAllTickets);
	}
}