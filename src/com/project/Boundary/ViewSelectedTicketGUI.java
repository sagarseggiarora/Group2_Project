package com.project.Boundary;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.project.Entity.Tickets_Group2;
import com.project.Entity.User_Group2;
import javax.swing.JButton;

public class ViewSelectedTicketGUI {

	private JFrame frame;
	private static int id = 0;
	private UserTicketDAO uto = new UserTicketDAO();
	private JTextField txtTicketNum;
	private JTextField txtEmail;
	private JTextField txtName;
	private JTextField txtIssue;

	/**
	 * Launch the application.
	 * @param currId 
	 */
	public static void ViewSelected(int currId) {
		id = currId;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSelectedTicketGUI window = new ViewSelectedTicketGUI();
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
	public ViewSelectedTicketGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 521, 423);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTicket = new JLabel("Ticket #");
		lblTicket.setBounds(33, 25, 66, 26);
		frame.getContentPane().add(lblTicket);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(33, 64, 66, 26);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(33, 111, 66, 26);
		frame.getContentPane().add(lblName);
		
		JLabel lblIssue = new JLabel("Issue");
		lblIssue.setBounds(33, 162, 66, 26);
		frame.getContentPane().add(lblIssue);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(33, 221, 66, 26);
		frame.getContentPane().add(lblStatus);
		
		txtTicketNum = new JTextField();
		txtTicketNum.setEditable(false);
		txtTicketNum.setBounds(127, 29, 149, 22);
		frame.getContentPane().add(txtTicketNum);
		txtTicketNum.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(127, 66, 149, 22);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(127, 113, 149, 22);
		frame.getContentPane().add(txtName);
		
		txtIssue = new JTextField();
		txtIssue.setColumns(10);
		txtIssue.setBounds(127, 164, 149, 22);
		frame.getContentPane().add(txtIssue);
		
		JComboBox cbStatus = new JComboBox();
		cbStatus.setBounds(127, 223, 149, 22);
		frame.getContentPane().add(cbStatus);
		
		cbStatus.addItem("Select");
		cbStatus.addItem("Open");
		cbStatus.addItem("Close");
		
		Tickets_Group2 cs = uto.getTicket(id);
		User_Group2 ug = uto.getUser(id);;
		
		//Populate the text fields with the ticket data
		txtTicketNum.setText(String.valueOf(cs.getTicket_number()));
		txtEmail.setText(cs.getEmail());
		txtName.setText(ug.getFirst_name()+ " " + ug.getLast_name());
		txtIssue.setText(cs.getIssue());
		String status=cs.getStatus();
		cbStatus.setSelectedItem(status);
		
		
		Tickets_Group2 ut=new Tickets_Group2();
		cbStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbStatus.getSelectedItem()=="Close") {
					ut.setStatus("Close");
					ut.setTicket_number(Integer.parseInt(txtTicketNum.getText()));					
				} else if (cbStatus.getSelectedItem()=="Open") {
					ut.setStatus("Open");
					ut.setTicket_number(Integer.parseInt(txtTicketNum.getText()));
				}
				
			}
		});
		JButton btnUpdateStatus = new JButton("Update Status");
		btnUpdateStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uto.updateTickets(ut);
				
			}
		});
		btnUpdateStatus.setBounds(135, 296, 205, 29);
		frame.getContentPane().add(btnUpdateStatus);
				
	}
}
