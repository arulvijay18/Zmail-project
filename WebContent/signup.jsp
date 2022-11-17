<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Signup</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('#name').keyup(function(){
			var username=$('#name').val();
			$.ajax({
				type:'POST',
				url:'usernameExistence',
				data:{
					'username':username
				},
				success:function(response){
					var result=response;
					$('#error1').html(result);
				},
				error:function(error){
					window.location.href="signup.jsp";
				}
			});
		});
	});
</script>
</head>
<body>
	
	<center><h1 style="font-size:80px;font-family:Comic Sans MS;margin-top:-2px"><span style="color:green">Z</span><span style="color:red">M</span><span style="color:blue">A</span><span style="color:purple">I</span><span style="color:black">L</span></h1></center>
	
	<form onsubmit="return check()" novalidate action="signup" method="post" style="border:2px solid black;height:700px;width:500px;margin:auto;margin-top:35px">
		<p style="font-size:20px;margin-left:5px">Enter the Username:<span style="color:red;font-size:18px">*</span></p>
		<input type="text" style="margin-left:5px;height:30px;width:350px;font-size:20px" name="username" id="name" pattern="[a-zA-Z0-9]+@zmail.com" placeholder="eg: abc123@zmail.com" autocomplete="off">
		<p style="color:blue;margin-left:5px">Username must ends with "@zmail.com"</p>
		<div style="margin-left:5px;color:red;font-size:15px;font-weight:bold" id="error1"></div>
		<p style="font-size:20px;margin-left:5px">Enter the Password:<span style="color:red;font-size:18px">*</span></p>
		<input type="password" style="margin-left:5px;height:30px;width:350px;font-size:20px" name="password" id="password" pattern="(?=.*?[!@#$%^&*+`~'=?\|\]\[\(\)\-<>/]).{8,}">
		<p style="color:blue;margin-left:5px">Password must contain 8 characters which includes a special character</p>
		<div style="color:red;font-size:15px;font-weight:bold;margin-left:5px" id="error2"></div>
		<p style="font-size:20px;margin-left:5px">Re-Enter the Password:<span style="color:red;font-size:18px">*</span></p>
		<input type="password" style="margin-left:5px;height:30px;width:350px;font-size:20px" name="repassword" id="repassword" >
		<div style="color:red;font-size:15px;font-weight:bold;margin-left:5px" id="error3"></div>
		<p style="font-size:20px;margin-left:5px">Enter the mobileno:<span style="color:red;font-size:18px">*</span></p>
		<input type="text" style="margin-left:5px;height:30px;width:350px;font-size:20px" name="mobileno" id="mobileno" pattern="[0-9]{10}" autocomplete="off">
		<div style="color:red;font-size:15px;font-weight:bold;margin-left:5px" id="error4"></div>
		
		<p style="font-size:18px;margin-left:5px"><span style="color:red;font-size:18px">* &nbsp</span>Fields are mandatory</p><br>
		<input type="submit" id="signupbtn" value="Signup" style="margin-left:200px;height:40px;width:90px;box-shadow:3px 3px 2px gray;background-color:#019be4;border:1px solid;color:white;font-weight:bold">
		<a href="login.jsp" style="font-size:18px;margin-left:-285px">Login</a>
	</form>
	
	<script>
	function check()
		{
		var username=document.getElementById("name");
		var password=document.getElementById("password");
		var repassword=document.getElementById("repassword");
		var mobileno=document.getElementById("mobileno");
		var userNameValidationerror=document.getElementById("error1");
		var passwordValidationerror=document.getElementById("error2");
		var reEnterPassworderror=document.getElementById("error3");
		var mobileNoerror=document.getElementById("error4");
		
			
			var valid=true;
			if(!username.checkValidity())
			{
				valid=false;
				userNameValidationerror.innerHTML="The entered username doest not match the requested format";
				
				
			}
			
			if(!password.checkValidity())
			{
				valid=false;
				passwordValidationerror.innerHTML="The entered Password does not match the requested format";
				
				
			}
			if(!mobileno.checkValidity())
			{
				valid=false;
				mobileNoerror.innerHTML="The entered mobileno does not match the requested format";
				
			}
			if(username.value=="" || username.value==null)
			{
				valid=false;
				userNameValidationerror.innerHTML="Username required";
				username.value="";
				
			}
			if(password.value=="" || password.value==null)
			{
				valid=false;
				passwordValidationerror.innerHTML="Password required";
				password.value="";
			}
			 if(repassword.value!=password.value)
			{
				valid=false;
				reEnterPassworderror.innerHTML="Password did not match";
				
			}
			if(repassword.value=="" || repassword.value==null)
			{
				valid=false;
				reEnterPassworderror.innerHTML="This field can't be empty";
				repassword.value="";
			} 
			if(mobileno.value=="" || mobileno.value==null)
			{
				valid=false;
				mobileNoerror.innerHTML="Mobileno required";
				mobileno.value="";	
			}
			
			return valid;
			
		}
	</script>
</body>
</html>