package com.zmail;

import java.io.IOException;
import com.zmaildatastorage.Maildetails;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.List;
import java.util.Iterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;

import com.oreilly.servlet.MultipartRequest;
@MultipartConfig

@WebServlet("/uploadfiles")
public class Uploadfiles extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session =request.getSession();
		
		if(session.getAttribute("userName")!=null ||session.getAttribute("userName")!="")
		{
			response.setContentType("text/html; charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
			PrintWriter out=response.getWriter();
			
			String files="";
			int count=0;
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (!isMultipart) 
			{
				out.println("file not uploaded");
			}
			else
			{
				
			     FileItemFactory factory = new DiskFileItemFactory();
			     
			     ServletFileUpload upload = new ServletFileUpload(factory);
			     List items = null;
			     try 
			     {
			         items = upload.parseRequest(request);
			     } 
			     catch (FileUploadException e) 
			     {
			          e.printStackTrace();
			     }
			     if(items.size()>5)
			     {
			    	 out.println("No of files exceeded the limit");
			     }
			     else
			     {
			    	 Iterator itr = items.iterator();
				     while (itr.hasNext()) 
				     {
					     FileItem item = (FileItem) itr.next();
					     if(item.getSize()>1024*1024*2)
					     {
					    	 	 out.println(item.getName()+" exceeded the limit of allowed file size");
					    	 
					     }
					     else
					     {
						     if (item.isFormField()) 
						     {
						           
						     } 
						     else 
						     {
						         try 
						         {
						              String itemName = item.getName();
						              File savedFile = new File("D:\\HTML\\JAVA PROJECTS\\Zmail\\WebContent\\uploaded files\\"+itemName);
						              item.write(savedFile);
						              
						              if(count==items.size()-1)
						              {
						            	  files+=itemName;
						            	  count=0;
						            	  
						              }
						              else
						              {
						            	  files+=itemName+"*";
						            	  count+=1;
						              }
						         } 
						         catch (Exception e) 
						         {
						              out.println("File upload failed");
						         }
						       }
					     }
					}
			     }
			     
			}
			HttpSession filename=request.getSession();
			filename.setAttribute("fileName",files);
			out.close();
		}
		else
		{
			response.sendRedirect("login.jsp");
		}
	}

}
