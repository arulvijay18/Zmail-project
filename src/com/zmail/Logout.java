package com.zmail;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zmaildatastorage.Signupdetails;


@WebServlet("/logout")
public class Logout extends HttpServlet 
{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		try
		{
		HttpSession session=request.getSession();
		session.removeAttribute("userName");
		session.invalidate();
		request.setAttribute("message","loggedout");
		request.getRequestDispatcher("login.jsp").forward(request,response);
		}
		catch(Exception e)
		{
			response.sendRedirect("login.jsp");
		}
		out.close();
	}

}
