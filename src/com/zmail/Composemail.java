package com.zmail;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zmaildao.Composemaildata;
import com.zmaildatastorage.Maildetails;


@WebServlet("/composemail")
public class Composemail extends HttpServlet 
{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session =request.getSession();
		
		if(session.getAttribute("userName")!=null ||session.getAttribute("userName")!="")
		{
			PrintWriter out=response.getWriter();
			
			try
			{
				response.setContentType("text/html; charset=UTF-8");
		        response.setCharacterEncoding("UTF-8");
				String toMailId=request.getParameter("tomailid");
				String subject=request.getParameter("subject");
				String message=request.getParameter("message");
				String fileName=(String)session.getAttribute("fileName");
				String userName=(String)session.getAttribute("userName");
				if(toMailId==null ||toMailId=="")
				{
					out.println("to Mailid can't be empty");
				}
				else
				{
					Maildetails maildetails=new Maildetails(toMailId,subject,message,fileName);
					session.removeAttribute("fileName");
					boolean isMessageSent=Composemaildata.compose(maildetails,userName);
					System.out.println(isMessageSent);
					if(isMessageSent)
					{
						out.println("Message sent successfully!!!");
					}
					else
					{
						out.println("Message not sent!!!");
					}
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
				out.println("Message not sent!!!");
			}
			out.close();
		}
		else
		{
			response.sendRedirect("login.jsp");
		}
	}

}
