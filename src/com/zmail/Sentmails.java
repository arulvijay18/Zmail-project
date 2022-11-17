package com.zmail;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import com.zmaildao.Sentmailsdata;
import com.zmaildatastorage.Maildetails;
import com.zmaildatastorage.Signupdetails;


@WebServlet("/sentmails")
public class Sentmails extends HttpServlet 
{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session =request.getSession();
		
		if(session.getAttribute("userName")!=null ||session.getAttribute("userName")!="")
		{
			response.setContentType("application/json; charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
			PrintWriter out=response.getWriter();
			String userName=(String)session.getAttribute("userName");
			try
			{
				JSONArray getSentMails=Sentmailsdata.getSentMails(userName);
				out.println(getSentMails);
			}
			catch(Exception e)
			{
				out.println(e);
			}
			out.close();
		}
		else
		{
			response.sendRedirect("login.jsp");
		}
	}
}
