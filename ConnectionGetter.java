package com.servlets;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionGetter
{
	Connection con;
	public ConnectionGetter()
	{
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "hr", "admin" );
					
		
		} 
	catch (SQLException e)
	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	catch(ClassNotFoundException e)
	{
	    	System.out.println(e.getMessage());
	    }

}
	public Connection getConnection()
	{
		return con;
	}
	}
