package com.project.Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.project.Controller.WordWrapCellRenderer;
import com.project.Entity.Tickets_Group2;

import javax.swing.JButton;
import java.awt.ScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SearchTicketGUI {

	private JFrame frame;
	private int searchNum = 0;
	private String searchEmail = "";
	private JTextField txtSearchNum;
	private JTextField txtSearchEmail;
	private JTable table;
	
	int i = 0;
	private UserTicketDAO uto = new UserTicketDAO();
	private DefaultTableModel tm;
	private ListSelectionListener lsl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchTicketGUI window = new SearchTicketGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	private void addTableData()	{
		
		table.getSelectionModel().removeListSelectionListener(lsl);
		
		tm = new DefaultTableModel();
		
		tm.addColumn("Ticket Number");
		tm.addColumn("Email ID");
		tm.addColumn("Issue");
		
		ArrayList<Tickets_Group2> tl = getSearchedTicket();
		
		for (Tickets_Group2 t: tl)	{
			tm.addRow(t.getVector());
		}
		
		table.setModel(tm);
		
		table.setRowSorter(new TableRowSorter(tm));

		table.getSelectionModel().addListSelectionListener(lsl);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setMinWidth(150);
		
		table.getColumnModel().getColumn(2).setCellRenderer(new WordWrapCellRenderer());
	}
	
	public ArrayList<Tickets_Group2> getSearchedTicket() {
		if(i==1) {
			return uto.getTicketbyNumber(searchNum);
		} else if(i==2) {
			return uto.getTicketbyEmail(searchEmail);
		}
		return uto.getTickets();
	}

	/**
	 * Create the application.
	 */
	public SearchTicketGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 837, 483);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtSearchNum = new JTextField();
		txtSearchNum.setToolTipText("Enter the ticket number");
		txtSearchNum.setBounds(188, 13, 139, 22);
		frame.getContentPane().add(txtSearchNum);
		txtSearchNum.setColumns(10);
		
		JButton btnSearchByNum = new JButton("Search by Ticket Number");
		btnSearchByNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchNum = Integer.parseInt(txtSearchNum.getText());
				i=1;
				addTableData();
			}
		});
		btnSearchByNum.setBounds(356, 12, 177, 25);
		frame.getContentPane().add(btnSearchByNum);
		
		txtSearchEmail = new JTextField();
		txtSearchEmail.setToolTipText("Enter the email address");
		txtSearchEmail.setColumns(10);
		txtSearchEmail.setBounds(188, 62, 139, 22);
		frame.getContentPane().add(txtSearchEmail);
		
		JButton btnSearchByEmail = new JButton("Search by Email");
		btnSearchByEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchEmail = txtSearchEmail.getText();
				i=2;
				addTableData();
			}
		});
		btnSearchByEmail.setBounds(356, 61, 177, 25);
		frame.getContentPane().add(btnSearchByEmail);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(21, 117, 549, 295);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
	}
}
