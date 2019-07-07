package com.project.Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.project.Entity.User_Group2;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExistingCustGui {

	private JFrame frame;
	private JTextField gtEmail;
	private JTextField FName;
	private JTextField Lname;
	private JTextField AddrField;
	private JTextField numField;
	private JTextField txtIssue;
	UserTicketDAO dao1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		JButton fetchInfo = new JButton("Fetch");
		fetchInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dao1 = new UserTicketDAO();
			User_Group2 ug = new User_Group2();
			ug.setEmail(gtEmail.getText().trim());
			dao1.gtUser(ug);
			FName.setText(ug.getFirst_name());
			Lname.setText(ug.getLast_name());
			AddrField.setText(ug.getAddress());
			numField.setText(ug.getPhone_number());
			
				
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
		FName.setEditable(false);
		FName.setBounds(98, 66, 86, 20);
		frame.getContentPane().add(FName);
		FName.setColumns(10);
		
		Lname = new JTextField();
		Lname.setEditable(false);
		Lname.setBounds(98, 100, 86, 20);
		frame.getContentPane().add(Lname);
		Lname.setColumns(10);
		
		AddrField = new JTextField();
		AddrField.setEditable(false);
		AddrField.setBounds(97, 138, 86, 20);
		frame.getContentPane().add(AddrField);
		AddrField.setColumns(10);
		
		numField = new JTextField();
		numField.setEditable(false);
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
	}
}
