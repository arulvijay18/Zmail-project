package com.zmail;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zmaildao.Signupvalidate;
import com.zmaildatastorage.Signupdetails;


@WebServlet("/signup")
public class Signup extends HttpServlet 
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
			String password=request.getParameter("password");
			Pattern passwordpattern = Pattern.compile("^(?=.*[*.!@$%^&(){}\\[\\]:;<>,.?/~_+-=|]).{8,}$");
		    Matcher passwordmatcher = passwordpattern.matcher(password);
		    boolean passwordValidation = passwordmatcher.find();
			String mobileno=request.getParameter("mobileno");
			Pattern mobileNopattern = Pattern.compile("[0-9]{10}");
		    Matcher mobileNomatcher = mobileNopattern.matcher(mobileno);
		    boolean mobileNoValidation = mobileNomatcher.find();
		    if(userNameValidation && passwordValidation && mobileNoValidation)
		    {
		    	long mobileNo=Long.parseLong(mobileno);
		    	Signupdetails signup=new Signupdetails(userName,password,mobileNo);
				boolean isUserDetailsInserted=Signupvalidate.insertDetails(signup);
				if(isUserDetailsInserted)
				{
					request.getRequestDispatcher("login.jsp").include(request,response);
					out.println("<h4 style='margin-left:580px;margin-top:-45px;color:green'>Account Created Successfully</h4>");
				}
				else
				{
					request.getRequestDispatcher("signup.jsp").include(request,response);
					out.println("<h4 style='margin-left:530px;margin-top:-60px;color:red'>Account creation failed</h4>");
				}
					
					
			 } 
		}
		catch (Exception e) 
		{
			request.getRequestDispatcher("signup.jsp").include(request,response);
			out.println("<h4 style='margin-left:480px;margin-top:-60px;color:red'>Error occured while adding user</h4>");
		}
		out.close();
	}

}
