package com.project.Boundary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.Entity.Logs_Group2;
import com.project.Entity.Tickets_Group2;
import com.project.Entity.User_Group2;

public class UserTicketDAO {
	
		private String dsn = "jdbc:mysql://localhost/project_group2";
		private String username = "root";
		private String password = "";
		
		private Connection conn = null;
		 private ResultSet rs = null;
		 private Statement stmt = null;
		 private PreparedStatement pstmt = null;
		 
		/*
		 * Connect to database
		 */
		public void ConnectDB()	{
			
			try {
				//Initialize JDBC Connection
				this.conn = DriverManager.getConnection(this.dsn, this.username, this.password);
				//Check if the connection opened
				if (this.conn.isClosed()) {
					System.out.println("Database connection could not be establisehd");
				} else {
					System.out.println("Database connection established.");
				}
			} catch (SQLException sx)	{
				System.out.println("Error Connecting to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
				}
			}
				
		/*
		 * Disconnect from database
		 */
		public void DisconnectDB()	{
			try {
				this.conn.close();
				if (this.conn.isClosed()) {
					System.out.println("Database connection is closed.");
				} else {
					System.out.println("There was a problem disconnecting from the database!");
					
				}
			} catch (SQLException sx)	{
				System.out.println("Error Connecting to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
				}
		}
		
		/*
		 * Verify if the username and password is correct
		 */
		public boolean validate(String username, String Password)
		{
			String sql ="select username,password from login where username = '"+username+"' and password = SHA2('"+Password+"', 512)";
			boolean check = false;
			
			try {
				ConnectDB();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if(rs.next())
				{
					check= true;
				}
				else
				{
					check = false;
				}
				DisconnectDB();

			}

			catch(SQLException sx)
			{
				System.out.println("Error connection to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
			}
			return check;
			
		}
		
		/*
		 * Generate new ticket
		 */
		public int newTicket(Tickets_Group2 tick) {
			
			int ticketNumber=0;
			String sql="INSERT INTO Tickets (email, issue, status, date, added_by)" + " VALUES ('" + tick.getEmail()+"','"+tick.getIssue()+"','Open','"+tick.getDate()+"','"+tick.getAdded_by()+"');";
			
			try {
				ConnectDB();
				stmt = conn.createStatement();
				ticketNumber = stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
				DisconnectDB();
			}
			catch(SQLException sx)
			{
				System.out.println("Error connection to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
			}
			return ticketNumber;
			
		}
		
		public int newLog(Logs_Group2 nl) {
			
			int logId=0;
			String sql="INSERT INTO logs (ticket_no, Comment, date, submit_by)" + " VALUES ('" + nl.getTicket_no()+"','"+nl.getComment()+"','"+nl.getDate()+"','"+nl.getSubmitted_by()+"');";
			
			try {
				ConnectDB();
				stmt = conn.createStatement();
				logId = stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
				DisconnectDB();
			}
			catch(SQLException sx)
			{
				System.out.println("Error connection to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
			}
			return logId;
			
		}
		
		/*
		 * Create a new user
		 */
		public int newCustomer(User_Group2 cust)	{
			
			int newCustID = 0;
			
			try {
				
				ConnectDB();
				
				//Prepare the statement to save to the database.
				String sql = "INSERT INTO user (first_name, last_name, email,address,phone_number)"
						+ " VALUES ('" + cust.getFirst_name() + "','" + cust.getLast_name() + "','" + cust.getEmail() + "','" + cust.getAddress() + "','" + cust.getPhone_number() + "');";
				
				//Create the statement
				this.stmt = this.conn.createStatement();
				
				//Execute the statement
				 
				newCustID = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				
				DisconnectDB();
			} catch (SQLException sx)	{
				System.out.println("Error Inserting Student");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
			}
			
			
			return newCustID;
		}
		
		/**
		 * Get ticket object by ticket number
		 * @param i Ticket number
		 * @return Ticket object 
		 */
		public Tickets_Group2 getTicket(int i){
			
			//Create an empty ticket to return
			Tickets_Group2 gt = new Tickets_Group2();
			
			//Create the SQL prepared statement
			String sql = "SELECT * FROM tickets WHERE Ticket_Number = ?";
			
			
			try {
				//Connect to the database 
				ConnectDB();
				
				//Create the SQL query
				this.pstmt = this.conn.prepareStatement(sql);
				
				//Set the parameter
				this.pstmt.setInt(1,i);
				
				//Get the result
				this.rs = this.pstmt.executeQuery();
				
				while (this.rs.next())	{
					
					gt.setTicket_number(rs.getInt("Ticket_Number"));
					gt.setEmail(rs.getString("Email"));
					gt.setIssue(rs.getString("Issue"));
					gt.setStatus(rs.getString("status"));
									
				}
				
			} catch (SQLException se)	{
				System.out.println("Error retriving ticket " + i);
				System.out.println(se.getMessage());
				System.out.println(se.getErrorCode());
				System.out.println(se.getSQLState());
			} finally {
				
				//Disconnect from the database
				DisconnectDB();
			}
			
			return gt;
		}
		
		/**
		 * Get user object by ticket number
		 * @param i Ticket number
		 * @return User object 
		 */
		public User_Group2 getUser(int i){
			
			//Create an empty ticket to return
			User_Group2 gu = new User_Group2();
			
			//Create the SQL prepared statement
			String sql = "SELECT * FROM tickets inner join user on tickets.Email = user.email WHERE Ticket_Number = ?";
			
			
			try {
				//Connect to the database 
				ConnectDB();
				
				//Create the SQL query
				this.pstmt = this.conn.prepareStatement(sql);
				
				//Set the parameter
				this.pstmt.setInt(1,i);
				
				//Get the result
				this.rs = this.pstmt.executeQuery();
				
				while (this.rs.next())	{
					
					gu.setUser_id(rs.getInt("user_id"));
					gu.setFirst_name(rs.getString("first_name"));
					gu.setLast_name(rs.getString("last_name"));
					gu.setEmail(rs.getString("email"));
					gu.setAddress(rs.getString("address"));
					gu.setPhone_number(rs.getString("phone_number"));
									
				}
				
			} catch (SQLException se)	{
				System.out.println("Error retriving user");
				System.out.println(se.getMessage());
				System.out.println(se.getErrorCode());
				System.out.println(se.getSQLState());
			} finally {
				
				//Disconnect from the database
				DisconnectDB();
			}
			
			return gu;
		}
		
		/**
		 * Get all tickets
		 * @return Array List of all tickets in the database
		 */
		public ArrayList<Tickets_Group2> getTickets()	{
			
			ArrayList<Tickets_Group2> tl = new ArrayList<Tickets_Group2>();
			
			String sql = "SELECT * FROM tickets";
			
			try {
				ConnectDB();
				
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(sql);
				
			while (rs.next())	{
				Tickets_Group2 nt = new Tickets_Group2();
				
				nt.setTicket_number(rs.getInt("Ticket_Number"));
				nt.setEmail(rs.getString("Email"));
				nt.setIssue(rs.getString("Issue"));
				nt.setStatus(rs.getString("Status"));
				nt.setDate(rs.getString("Date"));
				nt.setAdded_by(rs.getString("Added_by"));
				
				tl.add(nt);
			
			}
			
			DisconnectDB();
				
			} catch (SQLException sx)	{
				System.out.println("Error Connecting to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
				}
			
			
			return tl;
		}
		
		public ArrayList<Logs_Group2> getLogs(String ticket){
			
			ArrayList<Logs_Group2> logList = new ArrayList<Logs_Group2>();
			
			String sql = "SELECT * FROM logs where ticket_no = ' "+ticket+"';";
			
			try {
				ConnectDB();
				
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(sql);
				
			while (rs.next())	{
				Logs_Group2 nl = new Logs_Group2();
				
				nl.setLog_id(rs.getInt("log_id"));
				nl.setComment(rs.getString("Comment"));
				nl.setDate(rs.getString("date"));
				nl.setSubmitted_by(rs.getString("submit_by"));
				
				logList.add(nl);
			
			}
			
			DisconnectDB();
				
			} catch (SQLException sx)	{
				System.out.println("Error Connecting to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
				}
			
			
			return logList;
			
		}
		
		/**
		 * Get all open tickets
		 * @return Array List of all open tickets in the database
		 */
		public ArrayList<Tickets_Group2> getOpenTickets()	{
			
			ArrayList<Tickets_Group2> tl = new ArrayList<Tickets_Group2>();
			
			String sql = "Select * from tickets where status ='open';";
			
			try {
				ConnectDB();
				
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(sql);
				
			while (rs.next())	{
				Tickets_Group2 nt = new Tickets_Group2();
				
				nt.setTicket_number(rs.getInt("Ticket_Number"));
				nt.setEmail(rs.getString("Email"));
				nt.setIssue(rs.getString("Issue"));
				nt.setStatus(rs.getString("Status"));
				nt.setDate(rs.getString("Date"));
				nt.setAdded_by(rs.getString("Added_by"));
				
				tl.add(nt);
			
			}
			
			
			
			DisconnectDB();
				
			} catch (SQLException sx)	{
				System.out.println("Error Connecting to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
				}
			
			
			return tl;
		}
		
		/**
		 * Get all closed tickets
		 * @return Array List of all closed tickets in the database
		 */
		public ArrayList<Tickets_Group2> getCloseTickets()	{
			
			ArrayList<Tickets_Group2> tl = new ArrayList<Tickets_Group2>();
			
			String sql = "Select * from tickets where status ='close';";
			
			try {
				ConnectDB();
				
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(sql);
				
			while (rs.next())	{
				Tickets_Group2 nt = new Tickets_Group2();
				
				nt.setTicket_number(rs.getInt("Ticket_Number"));
				nt.setEmail(rs.getString("Email"));
				nt.setIssue(rs.getString("Issue"));
				nt.setStatus(rs.getString("Status"));
				nt.setDate(rs.getString("Date"));
				nt.setAdded_by(rs.getString("Added_by"));
				
				tl.add(nt);
			
			}

			DisconnectDB();
				
			} catch (SQLException sx)	{
				System.out.println("Error Connecting to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
				}
			
			
			return tl;
		}
		
		/**
		 * Get the ticket from ticket number
		 * @param Ticket_Number
		 * @return Array List of tickets matching the ticket number
		 */
		public ArrayList<Tickets_Group2> getTicketbyNumber(int Ticket_Number)	{
			
			ArrayList<Tickets_Group2> tl = new ArrayList<Tickets_Group2>();
			
			String sql = "SELECT * FROM tickets WHERE Ticket_Number = ?";
			
			try {
				ConnectDB();
				
				this.pstmt = this.conn.prepareStatement(sql);
				
				pstmt.setInt(1,Ticket_Number);
				
				this.rs = pstmt.executeQuery();
				
			while (rs.next())	{
				Tickets_Group2 nt = new Tickets_Group2();
				
				nt.setTicket_number(rs.getInt("Ticket_Number"));
				nt.setEmail(rs.getString("Email"));
				nt.setIssue(rs.getString("Issue"));
				nt.setStatus(rs.getString("Status"));
				nt.setDate(rs.getString("Date"));
				nt.setAdded_by(rs.getString("Added_by"));
				
				tl.add(nt);
			
			}
			
			DisconnectDB();
				
			} catch (SQLException sx)	{
				System.out.println("Error Connecting to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
				}
			
			
			return tl;
		}

		/**
		 * Get all the tickets for the entered email
		 * @param Email
		 * @return Array List of tickets for the email
		 */
		public ArrayList<Tickets_Group2> getTicketbyEmail(String Email)	{
			
			ArrayList<Tickets_Group2> tl = new ArrayList<Tickets_Group2>();
			
			String sql = "SELECT * FROM tickets WHERE Email = ?";
			
			try {
				ConnectDB();
				
				this.pstmt = this.conn.prepareStatement(sql);
				
				pstmt.setString(1,Email);
				
				this.rs = pstmt.executeQuery();
				
			while (rs.next())	{
				Tickets_Group2 nt = new Tickets_Group2();
				
				nt.setTicket_number(rs.getInt("Ticket_Number"));
				nt.setEmail(rs.getString("Email"));
				nt.setIssue(rs.getString("Issue"));
				nt.setStatus(rs.getString("Status"));
				nt.setDate(rs.getString("Date"));
				nt.setAdded_by(rs.getString("Added_by"));
				
				tl.add(nt);
			
			}
			
			DisconnectDB();
				
			} catch (SQLException sx)	{
				System.out.println("Error Connecting to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
				}
			
			
			return tl;
		}
		
		/**
		 * Returns User object based on the email entered
		 * @param cs Email id of the user
		 * @return User object for the entered email
		 */
		public User_Group2 gtUser(User_Group2 cs)
		{
			try
			{
				ConnectDB();
			String sql = "select first_name,last_name,address,phone_number from user where email=?";
			pstmt = conn.prepareStatement(sql);
			this.pstmt.setString(1,cs.getEmail().trim());
			rs= pstmt.executeQuery();
			while(rs.next())
			{
				cs.setFirst_name(rs.getString("first_name"));
				cs.setLast_name(rs.getString("last_name"));
				cs.setAddress(rs.getString("address"));
				cs.setPhone_number(rs.getString("phone_number"));
			}
			}
			catch(SQLException sx)	{
				System.out.println("Error Fetching Customer");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
			}
			return cs;
			
		}
		

		/** Update the ticket with passed values
		 * @param ut Ticket object
		 */
		public void updateTickets(Tickets_Group2 ut)	{
			
			//Update Query
			String sql = "UPDATE tickets SET " + 
					"status = ? " +
					" WHERE Ticket_Number = ?";
			
			try {
				
				//Connect to the database
				ConnectDB();
				
				//Create the prepared statements
				this.pstmt = this.conn.prepareStatement(sql);
				
				//Set the parameters
				this.pstmt.setString(1, ut.getStatus());
				this.pstmt.setInt(2, ut.getTicket_number());
								
				//Execute the statement
				this.pstmt.executeUpdate();
				//Run the statement
				System.out.println("Updated Ticket: " + ut.getTicket_number());
				//Disconnect to the database
				DisconnectDB();
				
			} catch (SQLException sx)	{
				
				System.out.println("Error updating Ticket: " + String.valueOf(ut.getTicket_number()) + "\n");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
			}
			
		}
		
		/**
		 * Gets ticket number generated
		 * @param tn Ticket object
		 */
		public void gtTicketNumberNewCust(Tickets_Group2 tn)
		{
			String sql = "select Ticket_Number from tickets where email=?";
			try {
				ConnectDB();
				pstmt = conn.prepareStatement(sql);
				this.pstmt.setString(1,tn.getEmail().trim());
				rs= pstmt.executeQuery();
				while(rs.next())
				{
					tn.setTicket_number(rs.getInt("Ticket_Number"));
				}
				
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		/**
		 * Updates user details
		 * @param up User object
		 */
		public void UpdateUser(User_Group2 up)
		{
			
			String sql =" update user "
					+ "set first_name=?,last_name=?,address=?,phone_number=? "
					+"where email=?";
			try {
				ConnectDB();
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1,up.getFirst_name());
			this.pstmt.setString(2,up.getLast_name());
			this.pstmt.setString(3,up.getAddress());
			this.pstmt.setString(4,up.getPhone_number());
			this.pstmt.setString(5,up.getEmail());
			this.pstmt.executeUpdate();
			DisconnectDB();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		public int getCountByAdmin(String admin)
		{
			int count = 0;
			String sql = "Select count(*) as total from tickets where added_by = ?";
			
			try {
				ConnectDB();
				
				this.pstmt = this.conn.prepareStatement(sql);
				
				pstmt.setString(1,admin);
				
				this.rs = pstmt.executeQuery();
				
			while (rs.next())	{
				count = rs.getInt("total");
			}
			
			DisconnectDB();
				
			} catch (SQLException sx)	{
				System.out.println("Error Connecting to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
				}
			
			
			return count;
		}
		
		public int getCountByDate(String date)
		{
			int count = 0;
			String sql = "Select count(*) as total from tickets where date like ?";
			
			try {
				ConnectDB();
				
				this.pstmt = this.conn.prepareStatement(sql);
				
				pstmt.setString(1, date + "%");
				
				this.rs = pstmt.executeQuery();
				
			while (rs.next())	{
				count = rs.getInt("total");
			}
			
			DisconnectDB();
				
			} catch (SQLException sx)	{
				System.out.println("Error Connecting to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
				}
			
			
			return count;
		}
		 public Tickets_Group2 gtNewTickCount(Tickets_Group2 tu) {
				
				
				String sql = "insert into cxcreate values('"+ tu.getCreated_by() +"');";
				
				try {
					ConnectDB();
					stmt = conn.createStatement();
					stmt.execute(sql);
					DisconnectDB();
				}
				catch(SQLException sx)
				{
					System.out.println("Error connection to database");
					System.out.println(sx.getMessage());
					System.out.println(sx.getErrorCode());
					System.out.println(sx.getSQLState());
				}
				return tu;
				
			}
         public Tickets_Group2 upcx(Tickets_Group2 tu) {
			
			
			String sql = "insert into fetchcx values('"+ tu.getfCust() +"');";
			
			try {
				ConnectDB();
				stmt = conn.createStatement();
				stmt.execute(sql);
				//ticketNumber = stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
				DisconnectDB();
			}
			catch(SQLException sx)
			{
				System.out.println("Error connection to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
			}
			return tu;
			
		}
         
     	public int getCountBycx(String val)
		{
			int count = 0;
			String sql ="select count(created_by) as total from cxcreate where created_by=?";
			
			try {
				ConnectDB();
				
				this.pstmt = this.conn.prepareStatement(sql);
				
				pstmt.setString(1, val);
				
				this.rs = pstmt.executeQuery();
				
			while (rs.next())	{
				count = rs.getInt("total");
			}
			
			DisconnectDB();
				
			} catch (SQLException sx)	{
				System.out.println("Error Connecting to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
				}
			return count;
		}
     	
     	public int getCountByUp(String val)
		{
			int count = 0;
			String sql ="select count(created_by) as total from fetchcx where created_by=?";
			
			try {
				ConnectDB();
				
				this.pstmt = this.conn.prepareStatement(sql);
				
				pstmt.setString(1, val);
				
				this.rs = pstmt.executeQuery();
				
			while (rs.next())	{
				count = rs.getInt("total");
			}
			
			DisconnectDB();
				
			} catch (SQLException sx)	{
				System.out.println("Error Connecting to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
				}
			return count;
		}
     	
    	public int getCountByLog(String val)
		{
			int count = 0;
			String sql ="select count(submit_by) as total from logs where submit_by=?";
			
			try {
				ConnectDB();
				
				this.pstmt = this.conn.prepareStatement(sql);
				
				pstmt.setString(1, val);
				
				this.rs = pstmt.executeQuery();
				
			while (rs.next())	{
				count = rs.getInt("total");
			}
			
			DisconnectDB();
				
			} catch (SQLException sx)	{
				System.out.println("Error Connecting to database");
				System.out.println(sx.getMessage());
				System.out.println(sx.getErrorCode());
				System.out.println(sx.getSQLState());
				}
			return count;
		}

		
		
}
