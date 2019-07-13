package com.project.Boundary;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.project.Controller.Validations;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;

public class LoginGUI {

	private JFrame frame;
	private JTextField txtUsername;
	private static Validations v = new Validations();
	UserTicketDAO dao;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
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
	public LoginGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 775, 435);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblItTicketingSystem = new JLabel("IT Ticketing System");
		lblItTicketingSystem.setBounds(307, 16, 149, 20);
		frame.getContentPane().add(lblItTicketingSystem);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(152, 108, 87, 20);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(152, 154, 87, 20);
		frame.getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(283, 105, 146, 26);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(283, 151, 146, 26);
		frame.getContentPane().add(txtPassword);
		
		JLabel lblResult = new JLabel("");
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblResult.setBounds(396, 190, 291, 59);
		frame.getContentPane().add(lblResult);
		
		/*
		 * Verifying login 
		 */
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(v.isNotEmpty(txtUsername.getText()) && v.isNotEmpty(txtPassword.getText())) {
					dao = new UserTicketDAO();
					String user = txtUsername.getText();
					String pass = txtPassword.getText();
					boolean isValid= dao.validate(user, pass);
					if(isValid) {
						lblResult.setForeground(Color.green);
						lblResult.setText("Login Successful");
						DashboardGUI dg = new DashboardGUI();
						dg.Dashboard();
					}
					else
					{
						lblResult.setForeground(Color.red);
						lblResult.setText("Invalid credentials, Try again!");
					}
				} else {
					lblResult.setForeground(Color.red);
					lblResult.setText("Input fields cannot be empty.");
				}
			}
		});
		btnLogin.setBounds(211, 209, 115, 29);
		frame.getContentPane().add(btnLogin);

	}
}
