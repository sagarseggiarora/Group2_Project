package com.project.Boundary;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.project.Controller.Validations;
import com.project.Entity.Tickets_Group2;
import com.project.Entity.User_Group2;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class StatsGUI {

	private JFrame frame;
	private JTextField txtEmail;
	private static String userName = "";
	private JTextField txtDate;
	private static Validations v = new Validations();
	UserTicketDAO dao = new UserTicketDAO();
	int count = 0;

	/**
	 * Launch the application.
	 */
	public static void Stats(String user) {
		userName=user;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatsGUI window = new StatsGUI();
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
	public StatsGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 804, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(402, 27, 186, 31);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblUser = new JLabel("Number of issues submitted by user (email id):");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUser.setBounds(12, 27, 388, 24);
		frame.getContentPane().add(lblUser);
		
		JLabel lblResult = new JLabel("");
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblResult.setBounds(537, 184, 73, 94);
		lblResult.setForeground(Color.blue);
		frame.getContentPane().add(lblResult);
		
		JLabel lblDate = new JLabel("Number of issues submitted on (yyyy/mm/dd):");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDate.setBounds(12, 75, 388, 24);
		frame.getContentPane().add(lblDate);
		
		JButton btnBack = new JButton("<<GoBack");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				DashboardGUI dg = new DashboardGUI();
				dg.Dashboard(userName);
			}
		});
		btnBack.setBounds(12, 337, 102, 23);
		frame.getContentPane().add(btnBack);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LoginGUI lg = new LoginGUI();
				lg.main(null);
			}
		});
		btnLogout.setBounds(672, 336, 102, 23);
		frame.getContentPane().add(btnLogout);
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(402, 74, 186, 31);
		frame.getContentPane().add(txtDate);
		
		JLabel lblNumberOfIssues = new JLabel("Number of issues created by admin (username):");
		lblNumberOfIssues.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNumberOfIssues.setBounds(12, 126, 388, 24);
		frame.getContentPane().add(lblNumberOfIssues);
		
		JLabel lblTotalNumberOf = new JLabel("Total number of tickets:");
		lblTotalNumberOf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotalNumberOf.setBounds(12, 174, 297, 24);
		frame.getContentPane().add(lblTotalNumberOf);
		
		JLabel lblTotalNumberOf_1 = new JLabel("Total number of open tickets:");
		lblTotalNumberOf_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotalNumberOf_1.setBounds(12, 222, 283, 24);
		frame.getContentPane().add(lblTotalNumberOf_1);
		
		JLabel lblTotalNumberOf_2 = new JLabel("Total number of closed tickets:");
		lblTotalNumberOf_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotalNumberOf_2.setBounds(12, 266, 276, 24);
		frame.getContentPane().add(lblTotalNumberOf_2);
		
		JComboBox cbAdmin = new JComboBox();
		cbAdmin.setBounds(402, 129, 186, 22);
		frame.getContentPane().add(cbAdmin);
		cbAdmin.addItem("test");
		cbAdmin.addItem("Dash");
		cbAdmin.addItem("Sagar");
		cbAdmin.addItem("Parth");
		
		JLabel lblError = new JLabel("");
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblError.setBounds(237, 329, 389, 31);
		lblError.setForeground(Color.red);
		frame.getContentPane().add(lblError);
		
		JButton btnCountEmail = new JButton("Count");
		btnCountEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(v.validateEmail(txtEmail.getText())) {
					UserTicketDAO dao1 = new UserTicketDAO();
					User_Group2 ug = new User_Group2();
					ug.setEmail(txtEmail.getText());
					dao1.gtUser(ug);
					if(v.isNotEmpty(ug.getFirst_name())) {
						count = dao.getTicketbyEmail(txtEmail.getText()).size();
						lblError.setText("");
						lblResult.setText(String.valueOf(count));
					}
					else {
						lblResult.setText("");
						lblError.setText("Email ID not found in records");
					}
				}
				else {
					lblResult.setText("");
					lblError.setText("Please enter a valid Email ID");
				}
			}
		});
		btnCountEmail.setBounds(650, 30, 73, 25);
		frame.getContentPane().add(btnCountEmail);
		
		
		JButton btnCountDate = new JButton("Count");
		btnCountDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(v.validateDate(txtDate.getText())) {
					lblResult.setText("");
					count = dao.getCountByDate(txtDate.getText());
					lblError.setText("");
					lblResult.setText(String.valueOf(count));
				} 
				else {
					lblError.setText("Please enter the date in yyyy/mm/dd");
				}
			}
		});
		btnCountDate.setBounds(650, 77, 73, 25);
		frame.getContentPane().add(btnCountDate);
		
		
		JButton btnCountAdmin = new JButton("Count");
		btnCountAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResult.setText("");
				count = dao.getCountByAdmin(String.valueOf(cbAdmin.getSelectedItem()));
				lblResult.setText(String.valueOf(count));
			}
		});
		btnCountAdmin.setBounds(650, 128, 73, 25);
		frame.getContentPane().add(btnCountAdmin);
		
		
		JButton btnCountTicket = new JButton("Count");
		btnCountTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResult.setText("");
				count = dao.getTickets().size();
				lblResult.setText(String.valueOf(count));
			}
		});
		btnCountTicket.setBounds(327, 176, 73, 25);
		frame.getContentPane().add(btnCountTicket);
		
		
		JButton btnCountOTicket = new JButton("Count");
		btnCountOTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResult.setText("");
				count = dao.getOpenTickets().size();
				lblResult.setText(String.valueOf(count));
			}
		});
		btnCountOTicket.setBounds(327, 222, 73, 25);
		frame.getContentPane().add(btnCountOTicket);
		
		
		JButton btnCountCTicket = new JButton("Count");
		btnCountCTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResult.setText("");
				count = dao.getCloseTickets().size();
				lblResult.setText(String.valueOf(count));
			}
		});
		btnCountCTicket.setBounds(327, 265, 73, 25);
		frame.getContentPane().add(btnCountCTicket);
		
		
	}
}
