<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>Sent Mails</title>
<style>
	.receivedMailstable,th,td{
	 	border:1px solid black;
	 	border-collapse:collapse;
	 	text-align:center;
	}
	th,td{
		height:20px;
		width:150px;
	}
	.options{
		display:flex;
		height:50px;
		width:100%;
		background-color:red;
	}
	a{
	
	margin-top:10px;
	
	font-size:20px;
	text-decoration:none;
	}
	a:hover{
		background-color:blue;
		height:30px;
		width:110px;
		color:white;
		margin-top:0px;
		padding:10px;
		text-align:center;
	}
	.delete:hover{
		background-color:blue;
		height:30px;
		width:200px;
		color:white;
		margin-top:0px;
		padding:10px;
		text-align:center;
	}
	#down:hover{
		background-color:white;
		font-size:20px;
		color:blue;
		height:0px;
		width:0px;
		padding:0px;		
	}
</style>
</head>
<body onload="loadsentmails()">
	<%
	String userName="";
	try
	{
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
		
		if((String)session.getAttribute("userName")==null||session.getAttribute("userName")=="")
		{
			response.sendRedirect("login.jsp");
		}
		userName=(String)session.getAttribute("userName");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	%>
	<center><h1 style="font-size:80px;font-family:Comic Sans MS;margin-top:-2px"><span style="color:green">Z</span><span style="color:red">M</span><span style="color:blue">A</span><span style="color:purple">I</span><span style="color:black">L</span></h1></center>
	 <h2 style="text-align:right;margin-top:-25px"><i class="fa fa-user-circle-o" aria-hidden="true" ></i>&nbsp<span style="color:blue;font-size:22px"><%= userName %></span></h2>
	 
	<div class="options" style="margin-top:-10px">
	 <a style="margin-left:100px" href="welcomepage.jsp">Home</a>
	 <a style="margin-left:100px" href="composemail.jsp">Compose</a>
	 <a style="margin-left:100px" href="searchmails.jsp">Search Mails</a>
	 <a class="delete" style="margin-left:100px" href="deletesentmails.jsp">Delete Sent Mails</a>
	 <a class="delete" style="margin-left:100px" href="deletereceivedmails.jsp">Delete Received Mails</a>
	 <a style="margin-left:100px" href="logout">Logout</a>
	 </div>
	<center><h4>Sent Mails List</h4></center>
	<center> <div id="sentmails" style="margin-left:-80px"></div></center>
	
<script>
	$(document).ready(function(){
		$.ajax({
			type:'GET',
			url:'sentmails',
			content:document.body,
			success:function(response){
				var sentmails=response;
				if(sentmails==null ||sentmails.length==0)
				{
					$('#sentmails').html("There is no mails in the list");
				}
				else
				{
					var str='<table class="receivedMailstable" style="margin-left:100px"> ';
					 str+='<tr>';
					 str+='<th>Message Id</th>';
					 str+='<th style="width:200px">Receiver Id</th>';
					 str+='<th style="width:250px">Subject</th>';
					 str+='<th style="width:500px">Message</th>';
					 str+='<th style="width:300px">Attachments</th>';
					 str+='</tr>';
					for(var i=0;i<sentmails.length;i++)
					{
						var getsentdata=sentmails[i];
						
						str+='<tr>';
						str+='<td>' +getsentdata.MessageId+ '</td>';
						str+='<td style="width:200px">' +getsentdata.ReceiverId+ '</td>';
						str+='<td style="width:200px">' +getsentdata.Subject+ '</td>';
						str+='<td style="width:500px">' +getsentdata.Message+ '</td>';
						
						var files=getsentdata.Attachments;
						console.log(files);
						var filesarray=files.split("*");
						str+='<td>'
						if(filesarray.length==0 ||filesarray==null||filesarray[0]=="")
						{
							str+='<h3>'+"No attachments"+'</h3>';
						}
						else
						{
							for(var j=0;j<filesarray.length;j++)
							{
								str+='<a id="down" style="font-size:15px" href="downloadservlet?fileName='+filesarray[j]+'">'+filesarray[j]+'</a><br>';	
							}
						}
						str+='</td>';
						str+='</tr>';
					}
					str+='</table>';
					$('#sentmails').html(str);
				}
			},
			error:function(error){
				window.location.href="login.jsp";
			}
		});
	});
	
	
	
</script>
</body>
</html>