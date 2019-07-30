package com.project.Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class UserStatGui {

	private JFrame frame;
	private JTextField admin_name;
	 UserTicketDAO dao;
   private static String user="";

	/**
	 * Launch the application.
	 */
	public static void userStat(String Username) {
		user =Username;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					UserStatGui window = new UserStatGui();
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
	public UserStatGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		admin_name = new JTextField();
		admin_name.setBounds(210, 36, 103, 20);
		frame.getContentPane().add(admin_name);
		admin_name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(308, 112, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(308, 172, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblPleaseEnterThe = new JLabel("Please Enter the name of the admin");
		lblPleaseEnterThe.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPleaseEnterThe.setBounds(10, 39, 190, 14);
		frame.getContentPane().add(lblPleaseEnterThe);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(308, 219, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnGo = new JButton("Go");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dao = new UserTicketDAO();
				//dao.getCountBycx(admin_name.getText().trim());
				DecimalFormat fd = new DecimalFormat("##.#");
				lblNewLabel.setText(fd.format(dao.getCountBycx(admin_name.getText().trim())));
				lblNewLabel_1.setText(fd.format(dao.getCountByUp(admin_name.getText().trim())));
				lblNewLabel_2.setText(fd.format(dao.getCountByLog(admin_name.getText().trim())));
			}
		});
		btnGo.setBounds(335, 35, 89, 23);
		frame.getContentPane().add(btnGo);
		
		JLabel lblTotalNumberOf = new JLabel("Total number of new Customers created");
		lblTotalNumberOf.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblTotalNumberOf.setBounds(10, 109, 237, 20);
		frame.getContentPane().add(lblTotalNumberOf);
		
		JLabel lblTotalNumberOf_1 = new JLabel("Total Number of Updations Made");
		lblTotalNumberOf_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblTotalNumberOf_1.setBounds(10, 172, 190, 14);
		frame.getContentPane().add(lblTotalNumberOf_1);
		
		JLabel lblTotalNumberOf_2 = new JLabel("Total number of Logs Generated");
		lblTotalNumberOf_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblTotalNumberOf_2.setBounds(10, 219, 190, 14);
		frame.getContentPane().add(lblTotalNumberOf_2);
		
		
		
		
	}
}
