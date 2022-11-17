package com.zmail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zmaildao.Passwordresetdata;


@WebServlet("/passwordreset")
public class Passwordreset extends HttpServlet 
{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		try
		{
			response.setContentType("text/html; charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
			String username=request.getParameter("userName");
			Pattern userNamepattern=Pattern.compile("[a-zA-Z0-9]+@zmail.com");
			Matcher userNamematcher=userNamepattern.matcher(username);
			boolean userNameValidation=userNamematcher.find();
			String mobileno=request.getParameter("mobileNo");
			Pattern mobileNopattern = Pattern.compile("[0-9]{10}");
		    Matcher mobileNomatcher = mobileNopattern.matcher(mobileno);
		    boolean mobileNoValidation = mobileNomatcher.find();
		    if(userNameValidation && mobileNoValidation)
		    {
		    	long  mobileNo=Long.parseLong(mobileno);
				boolean isValidated=Passwordresetdata.checkDetails(username, mobileNo);
				
				if(isValidated)
				{
					out.println("validated");
				}
				else
				{
					out.println("validation failed");
				}
		    }
		    else
		    {
		    	out.println("Username and mobile no did not match");
		    }
		}
		catch(Exception e)
		{
			out.println("Username and mobile no did not match");
		}
		out.close();
	}

}
