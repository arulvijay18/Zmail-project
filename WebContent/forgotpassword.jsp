<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>Forgot Password</title>
<script>
$(document).ready(function(){
	$('#resetbtn').click(function(){
		var username=$('#name').val();
		var mobile=$('#mobileno').val();
		console.log(username);
		$.ajax({
			type:'POST',
			url:'passwordreset',
			data:{
				'userName':username,
				'mobileNo':mobile
			},
			success:function(response){
				var info=response;
				if(info=="validated\r\n")
				{
					console.log("hi");
					$('#resetbtn').hide();
					var str='<p style="font-size:20px;margin-left:5px">Enter the new password:<span style="color:red;font-size:18px">*</span></p>';
						str+='<input type="text" style="margin-left:5px;height:30px;width:350px;font-size:20px" name="password" id="password" pattern="(?=.*?[!@#$%^&*+`~&#39=?\|\]\[\(\)\-<>/]).{8,}" onkeyup="check()">';
						str+='<p style="color:blue;margin-left:5px">Password must contain 8 characters which includes a special character</p>';
						str+='<div style="margin-left:5px;color:red;font-size:15px;font-weight:bold" id="error1"></div>';
						str+='<p style="font-size:20px;margin-left:5px">Re-enter the new pasword:<span style="color:red;font-size:18px">*</span></p>';
						str+='<input type="text" style="margin-left:5px;height:30px;width:350px;font-size:20px" name="repassword" id="repassword" onkeyup="check1()"><br><br><br><br>';
						str+='<div style="margin-left:5px;color:red;font-size:15px;font-weight:bold" id="error2"></div>';
						str+='<input type="submit" value="Reset Password" id="resetpass" onclick="check3()" style="margin-left:200px;height:40px;width:130px;box-shadow:3px 3px 2px gray;background-color:#019be4;border:1px solid;color:white;font-weight:bold">';
						$('#passreset').html(str);	
				}
				else
				{
					$('#message').html("Username and mobile no did not match");
				}
			},
			error:function(error){
				window.location.href="login.jsp";
			}
		});
	});
});

function check()
{
	var password=document.getElementById('password');
	var error1=document.getElementById('error1');
	
	if(!password.checkValidity())
	{
		error1.innerHTML="Password did not match the requested format";
	}
	else
	{
		error1.innerHTML="";
	}
}
function check1()
{
	var password=document.getElementById('password');
	var repassword=document.getElementById('repassword');
	var error2=document.getElementById('error2');
	if(password.value!=repassword.value)
	{
		error2.innerHTML="Password did not match";
	}
	else
	{
		error2.innerHTML="";
	}
}
 function check3(){
$(document).ready(function(){
	
		var username=$('#name').val();
		var password=$('#password').val();
		var error1=$('#error1').val();
		var error2=$('#error2').val();
		if(error1!="" && error2!="")
		{
			alert("please enter correct details");
		}
		else
		{
			$.ajax({
				type:'POST',
				url:'forgotpassword',
				data:{
					'userName':username,
					'password':password,
				},
				success:function(response)
				{
					var info=response;
					$('#message').html(response);
				},
				error:function(error){
					window.location.href="login.jsp";
				}
				
			});
		}
	
});
 }
</script>
</head>
<body>
	<center><h1 style="font-size:80px;font-family:Comic Sans MS;margin-top:-2px"><span style="color:green">Z</span><span style="color:red">M</span><span style="color:blue">A</span><span style="color:purple">I</span><span style="color:black">L</span></h1></center>
	<div style="margin:auto;width:500px;margin-top:-20px;border:2px solid black">
		
		<p style="font-size:20px;margin-left:5px">Enter your Username:<span style="color:red;font-size:18px">*</span></p>
		<input type="text" style="margin-left:5px;height:30px;width:350px;font-size:20px" name="username" id="name"  autocomplete="off" required>
		<p style="font-size:20px;margin-left:5px">Enter your mobileno:<span style="color:red;font-size:18px">*</span></p>
		<input type="text" style="margin-left:5px;height:30px;width:350px;font-size:20px" name="mobileno" id="mobileno" required><br><br><br><br>
		<div id="passreset"></div>
		<center><div id="error" style="color:red;font-weight:bold;font-size:20px"></div></center>
		<input type="submit" value="Continue" id="resetbtn" style="margin-left:200px;height:40px;width:90px;box-shadow:3px 3px 2px gray;background-color:#019be4;border:1px solid;color:white;font-weight:bold"><br><br>
		<center><a href="login.jsp" style="font-size:18px;margin-left:-30px">Login</a></center><br><br><br>
		<div style="margin-left:135px;color:red;font-size:15px;font-weight:bold" id="error"></div>
	  	</div>	
	  	<center><h2 id="message"></h2></center>
</body>
</html>