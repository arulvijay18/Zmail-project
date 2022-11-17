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

import com.zmaildao.Signupvalidate;
import com.zmaildatastorage.Signupdetails;


@WebServlet("/usernameExistence")
public class UsernameExistence extends HttpServlet 
{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        try
		{
			String userName=request.getParameter("username");
			Pattern userNamepattern=Pattern.compile("[a-zA-Z0-9]+@zmail.com");
			Matcher userNamematcher=userNamepattern.matcher(userName);
			boolean userNameValidation=userNamematcher.find();
			
			if(userNameValidation)
			{
				Signupdetails userNamecheck=new Signupdetails(userName);
				boolean isUserNameAlreadyExists=Signupvalidate.isuserNameExist(userNamecheck);
				if(!isUserNameAlreadyExists)
				{
					out.println("This username already exists,pls try different username");
				}
			}
		}
		catch(Exception e)
		{
			out.println("Error occured");
		}
        out.close();
	}

}
