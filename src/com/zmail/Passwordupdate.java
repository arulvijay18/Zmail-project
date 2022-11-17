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


@WebServlet("/forgotpassword")
public class Passwordupdate extends HttpServlet 
{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		try
		{
			response.setContentType("text/html; charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
			String userName=request.getParameter("userName");
			Pattern userNamepattern=Pattern.compile("[a-zA-Z0-9]+@zmail.com");
			Matcher userNamematcher=userNamepattern.matcher(userName);
			boolean userNameValidation=userNamematcher.find();
			String password=request.getParameter("password");
			Pattern passwordpattern = Pattern.compile("^(?=.*[*.!@$%^&(){}\\[\\]:;<>,.?/~_+-=|]).{8,}$\"");
		    Matcher passwordmatcher = passwordpattern.matcher(password);
		    boolean passwordValidation = passwordmatcher.find();
		    if(userNameValidation && passwordValidation)
		    {
				boolean isUpdated=Passwordresetdata.updatePassword(userName, password);
				if(isUpdated)
				{
					out.println("Password changed successfully");
				}
				else
				{
					out.println("Password update failed");
				}
		    }
		    else
		    {
		    	out.println("Password update failed");
		    }
		}
		catch(Exception e)
		{
			out.println("password update failed");
		}
		out.close();
	}

}
