package com.zmaildao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zmaildatastorage.Messageiddetails;

public class Deletesentmaildata 
{
	public static boolean deleteSentMail(Messageiddetails getmessageId) 
	{
		try
		{
			Connection connect=Getdbconnection.getConnection();
			
			int messageId=getmessageId.getMessageId();
			PreparedStatement deleteSentMails=connect.prepareStatement("delete from from_mailids where message_id=?");
			deleteSentMails.setInt(1,messageId);
			int deleteSentMailsexecution=deleteSentMails.executeUpdate();
			if(deleteSentMailsexecution==0)
			{
				return false;
			}
			deleteSentMails.close();
			connect.close();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
