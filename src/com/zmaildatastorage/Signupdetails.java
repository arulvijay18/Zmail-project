package com.zmaildatastorage;

public class Signupdetails
{
	private String userName,password;
	private long mobileno;
	
	public Signupdetails(String userName,String password,long mobileno)
	{
		this.userName=userName;
		this.password=password;
		this.mobileno=mobileno;
	}
	public Signupdetails(String userName,String password)
	{
		this.userName=userName;
		this.password=password;
	}
	public Signupdetails(String userName) 
	{
		this.userName=userName;
	}
	public String getUserName() 
	{
		return userName;
	}
	public String getPassword() 
	{
		return password;
	}
	public long getMobileno() {
		return mobileno;
	}
	
}

