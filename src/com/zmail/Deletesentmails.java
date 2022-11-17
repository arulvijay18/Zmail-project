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
import org.json.JSONObject;

import com.zmaildao.Deletesentmaildata;
import com.zmaildao.Sentmailsdata;
import com.zmaildatastorage.Messageiddetails;


@WebServlet("/deletesentmails")
public class Deletesentmails extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session =request.getSession();
		
		if(session.getAttribute("userName")!=null ||session.getAttribute("userName")!="")
		{
			PrintWriter out=response.getWriter();
			try
			{
				response.setContentType("application/json; charset=UTF-8");
		        response.setCharacterEncoding("UTF-8");
				JSONArray message=new JSONArray();
				int messageId=Integer.parseInt(request.getParameter("messageid"));
				Messageiddetails sentMessageidSet=new Messageiddetails(messageId);
				boolean isMessageDeleted=Deletesentmaildata.deleteSentMail(sentMessageidSet);
				String userName=(String)session.getAttribute("userName");
				if(isMessageDeleted)
				{
					JSONArray sentMails=Sentmailsdata.getSentMails(userName);
					out.println(sentMails);
				}
				else
				{
					
					out.println(message);
				}
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
