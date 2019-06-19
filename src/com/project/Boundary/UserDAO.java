package com.project.Boundary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.project.Entity.User_Group2;

public class UserDAO {
	
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
	
public ArrayList<User_Group2> getUsers()	{
		
		ArrayList<User_Group2> ul = new ArrayList<User_Group2>();
		
		String sql = "SELECT * FROM user";
		
		try {
			ConnectDB();
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
		while (rs.next())	{
			User_Group2 nu = new User_Group2();
			
			nu.setUser_id(rs.getInt("user_id"));
			nu.setFirst_name(rs.getString("first_name"));
			nu.setLast_name(rs.getString("last_name"));
			nu.setEmail(rs.getString("email"));
			nu.setAddress(rs.getString("address"));
			nu.setPhone_number(rs.getString("phone_number"));
			
			ul.add(nu);
		
		}
		
		DisconnectDB();
			
		} catch (SQLException sx)	{
			System.out.println("Error Connecting to database");
			System.out.println(sx.getMessage());
			System.out.println(sx.getErrorCode());
			System.out.println(sx.getSQLState());
			}
		
		
		return ul;
	}

	
}
