package com.zmaildao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zmaildatastorage.Signupdetails;

public class Signupvalidate 
{
	public static boolean isuserNameExist(Signupdetails userNamecheck) throws SQLException
	{
		Connection connect=Getdbconnection.getConnection();
		
		String userName=userNamecheck.getUserName();
		PreparedStatement getUserName=connect.prepareStatement("select username from userdetails where username=?");
		getUserName.setString(1,userName);
		ResultSet isExists=getUserName.executeQuery();
		isExists.close();
		getUserName.close();
		connect.close();
		if(isExists.next())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public static boolean insertDetails(Signupdetails signup) 
	{
		Connection connect=Getdbconnection.getConnection();
		try
		{
		PreparedStatement insertUserdetails=connect.prepareStatement("insert into userdetails(username,password,mobileno) values(?,?,?)");
		insertUserdetails.setString(1,signup.getUserName());
		insertUserdetails.setString(2,signup.getPassword());
		insertUserdetails.setLong(3,signup.getMobileno());
		int status=insertUserdetails.executeUpdate();
		insertUserdetails.close();
		connect.close();
		if(status>0)
		{
			return true;
		}
		else
		{
			return false;
		}
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
