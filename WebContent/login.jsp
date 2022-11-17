<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Zmail</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('#loginbtn').click(function(){
			
			var username=$('#name').val();
			var password=$('#password').val();
			document.getElementById('name').value="";
			document.getElementById('password').value="";
			
			$.ajax({
				type:'POST',
				url:'login',
				dataType:'text',
				data:{
					'username':username,
					'password':password
				},
				success:function(response){
					var loginSuccess=response;
					if(loginSuccess=="Incorrect username or password\r\n")
					{
						$('#error1').html(loginSuccess);
					}
					else
					{
						window.location.href="welcomepage.jsp";
					}
					
				},
				error:function(error){
					window.location.href="login.jsp";
				}
			});
		});
			
	});
	
	

</script>
</head>
<body>
	<center><h1 style="font-size:80px;font-family:Comic Sans MS;margin-top:-2px"><span style="color:green">Z</span><span style="color:red">M</span><span style="color:blue">A</span><span style="color:purple">I</span><span style="color:black">L</span></h1></center>
	<div style="margin:auto;height:400px;width:500px;margin-top:-20px;border:2px solid black">
		
		<p style="font-size:20px;margin-left:5px">Enter your Username:<span style="color:red;font-size:18px">*</span></p>
		<input type="text" style="margin-left:5px;height:30px;width:350px;font-size:20px" name="username" id="name"  autocomplete="off" required>
		<p style="font-size:20px;margin-left:5px">Enter your Password:<span style="color:red;font-size:18px">*</span></p>
		<input type="password" style="margin-left:5px;height:30px;width:350px;font-size:20px" name="password" id="password" required><br><br><br><br>
		<input type="submit" value="Login" id="loginbtn" style="margin-left:200px;height:40px;width:90px;box-shadow:3px 3px 2px gray;background-color:#019be4;border:1px solid;color:white;font-weight:bold">
		<a href="signup.jsp" style="font-size:18px;margin-left:-280px">Signup</a><br><br><br>
		<center><a href="forgotpassword.jsp" style="font-size:18px">Forgot Password</a></center><br>
		<div style="margin-left:135px;color:red;font-size:15px;font-weight:bold" id="error1"></div>
	  	</div>
	  	<% 
	  	String logoutMessage="";
	  	try
	  	{
	  		if(request.getAttribute("message")!=null)
		  	{ 	
		    	logoutMessage="Logged out Successfully!!!";
		  	}
		}
	  	catch(Exception e)
	  	{
	  		System.out.println(e.getMessage());
	  	}
	    %>
		<center><h3 style="color:red"><%= logoutMessage  %></h3></center>
	
	
	
</body>
</html>