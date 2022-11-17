package com.zmail;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zmaildao.Loginvalidate;
import com.zmaildatastorage.Signupdetails;


@WebServlet("/login")
public class Login extends HttpServlet 
{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
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
		   if(userNameValidation && passwordValidation)
		    {
				Signupdetails login=new Signupdetails(userName,password);
				boolean loginStatus=Loginvalidate.loginValidate(login);
				if(loginStatus)
				{
					session.setAttribute("userName",userName);
					out.println("Login success");
				}
				else
				{
					out.println("Incorrect username or password");
				}
		    }
		    else
		    {
		    	out.println("Incorrect username or password");
		    }
			
		} 
		catch (Exception e) 
		{
			out.println("Incorrect username or password");
		}
		out.close();
	}

}
