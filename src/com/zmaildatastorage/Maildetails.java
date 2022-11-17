package com.zmaildatastorage;

public class Maildetails 
{
	private String toMailid,subject,message,fileName;
	public Maildetails(String toMailid,String subject,String message,String fileName)
	{
		this.toMailid=toMailid;
		this.subject=subject;
		this.message=message;
		this.fileName=fileName;
	}
	public String getToMailid() 
	{
		return toMailid;
	}
	public String getSubject() 
	{
		return subject;
	}

	public String getMessage() 
	{
		return message;
	}
	public  String getFileName() {
		return fileName;
	}

}
