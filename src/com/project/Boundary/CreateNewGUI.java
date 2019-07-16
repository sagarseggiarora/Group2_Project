package com.project.Boundary;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.project.Controller.Validations;
import com.project.Entity.Tickets_Group2;
import com.project.Entity.User_Group2;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CreateNewGUI {

	private JFrame frame;
	private JTextField txtTicket;
	private JTextField txtFname;
	private JTextField txtLname;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JTextField txtPhone;
	private static Validations v = new Validations();
	
	UserTicketDAO dao = new UserTicketDAO();;

	/**
	 * Launch the application.
	 */
	public static void CreateNew() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateNewGUI window = new CreateNewGUI();
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
	public CreateNewGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 792, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblItTicketingSystem = new JLabel("IT Ticketing System");
		lblItTicketingSystem.setBounds(321, 26, 181, 20);
		frame.getContentPane().add(lblItTicketingSystem);
		
		JLabel lblTicket = new JLabel("Ticket");
		lblTicket.setBounds(15, 85, 69, 20);
		frame.getContentPane().add(lblTicket);
		
		JLabel lblCustomerName = new JLabel("Customer FName");
		lblCustomerName.setBounds(15, 121, 125, 20);
		frame.getContentPane().add(lblCustomerName);
		
		JLabel lblCustomerLname = new JLabel("Customer LName");
		lblCustomerLname.setBounds(15, 157, 125, 20);
		frame.getContentPane().add(lblCustomerLname);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(15, 189, 69, 20);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(15, 224, 69, 20);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(15, 260, 69, 20);
		frame.getContentPane().add(lblPhone);
		
		txtTicket = new JTextField();
		txtTicket.setEditable(false);
		txtTicket.setBounds(159, 82, 146, 26);
		frame.getContentPane().add(txtTicket);
		txtTicket.setColumns(10);
		
		txtFname = new JTextField();
		txtFname.setBounds(159, 118, 146, 26);
		frame.getContentPane().add(txtFname);
		txtFname.setColumns(10);
		
		txtLname = new JTextField();
		txtLname.setBounds(159, 154, 146, 26);
		frame.getContentPane().add(txtLname);
		txtLname.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(159, 189, 146, 26);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(159, 224, 146, 26);
		frame.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(159, 260, 146, 26);
		frame.getContentPane().add(txtPhone);
		txtPhone.setColumns(10);
		
		JTextArea txtrIssue = new JTextArea();
		txtrIssue.setBounds(159, 307, 146, 99);
		frame.getContentPane().add(txtrIssue);
		
		JLabel lblResult = new JLabel("");
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblResult.setBounds(391, 228, 348, 52);
		frame.getContentPane().add(lblResult);
		
		JLabel lblIssue = new JLabel("Issue");
		lblIssue.setBounds(15, 307, 69, 20);
		frame.getContentPane().add(lblIssue);
		
		/*
		 * Creating a new ticket and a new user in database
		 */
		JButton btnCreateTicket = new JButton("Create Ticket");
		btnCreateTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(v.isNotEmpty(txtFname.getText()) && v.isNotEmpty(txtLname.getText()) && v.isNotEmpty(txtEmail.getText()) && v.isNotEmpty(txtPhone.getText()) && v.isNotEmpty(txtAddress.getText()) && v.isNotEmpty(txtrIssue.getText())) {
					if(v.validateEmail(txtEmail.getText())) {
						if(v.validatePhone(txtPhone.getText())) {
							if(dao.getTicketbyEmail(txtEmail.getText()).isEmpty()) {
								Tickets_Group2 t1=new Tickets_Group2();
								User_Group2 u1=new User_Group2();
								u1.setFirst_name(txtFname.getText());
								u1.setLast_name(txtLname.getText());
								u1.setEmail(txtEmail.getText());
								u1.setAddress(txtAddress.getText());
								u1.setPhone_number(txtPhone.getText());

								t1.setEmail(txtEmail.getText());
								t1.setIssue(txtrIssue.getText());								
								
							    dao.newCustomer(u1);
								dao.newTicket(t1);
								Tickets_Group2 tf = new Tickets_Group2();
								tf.setEmail(txtEmail.getText().trim());
								dao.gtTicketNumberNewCust(tf);
								
								lblResult.setForeground(Color.GREEN);
								lblResult.setText("Ticket ID Generated: " + String.valueOf(tf.getTicket_number()));
							} else {
								lblResult.setForeground(Color.red);
								lblResult.setText("Email ID already exists!");
							}
						} else {
							lblResult.setForeground(Color.red);
							lblResult.setText("Please enter a valid Phone Number");
						}
					} else {
						lblResult.setForeground(Color.red);
						lblResult.setText("Please enter a valid Email ID");
					}
				} else {
					lblResult.setForeground(Color.red);
					lblResult.setText("Input fields cannot be empty.");
				}
			}
		});
		btnCreateTicket.setBounds(391, 81, 146, 29);
		frame.getContentPane().add(btnCreateTicket);
		
		JButton button = new JButton("<<GoBack");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashboardGUI dg = new DashboardGUI();
				dg.Dashboard();
			}
		});
		button.setBounds(15, 465, 106, 23);
		frame.getContentPane().add(button);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lg = new LoginGUI();
				lg.main(null);
			}
		});
		btnLogout.setBounds(431, 465, 106, 23);
		frame.getContentPane().add(btnLogout);
		
	}
}
