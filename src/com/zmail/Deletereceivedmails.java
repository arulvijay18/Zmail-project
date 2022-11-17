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

import com.zmaildao.Deletereceivedmaildata;
import com.zmaildao.Deletesentmaildata;
import com.zmaildao.Sentmailsdata;
import com.zmaildao.Welcomepagedata;
import com.zmaildatastorage.Messageiddetails;


@WebServlet("/deletereceivedmails")
public class Deletereceivedmails extends HttpServlet 
{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session =request.getSession();
		
		if(session.getAttribute("userName")!=null ||session.getAttribute("userName")!="")
		{
			PrintWriter out=response.getWriter();
			JSONArray message=new JSONArray();
			try
			{
				response.setContentType("application/json; charset=UTF-8");
		        response.setCharacterEncoding("UTF-8");
				
				int messageId=Integer.parseInt(request.getParameter("messageid"));
				Messageiddetails messageidSet=new Messageiddetails(messageId);
				boolean isMessageDeleted=Deletereceivedmaildata.deleteReceivedMails(messageidSet);
				String userName=(String)session.getAttribute("userName");
				if(isMessageDeleted)
				{
					JSONArray receivedMails=Welcomepagedata.getData(userName);
					out.println(receivedMails);
				}
				else
				{
					
					out.println(message);
				}
			}
			catch(Exception e)
			{
				out.println(message);
			}
			out.close();
		}
		else
		{
			response.sendRedirect("login.jsp");
		}
		
	}

}
