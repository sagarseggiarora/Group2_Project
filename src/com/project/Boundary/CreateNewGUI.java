package com.project.Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.project.Entity.Tickets_Group2;
import com.project.Entity.User_Group2;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateNewGUI {

	private JFrame frame;
	private JTextField txtTicket;
	private JTextField txtFname;
	private JTextField txtLname;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JTextField txtPhone;
	
	UserTicketDAO dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		JButton btnCreateTicket = new JButton("Create Ticket");
		btnCreateTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dao=new UserTicketDAO();
				Tickets_Group2 t1=new Tickets_Group2();
				User_Group2 u1=new User_Group2();
				//String email=txtEmail.getText();
				u1.setFirst_name(txtFname.getText());
				u1.setLast_name(txtLname.getText());
				u1.setEmail(txtEmail.getText());
				u1.setAddress(txtAddress.getText());
				u1.setPhone_number(txtPhone.getText());
				
				
				t1.setEmail(txtEmail.getText());
				t1.setIssue(txtrIssue.getText());
				
				
				dao.newCustomer(u1);
				dao.newTicket(t1);
				
				//String ticket_NO=dao.getTicketID(email);
				//txtTicket.setText(ticket_NO);
				
				
				
			}
		});
		btnCreateTicket.setBounds(391, 81, 146, 29);
		frame.getContentPane().add(btnCreateTicket);
		
		JLabel lblIssue = new JLabel("Issue");
		lblIssue.setBounds(15, 307, 69, 20);
		frame.getContentPane().add(lblIssue);
		
		
		
		
	}
}
