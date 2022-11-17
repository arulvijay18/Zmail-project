package com.zmaildao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zmaildatastorage.Signupdetails;

public class Searchmaildata 
{
	public static JSONArray getMessage(String mailtype,String searchtext,String userName) 
	{
		
		Connection connect=Getdbconnection.getConnection();
		try
		{
		JSONArray messages=new JSONArray();
		PreparedStatement getUserid = connect.prepareStatement("select userid from userdetails where username=?");
		getUserid.setString(1,userName);
		ResultSet getUseridResult=getUserid.executeQuery();
		int userid=0;
		if(getUseridResult.next())
		{
			userid=getUseridResult.getInt("userid");
		}
		getUseridResult.close();
		getUserid.close();
		if(mailtype=="Sent mails" || mailtype.equals("Sent mails"))
		{
			String text=searchtext;
			boolean isNumber=false;
			int messageId=0;
			for(int i=0;i<text.length();i++)
			{
				if(Character.isDigit(text.charAt(i)))
				{
					isNumber=true;
				}
				else
				{
					isNumber=false;
					break;
				}
			}
			if(isNumber)
			{
				messageId=Integer.parseInt(text);
				PreparedStatement searchStatement=connect.prepareStatement("select from_mailids.message_id,userdetails.username,mails_details.subject,mails_details.message,mails_details.attachments from from_mailids inner join from_to_mailids on from_to_mailids.from_id=from_mailids.from_id inner join userdetails on userdetails.userid=from_to_mailids.to_id inner join mails_details on mails_details.message_id=from_mailids.message_id where from_mailids.from_id=? and from_to_mailids.message_id=mails_details.message_id and from_mailids.message_id=? and from_to_mailids.message_id=?");
				searchStatement.setInt(1,userid);
				searchStatement.setInt(2,messageId);
				searchStatement.setInt(3,messageId);
				ResultSet getMessage=searchStatement.executeQuery();
				int message_id=0;
				while(getMessage.next())
				{
					JSONObject messageObject=new JSONObject();
					String isattachmentsexists=getMessage.getString("attachments");
					String issubjectexists=getMessage.getString("subject");
					String ismessageexists=getMessage.getString("message");
					String subject="";
					String message="";
					String attachments="";
					message_id=getMessage.getInt("message_id");
					messageObject.put("MessageId",message_id);
					messageObject.put("ReceiverId",getMessage.getString("username"));
					
					
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
					
					messageObject.put("Subject",subject);
					messageObject.put("Message",message);
					messageObject.put("Attachments",attachments);
					messages.put(messageObject);
				}
				if(message_id==0)
				{
					return messages;
				}
				getMessage.close();
				searchStatement.close();
				return messages;
			}
			else
			{
				if(searchtext.contains("@zmail.com"))
				{
					String messagetext=searchtext;
					PreparedStatement searchStatement=connect.prepareStatement("select from_mailids.message_id,userdetails.username,mails_details.subject,mails_details.message,mails_details.attachments from from_mailids inner join from_to_mailids on from_to_mailids.from_id=from_mailids.from_id inner join userdetails on userdetails.userid=from_to_mailids.to_id inner join mails_details on mails_details.message_id=from_mailids.message_id where from_mailids.from_id=? and from_to_mailids.message_id=mails_details.message_id and userdetails.username=?");
					searchStatement.setInt(1,userid);
					searchStatement.setString(2,messagetext);
					
					ResultSet getMessage=searchStatement.executeQuery();
					int message_id=0;
					while(getMessage.next())
					{
						JSONObject messageObject=new JSONObject();
						String isattachmentsexists=getMessage.getString("attachments");
						String issubjectexists=getMessage.getString("subject");
						String ismessageexists=getMessage.getString("message");
						String subject="";
						String message="";
						String attachments="";
						message_id=getMessage.getInt("message_id");
						messageObject.put("MessageId",message_id);
						messageObject.put("ReceiverId",getMessage.getString("username"));
						
						
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
						
						messageObject.put("Subject",subject);
						messageObject.put("Message",message);
						messageObject.put("Attachments",attachments);
						messages.put(messageObject);
					}
					if(message_id==0)
					{
						return messages;
					}
					getMessage.close();
					searchStatement.close();
					return messages;
				}
				else
				{
					String getmessagetext=searchtext;
					String messagetext="";
					for(int i=0;i<getmessagetext.length();i++)
					{
						messagetext+="\\"+getmessagetext.charAt(i);
						
					}
					PreparedStatement searchStatement=connect.prepareStatement("select from_mailids.message_id,userdetails.username,mails_details.subject,mails_details.message,mails_details.attachments from from_mailids inner join from_to_mailids on from_to_mailids.from_id=from_mailids.from_id inner join userdetails on userdetails.userid=from_to_mailids.to_id inner join mails_details on mails_details.message_id=from_mailids.message_id where from_mailids.from_id=? and from_to_mailids.message_id=mails_details.message_id and message ilike ?");
					searchStatement.setInt(1,userid);
					searchStatement.setString(2,"%"+messagetext+"%");
					
					ResultSet getMessage=searchStatement.executeQuery();
					int message_id=0;
					while(getMessage.next())
					{
						JSONObject messageObject=new JSONObject();
						String isattachmentsexists=getMessage.getString("attachments");
						String issubjectexists=getMessage.getString("subject");
						String ismessageexists=getMessage.getString("message");
						String subject="";
						String message="";
						String attachments="";
						message_id=getMessage.getInt("message_id");
						messageObject.put("MessageId",message_id);
						messageObject.put("ReceiverId",getMessage.getString("username"));
						
						
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
						
						messageObject.put("Subject",subject);
						messageObject.put("Message",message);
						messageObject.put("Attachments",attachments);
						messages.put(messageObject);
					}
					if(message_id==0)
					{
						return messages;
					}
					getMessage.close();
					searchStatement.close();
					return messages;
					
				}
			}
			
		}
		else
		{
			String text=searchtext;
			boolean isNumber=false;
			int messageId=0;
			for(int i=0;i<text.length();i++)
			{
				if(Character.isDigit(text.charAt(i)))
				{
					isNumber=true;
				}
				else
				{
					isNumber=false;
				}
			}
			if(isNumber)
			{
				messageId=Integer.parseInt(text);
				PreparedStatement searchStatement=connect.prepareStatement("select to_mailids.message_id,userdetails.username,mails_details.subject,mails_details.message,mails_details.attachments from to_mailids inner join from_to_mailids on from_to_mailids.to_id=to_mailids.to_id inner join userdetails on userdetails.userid=from_to_mailids.from_id inner join mails_details on mails_details.message_id=to_mailids.message_id where to_mailids.to_id=? and from_to_mailids.message_id=mails_details.message_id and to_mailids.message_id=? and from_to_mailids.message_id=?");
				searchStatement.setInt(1,userid);
				searchStatement.setInt(2,messageId);
				searchStatement.setInt(3,messageId);
				ResultSet getMessage=searchStatement.executeQuery();
				int message_id=0;
				while(getMessage.next())
				{
					JSONObject messageObject=new JSONObject();
					String isattachmentsexists=getMessage.getString("attachments");
					String issubjectexists=getMessage.getString("subject");
					String ismessageexists=getMessage.getString("message");
					String subject="";
					String message="";
					String attachments="";
					message_id=getMessage.getInt("message_id");
					messageObject.put("MessageId",message_id);
					messageObject.put("SenderId",getMessage.getString("username"));
					
					
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
					
					messageObject.put("Subject",subject);
					messageObject.put("Message",message);
					messageObject.put("Attachments",attachments);
					messages.put(messageObject);
				}
				if(message_id==0)
				{
					return messages;
				}
				getMessage.close();
				searchStatement.close();
				return messages;
			}
			else
			{
				if(searchtext.contains("@zmail.com"))
				{
					String messagetext=searchtext;
					PreparedStatement searchStatement=connect.prepareStatement("select to_mailids.message_id,userdetails.username,mails_details.subject,mails_details.message,mails_details.attachments from to_mailids inner join from_to_mailids on from_to_mailids.to_id=to_mailids.to_id inner join userdetails on userdetails.userid=from_to_mailids.from_id inner join mails_details on mails_details.message_id=to_mailids.message_id where to_mailids.to_id=? and from_to_mailids.message_id=to_mailids.message_id and userdetails.username=?");
					searchStatement.setInt(1,userid);
					searchStatement.setString(2,messagetext);
					
					ResultSet getMessage=searchStatement.executeQuery();
					int message_id=0;
					while(getMessage.next())
					{
						JSONObject messageObject=new JSONObject();
						String isattachmentsexists=getMessage.getString("attachments");
						String issubjectexists=getMessage.getString("subject");
						String ismessageexists=getMessage.getString("message");
						String subject="";
						String message="";
						String attachments="";
						message_id=getMessage.getInt("message_id");
						messageObject.put("MessageId",message_id);
						messageObject.put("SenderId",getMessage.getString("username"));
						
						
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
						
						messageObject.put("Subject",subject);
						messageObject.put("Message",message);
						messageObject.put("Attachments",attachments);
						messages.put(messageObject);
					}
					if(message_id==0)
					{
						return messages;
					}
					getMessage.close();
					searchStatement.close();
					return messages;
				}
				else
				{
					String getmessagetext=searchtext;
					String messagetext="";
					for(int i=0;i<getmessagetext.length();i++)
					{
						if(i==getmessagetext.length()-1)
						{
							messagetext+=getmessagetext.charAt(i);
						}
						else
						{
							messagetext+=getmessagetext.charAt(i)+"\\";
						}
					}
					PreparedStatement searchStatement=connect.prepareStatement("select to_mailids.message_id,userdetails.username,mails_details.subject,mails_details.message,mails_details.attachments from to_mailids inner join from_to_mailids on from_to_mailids.to_id=to_mailids.to_id inner join userdetails on userdetails.userid=from_to_mailids.from_id inner join mails_details on mails_details.message_id=to_mailids.message_id where to_mailids.to_id=? and from_to_mailids.message_id=mails_details.message_id and message ilike ?");
					searchStatement.setInt(1,userid);
					searchStatement.setString(2,"%"+messagetext+"%");
					
					ResultSet getMessage=searchStatement.executeQuery();
					int message_id=0;
					while(getMessage.next())
					{
						JSONObject messageObject=new JSONObject();
						String isattachmentsexists=getMessage.getString("attachments");
						String issubjectexists=getMessage.getString("subject");
						String ismessageexists=getMessage.getString("message");
						String subject="";
						String message="";
						String attachments="";
						message_id=getMessage.getInt("message_id");
						messageObject.put("MessageId",message_id);
						messageObject.put("SenderId",getMessage.getString("username"));
						
						
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
						
						messageObject.put("Subject",subject);
						messageObject.put("Message",message);
						messageObject.put("Attachments",attachments);
						messages.put(messageObject);
					}
					if(message_id==0)
					{
						return messages;
					}
					getMessage.close();
					searchStatement.close();
					return messages;
					
				}
			}
		}
		}
		catch(Exception e)
		{
			JSONArray emptyArray=new JSONArray();
			return emptyArray;
		}
		
	}
}
