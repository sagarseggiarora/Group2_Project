package com.project.Boundary;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.project.Controller.Validations;
import com.project.Entity.Tickets_Group2;
import com.project.Entity.User_Group2;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ExistingCustGui {

	private JFrame frame;
	private JTextField gtEmail;
	private JTextField FName;
	private JTextField Lname;
	private JTextField AddrField;
	private JTextField numField;
	private JTextField txtIssue;
	UserTicketDAO dao1;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	private static Validations v = new Validations();
	private static String userName = "";

	/**
	 * Launch the application.
	 */
	public static void Existing(String user) {
		userName=user;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExistingCustGui window = new ExistingCustGui();
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
	public ExistingCustGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 518, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 26, 46, 14);
		frame.getContentPane().add(lblEmail);
		
		gtEmail = new JTextField();
		gtEmail.setBounds(98, 23, 167, 20);
		frame.getContentPane().add(gtEmail);
		gtEmail.setColumns(10);
		
		JLabel lblResult = new JLabel("");
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblResult.setBounds(206, 170, 282, 49);
		frame.getContentPane().add(lblResult);
		
		/*
		 * Fetching the customer details using email
		 */
		
		JButton fetchInfo = new JButton("Fetch");
		fetchInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(v.validateEmail(gtEmail.getText())) {
					dao1 = new UserTicketDAO();
					User_Group2 ug = new User_Group2();
					ug.setEmail(gtEmail.getText().trim());
					dao1.gtUser(ug);
					
					if(v.isNotEmpty(ug.getFirst_name())) {
						FName.setText(ug.getFirst_name());
						Lname.setText(ug.getLast_name());
						AddrField.setText(ug.getAddress());
						numField.setText(ug.getPhone_number());
						lblResult.setText("");
						gtEmail.setEditable(false);
						
						
						
					} else {
						lblResult.setForeground(Color.red);
						lblResult.setText("Email ID not found in records");
					}
				}
				else {
					lblResult.setForeground(Color.red);
					lblResult.setText("Please enter a valid Email ID");
				}
				
				
			}
		});
		fetchInfo.setBounds(302, 22, 89, 23);
		frame.getContentPane().add(fetchInfo);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 69, 78, 14);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 103, 78, 14);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 141, 78, 14);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(10, 181, 78, 14);
		frame.getContentPane().add(lblPhoneNumber);
		
		FName = new JTextField();
		FName.setBounds(98, 66, 86, 20);
		frame.getContentPane().add(FName);
		FName.setColumns(10);
		
		Lname = new JTextField();
		Lname.setBounds(98, 100, 86, 20);
		frame.getContentPane().add(Lname);
		Lname.setColumns(10);
		
		AddrField = new JTextField();
		AddrField.setBounds(97, 138, 86, 20);
		frame.getContentPane().add(AddrField);
		AddrField.setColumns(10);
		
		numField = new JTextField();
		numField.setBounds(97, 178, 86, 20);
		frame.getContentPane().add(numField);
		numField.setColumns(10);
		
		JLabel lblIssue = new JLabel("Issue");
		lblIssue.setBounds(10, 232, 78, 20);
		frame.getContentPane().add(lblIssue);
		
		txtIssue = new JTextField();
		txtIssue.setBounds(98, 232, 279, 112);
		frame.getContentPane().add(txtIssue);
		txtIssue.setColumns(10);
		
		/*
		 * Creating a new issue for an existing customer
		 */
		
		JButton btnAdd = new JButton("ADD ISSUE");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(v.isNotEmpty(txtIssue.getText())) {
					Tickets_Group2 tf = new Tickets_Group2();
					dao1 = new UserTicketDAO();
					tf.setEmail(gtEmail.getText().trim());
					tf.setIssue(txtIssue.getText().trim());
					tf.setDate(dateFormat.format(date).trim());
					tf.setAdded_by(userName);
					dao1.newTicket(tf);
					
					Tickets_Group2 tf1 = new Tickets_Group2();
					tf1.setEmail(gtEmail.getText().trim());
					dao1.gtTicketNumberNewCust(tf1);
					
					lblResult.setForeground(Color.GREEN);
					lblResult.setText("Ticket ID Generated: " + String.valueOf(tf1.getTicket_number()));
				} else {
					lblResult.setForeground(Color.red);
					lblResult.setText("Issue field cannot be empty");
				}
			}
		});
		btnAdd.setBounds(195, 354, 142, 23);
		frame.getContentPane().add(btnAdd);
		
		/*
		 * Updating details of fetched customer 
		 */
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(v.isNotEmpty(FName.getText()) && v.isNotEmpty(Lname.getText()) && v.isNotEmpty(AddrField.getText()) && v.isNotEmpty(numField.getText())) {
					if(v.validatePhone(numField.getText()))	{
						User_Group2 tf2 = new User_Group2();
						Tickets_Group2 tp = new Tickets_Group2();
						dao1 = new UserTicketDAO();
						tp.setfCust("test");
						dao1.upcx(tp);
						tf2.setFirst_name(FName.getText().trim());
						tf2.setLast_name(Lname.getText().trim());
						tf2.setAddress(AddrField.getText().trim());
						tf2.setPhone_number(numField.getText().trim());
						tf2.setEmail(gtEmail.getText().trim());
						dao1.UpdateUser(tf2);
						lblResult.setText("");
						
						
					} else { 
						lblResult.setForeground(Color.red);
						lblResult.setText("Please enter a valid Phone Number");
					}
				} else {
					lblResult.setForeground(Color.red);
					lblResult.setText("Input fields cannot be empty.");
				}
			}
		});
		btnUpdate.setBounds(302, 65, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton button = new JButton("LOGOUT");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LoginGUI lg = new LoginGUI();
				lg.main(null);
			}
		});
		button.setBounds(367, 355, 121, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("<<Go Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				DashboardGUI dg = new DashboardGUI();
				dg.Dashboard(userName);
			}
		});
		button_1.setBounds(30, 355, 121, 23);
		frame.getContentPane().add(button_1);
		
	}
}
