package com.zmaildao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zmaildatastorage.Messageiddetails;

public class Deletereceivedmaildata 
{
	public static boolean deleteReceivedMails(Messageiddetails messageid) 
	{
		try
		{
			Connection connect=Getdbconnection.getConnection();
			
			int messageId=messageid.getMessageId();
			PreparedStatement deleteReceivedMails=connect.prepareStatement("delete from to_mailids where message_id=?");
			deleteReceivedMails.setInt(1,messageId);
			int deleteReceivedMailsexecution=deleteReceivedMails.executeUpdate();
			if(deleteReceivedMailsexecution==0)
			{
				return false;
			}
			
			deleteReceivedMails.close();
			connect.close();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
