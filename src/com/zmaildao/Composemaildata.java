package com.zmaildao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zmaildatastorage.Maildetails;
import com.zmaildatastorage.Signupdetails;

public class Composemaildata 
{
	public static boolean compose(Maildetails maildetails,String userName) throws Exception 
	{
		Connection connect=Getdbconnection.getConnection();
		try
		{
			boolean msgSent=false;
			boolean isConfirmed=false;
			String toMailId=maildetails.getToMailid();
			String subject=maildetails.getSubject();
			String message=maildetails.getMessage();
			String files=maildetails.getFileName();
			
			PreparedStatement getuserid = connect.prepareStatement("select userid from userdetails where username=?");
			getuserid.setString(1,userName);
			ResultSet getuseridresult=getuserid.executeQuery();
			int from_id=0;
			if(getuseridresult.next())
			{
				from_id=getuseridresult.getInt("userid");
			}
			getuseridresult.close();
			getuserid.close();
			PreparedStatement insertstatement=connect.prepareStatement("insert into mails_details values(nextval('message_id_sequence'),?,?,?)");
			insertstatement.setString(1,subject);
			insertstatement.setString(2,message);
			insertstatement.setString(3,files);
			int insertexecution = insertstatement.executeUpdate();
			insertstatement.close();
			PreparedStatement getMessageid=connect.prepareStatement("select message_id from mails_details order by message_id desc limit 1");
			ResultSet getMessageidresult=getMessageid.executeQuery();
			int messageId=0;
			if(getMessageidresult.next())
			{
				messageId=getMessageidresult.getInt("message_id");
			}
			getMessageidresult.close();
			getMessageid.close();
			PreparedStatement insertfromid=connect.prepareStatement("insert into from_mailids(message_id,from_id) values(?,?)");
			insertfromid.setInt(1,messageId);
			insertfromid.setInt(2,from_id);
			int fromidexecution=insertfromid.executeUpdate();
			insertfromid.close();
			String[] multiplemails=toMailId.split("@zmail.com");
			
			String checkEachMail="";
			PreparedStatement insertToMailId=connect.prepareStatement("insert into to_mailids values(?,?)");
			PreparedStatement insertFromToMailId=connect.prepareStatement("insert into from_to_mailids(message_id,from_id,to_id) values(?,?,?)");
			PreparedStatement get_to_id=null;
			for(int i=0;i<multiplemails.length;i++)
			{
				checkEachMail=multiplemails[i].trim();
				for(int j=0;j<checkEachMail.length();j++)
				{
					if(Character.isAlphabetic(checkEachMail.charAt(j)) || Character.isDigit(checkEachMail.charAt(j)))
					{
						checkEachMail=checkEachMail.substring(j,checkEachMail.length())+"@zmail.com";
						break;
					}
				}
				get_to_id=connect.prepareStatement("select userid from userdetails where username=?");
				get_to_id.setString(1,checkEachMail);
				
				ResultSet get_to_id_result=get_to_id.executeQuery();
				int to_id_exists=0;
				get_to_id_result.close();
				
				if(get_to_id_result.next())
				{
					to_id_exists=get_to_id_result.getInt("userid");
					
					if(to_id_exists==0)
					{
						msgSent=false;
					}
					else
					{
						msgSent=true;
						insertToMailId.setInt(1,messageId);
						insertToMailId.setInt(2,to_id_exists);
						int insertToMailExecution=insertToMailId.executeUpdate();
						insertFromToMailId.setInt(1,messageId);
						insertFromToMailId.setInt(2,from_id);
						insertFromToMailId.setInt(3,to_id_exists);
						int insertFromToMailExecution=insertFromToMailId.executeUpdate();
						
					}
					
				}
				if(msgSent)
				{
					isConfirmed=true;
				}
				
			}
			insertToMailId.close();
			insertFromToMailId.close();
			get_to_id.close();
			return isConfirmed;
		}
		catch(Exception e)
		{
			return false;
		}
		finally
		{
			connect.close();
		}
	}
}
