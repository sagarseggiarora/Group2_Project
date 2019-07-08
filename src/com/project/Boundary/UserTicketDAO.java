package com.project.Boundary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	
		public boolean validate(String username, String Password)
		{
			String sql ="select username,password from login where username = '"+username+"' and password= '"+Password+"'";
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
		
		public int newTicket(Tickets_Group2 tick) {
			
			int ticketNumber=0;
			String sql="INSERT INTO Tickets (email, issue, status)" + " VALUES ('" + tick.getEmail()+"','"+tick.getIssue()+"','Open');";
			
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
		
		//
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
		public void AddIssue(User_Group2 t)
		{
			String sql = "insert into tickets(Email,Issue, status) value(?,?,?)";
			try {
				ConnectDB();
				pstmt = conn.prepareStatement(sql);
				this.pstmt.setString(1,t.getEmail());
				this.pstmt.setString(2,t.getIssue());
				this.pstmt.setString(3, "Open");
				this.pstmt.execute();
				System.out.println("Added");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
