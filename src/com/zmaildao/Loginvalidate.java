package com.zmaildao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zmaildatastorage.Signupdetails;

public class Loginvalidate 
{
	public static boolean loginValidate(Signupdetails login) 
	{
		
		Connection connection=Getdbconnection.getConnection();
		try
		{
			PreparedStatement getLoginDetails=connection.prepareStatement("Select password from userdetails where username=?");
			getLoginDetails.setString(1,login.getUserName());
			ResultSet getPassword=getLoginDetails.executeQuery();
			String password=null;
			if(getPassword.next())
			{
				password=getPassword.getString(1);
			}
			String enteredPassword=login.getPassword();
			getPassword.close();
			getLoginDetails.close();
			connection.close();
			if(enteredPassword.equals(password))
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
