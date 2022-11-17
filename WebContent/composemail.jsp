<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.zmaildatastorage.Signupdetails" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
.options{
		display:flex;
		height:50px;
		width:100%;
		background-color:violet;
	}
	a{
	
	margin-top:10px;
	font-size:20px;
	text-decoration:none;
	}
	a:hover{
		background-color:purple;
		height:30px;
		width:120px;
		color:white;
		margin-top:0px;
		padding:10px;
		text-align:center;
	}
	.delete:hover{
		background-color:purple;
		height:30px;
		width:200px;
		color:white;
		margin-top:0px;
		padding:10px;
		text-align:center;
	}
</style>
<script>
$(document).ready(function(){
	$('#fileUploader').change(function(){
		
			event.stopPropagation(); 
	        event.preventDefault(); 
	        var files = event.target.files; 
	        var data = new FormData();
	        $.each(files, function(key, value)
	        {
	            data.append(key, value);
	        });
	         $.ajax({
	        url: 'uploadfiles',
	        type: 'POST',
	        data: data,
	        cache: false,
	       
	        processData: false, 
	        contentType: false, 
	        success: function(response)
	        {
	            var info=response;
	            if(response==null || response==""){
	            	
	            }
	            else
	            {
	            alert(info);
	            }
	        },
	        error: function(jqXHR, textStatus, errorThrown)
	        {
	            console.log('ERRORS: ' + textStatus);
	        }
	        });
	});
});



	$(document).ready(function(){
		$('#sendbtn').click(function(){
			var tomailid=$('#tomailid').val();
			var subject=$('#subject').val();
			var message=$('#message').val();
			if(tomailid==null ||tomailid=="")
			{
				alert("To mailid can't be empty");
			}
			else
			{
			document.getElementById('tomailid').value="";
			document.getElementById('subject').value="";
			document.getElementById('message').value="";
			
			setTimeout(function () {
				location.reload();
			}, 2000);
			$.ajax({
				type:'POST',
				url:'composemail',
				data:{
					'tomailid':tomailid,
					'subject':subject,
					'message':message
				},
				success:function(response){
					var messageSent=response;
					if(messageSent=="Message sent successfully!!!\r\n")
					{
					
						$('#successmessage').html(messageSent);
					}
					else
					{
						$('#failmessage').html(messageSent);
					}
				},
				error:function(error){
					window.location.href="composemail.jsp";
				}
				
			});
			}
		});
	});
</script>
</head>
<body>
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
	 
	<div class="options">
	 <a style="margin-left:100px" href="welcomepage.jsp">Home</a>
	 <a style="margin-left:100px" href="sentmails.jsp">Sent Mails</a>
	 <a style="margin-left:100px" href="searchmails.jsp">Search Mails</a>
	 <a class="delete" style="margin-left:100px" href="deletesentmails.jsp">Delete Sent Mails</a>
	 <a class="delete" style="margin-left:100px" href="deletereceivedmails.jsp">Delete Received Mails</a>
	 <a style="margin-left:100px" href="logout">Logout</a>
	 </div>
	<center><h3>Compose Mail</h3></center>
	<div id="form" style="border:2px solid black;width:500px;margin:auto;margin-top:35px;margin-left:430px">
	
	<p style="font-size:20px;margin-left:5px">Enter to-mailid:</p>
	<input type="text" style="margin-left:5px;height:30px;width:350px;font-size:20px" name="tomailid" id="tomailid"  autocomplete="off" required>
	<p style="color:blue">If there is more than one to-mailid, enter those separated by a space</p>
	<p style="font-size:20px;margin-left:5px">Subject:</p>
	<input type="text" style="margin-left:5px;height:30px;width:350px;font-size:18px" name="subject" id="subject"  autocomplete="off" >
	<p style="font-size:20px;margin-left:5px">Message:</p>
	<textarea type="text" style="margin-left:5px;height:150px;width:450px;font-size:15px;text-align:top" name="message" id="message"  autocomplete="off"></textarea><br><br>
	<form  method="post" enctype="multipart/form-data" >
	Select file: <input type="file" name="fname"  multiple id="fileUploader"><br><br>
	<p style="color:blue">You can upload maximum of 5 files, each file must be less than 2 mb</p>
	</form>
	<input type="submit" value="Send" id="sendbtn" style="margin-left:200px;height:40px;width:90px;box-shadow:3px 3px 2px gray;background-color:#019be4;border:1px solid;color:white;font-weight:bold"><br><br><br>		
	<center><div id="successmessage" style="color:green;font-size:20px;font-weight:bold"></div></center>
	<center><div id="failmessage" style="color:red;font-size:20px;font-weight:bold"></div></center>
	
	</div>
</body>
</html>