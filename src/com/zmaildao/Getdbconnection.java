package com.zmaildao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Getdbconnection 
{
	public static Connection getConnection()
	{
		Connection connection=null;
		try 
		{
			Class.forName("org.postgresql.Driver");
			connection=DriverManager.getConnection("jdbc:postgresql://localhost/zmaildb","postgres","Viratvijay@1898");
			
		} 
		catch (Exception e) 
		{
			System.out.println("Error occured");
		}
		return connection;
	}
}
