package com.zmail;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/downloadservlet")
public class Downloadfiles extends HttpServlet 
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		HttpSession session =request.getSession();
		
		
		if(session.getAttribute("userName")!=null ||session.getAttribute("userName")!="")
		{
			PrintWriter out = response.getWriter();
			try
			{
				request.setCharacterEncoding("UTF-8");
		        response.setContentType("text/html; charset=UTF-8");
		        response.setCharacterEncoding("UTF-8");
			
			String getFile=request.getParameter("fileName");
			  
			  String filepath = "D:\\HTML\\JAVA PROJECTS\\Zmail\\WebContent\\uploaded files\\"; 
	 
			  response.setContentType("APPLICATION/OCTET-STREAM"); 
			  response.setHeader("Content-Disposition","attachment; filename=\"" + getFile + "\""); 
	
			  java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filepath + getFile);
			  
			  int i; 
			  while ((i=fileInputStream.read()) != -1) {
			    out.write(i); 
			  } 
			  fileInputStream.close(); 
			  out.close();
			}
			catch(Exception e)
			{
				
				out.println("Error occured while downloading");
			}
			out.close();
		}
		else
		{
			response.sendRedirect("login.jsp");
		}
	}

}
