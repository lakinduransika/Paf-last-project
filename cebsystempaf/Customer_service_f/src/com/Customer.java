package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


public class Customer {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cebsystem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertCustomer(String c_name, String c_address, String c_email, String c_nic, String c_phone)  
	{   
		String output = ""; 	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into customf(`C_ID`,`c_name`,`c_address`,`c_email`,`c_nic`,`c_phone`)" + " values (?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, c_name);
			 preparedStmt.setString(3, c_address);
			 preparedStmt.setString(4, c_email);
			 preparedStmt.setString(5, c_nic);
			 preparedStmt.setString(6, c_phone);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newCustomer = readCustomer(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Customer.\"}";  
			System.err.println(e.getMessage());   
		} 		
	  return output;  
	} 	
	
	public String readCustomer()  
	{   
		String output = ""; 
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Customer Name</th><th>Address</th><th>Email</th><th>NIC</th><th>Phone No</th><th>Update</th><th>Remove</th></tr>";
	 
			String query = "select * from customf";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				 String C_ID = Integer.toString(rs.getInt("C_ID"));
				 String c_name = rs.getString("c_name");
				 String c_address = rs.getString("c_address");
				 String c_email = rs.getString("c_email");
				 String c_nic = rs.getString("c_nic");
				 String c_phone = rs.getString("c_phone");
				 
				// Add into the html table 
				output += "<tr><td><input id=\'hidCustomerIDUpdate\' name=\'hidCustomerIDUpdate\' type=\'hidden\' value=\'" + C_ID + "'>" 
							+ c_name + "</td>"; 
				output += "<td>" + c_address + "</td>";
				output += "<td>" + c_email + "</td>";
				output += "<td>" + c_nic + "</td>";
				output += "<td>" + c_phone + "</td>";
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-customerid='" + C_ID + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	   
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Customer.";    
			System.err.println(e.getMessage());   
		} 	 
		return output;  
	}
	
	public String updateCustomer(String C_ID, String c_name, String c_address, String c_email, String c_nic, String c_phone)  
	{   
		String output = "";  
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE customf SET c_name=?,c_address=?,c_email=?,c_nic=?,c_phone=?"  + "WHERE C_ID=?";  	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, c_name);
			 preparedStmt.setString(2, c_address);
			 preparedStmt.setString(3, c_email);
			 preparedStmt.setString(4, c_nic);
			 preparedStmt.setString(5, c_phone);
			 preparedStmt.setInt(6, Integer.parseInt(C_ID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close();  
			String newCustomer = readCustomer();    
			output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Customer.\"}";   
			System.err.println(e.getMessage());   
		} 	 
	  return output;  
	} 
	
	public String deleteCustomer(String C_ID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 			
			} 
	 
			// create a prepared statement    
			String query = "delete from customf where C_ID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(C_ID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newCustomer = readCustomer();    
			output = "{\"status\":\"success\", \"data\": \"" +  newCustomer + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the Customer.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}
