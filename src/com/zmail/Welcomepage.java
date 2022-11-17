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

import com.zmaildao.Welcomepagedata;
import com.zmaildatastorage.Signupdetails;


@WebServlet("/welcomepage")
public class Welcomepage extends HttpServlet 
{
	
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
				    String userName=(String)session.getAttribute("userName");
					JSONArray receivedMails=Welcomepagedata.getData(userName);
					out.println(receivedMails);
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
