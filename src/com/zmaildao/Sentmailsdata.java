package com.zmaildao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zmaildatastorage.Signupdetails;

public class Sentmailsdata 
{
	public static JSONArray getSentMails(String userName) 
	{
		Connection connect=Getdbconnection.getConnection();
		try
		{
		
			JSONArray sentMailsArray=new JSONArray();
			PreparedStatement getUseridStatement = connect.prepareStatement("select userid from userdetails where username=?");
			getUseridStatement.setString(1,userName);
			ResultSet getUseridResult=getUseridStatement.executeQuery();
			int userid=0;
			if(getUseridResult.next())
			{
				userid=getUseridResult.getInt("userid");
			}
			getUseridResult.close();
			getUseridStatement.close();
			PreparedStatement selectSentMails=connect.prepareStatement("select from_mailids.message_id,userdetails.username,mails_details.subject,mails_details.message,mails_details.attachments from from_mailids inner join from_to_mailids on from_to_mailids.from_id=from_mailids.from_id inner join userdetails on userdetails.userid=from_to_mailids.to_id inner join mails_details on mails_details.message_id=from_mailids.message_id where from_mailids.from_id=? and from_to_mailids.message_id=mails_details.message_id");
			selectSentMails.setInt(1,userid);
			ResultSet selectSentMailsresult=selectSentMails.executeQuery();
			int message_id=0;
			while(selectSentMailsresult.next())
			{
				JSONObject sentMailsObject=new JSONObject();
				String isattachmentsexists=selectSentMailsresult.getString("attachments");
				String issubjectexists=selectSentMailsresult.getString("subject");
				String ismessageexists=selectSentMailsresult.getString("message");
				String subject="";
				String message="";
				String attachments="";
				message_id=selectSentMailsresult.getInt("message_id");
				sentMailsObject.put("MessageId",message_id);
				sentMailsObject.put("ReceiverId",selectSentMailsresult.getString("username"));
				
				
				if(isattachmentsexists==null)
				{
					attachments="*";
				}
				else
				{
					attachments=isattachmentsexists;
				}
				if(issubjectexists==null)
				{
					subject="No message";
				}
				else
				{
					subject=issubjectexists;
				}
				if(ismessageexists==null)
				{
					message="No message";
				}
				else
				{
					message=ismessageexists;
				}
				
				sentMailsObject.put("Subject",subject);
				sentMailsObject.put("Message",message);
				sentMailsObject.put("Attachments",attachments);
				sentMailsArray.put(sentMailsObject);
			}
			if(message_id==0)
		    {
		    	return sentMailsArray;
		    }
			selectSentMailsresult.close();
			selectSentMails.close();
			return sentMailsArray;
			}
		catch(Exception e)
		{
			JSONArray empty=new JSONArray();
			return empty;
		}
	}
}
