package com.zmaildao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zmaildatastorage.Signupdetails;

public class Welcomepagedata 
{
	public static JSONArray getData(String userName) 
	{
		Connection connect=Getdbconnection.getConnection();
		try
		{
		JSONArray mails=new JSONArray();
		PreparedStatement getUserid = connect.prepareStatement("select userid from userdetails where username=?");
		getUserid.setString(1,userName);
		int userid=0;
		ResultSet getUseridResult=getUserid.executeQuery();
		if(getUseridResult.next())
		{
			userid=getUseridResult.getInt("userid");
		}
		getUseridResult.close();
		getUserid.close();
		PreparedStatement selectReceivedMails=connect.prepareStatement("select to_mailids.message_id,userdetails.username,mails_details.subject,mails_details.message,mails_details.attachments from to_mailids inner join from_to_mailids on from_to_mailids.to_id=to_mailids.to_id inner join userdetails on userdetails.userid=from_to_mailids.from_id inner join mails_details on mails_details.message_id=to_mailids.message_id where to_mailids.to_id=? and from_to_mailids.message_id=mails_details.message_id");
		selectReceivedMails.setInt(1,userid);
		ResultSet selectReceivedMailsresult=selectReceivedMails.executeQuery();
		int message_id=0;
		while(selectReceivedMailsresult.next())
		{
			JSONObject receivedMailsObject=new JSONObject();
			String isattachmentsexists=selectReceivedMailsresult.getString("attachments");
			String issubjectexists=selectReceivedMailsresult.getString("subject");
			String ismessageexists=selectReceivedMailsresult.getString("message");
			String subject="";
			String message="";
			String attachments="";
			message_id=selectReceivedMailsresult.getInt("message_id");
			receivedMailsObject.put("MessageId",message_id);
			receivedMailsObject.put("SenderId",selectReceivedMailsresult.getString("username"));
			
			
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
			
			receivedMailsObject.put("Subject",subject);
			receivedMailsObject.put("Message",message);
			receivedMailsObject.put("Attachments",attachments);
			mails.put(receivedMailsObject);
		}
	    if(message_id==0)
	    {
	    	return mails;
	    }
	    selectReceivedMailsresult.close();
	    selectReceivedMails.close();
	    return mails;
		}
		catch(Exception e)
		{
			JSONArray emptyArray=new JSONArray();
			return emptyArray;
		}
	}
}
