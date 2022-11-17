package com.zmaildao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Passwordresetdata 
{
	public static boolean checkDetails(String username,long mobileno) 
	{
		boolean status=false;
		Connection connect=Getdbconnection.getConnection();
		try
		{
			PreparedStatement getMobileno=connect.prepareStatement("select mobileno from userdetails where username=?");
			getMobileno.setString(1,username);
			ResultSet mobilenoresult=getMobileno.executeQuery();
			long mobileNo=0;
			if(mobilenoresult.next())
			{
				mobileNo=mobilenoresult.getLong(1);
			}
			if(mobileNo==mobileno)
			{
				status=true;
			}
			else
			{
				status=false;
			}
			mobilenoresult.close();
			getMobileno.close();
			connect.close();
			return status;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public static boolean updatePassword(String username,String password) throws SQLException
	{
		boolean status=false;
		Connection connect=Getdbconnection.getConnection();
		try
		{
			PreparedStatement updatePasswordStatement=connect.prepareStatement("update userdetails set password=? where username=?");
			updatePasswordStatement.setString(1,password);
			updatePasswordStatement.setString(2,username);
			int isUpdated=updatePasswordStatement.executeUpdate();
			if(isUpdated==0)
			{
				status=false;
			}
			else
			{
				status=true;
			}
			updatePasswordStatement.close();
			connect.close();
			return status;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
