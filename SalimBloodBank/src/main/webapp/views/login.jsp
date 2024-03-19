<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="/css/style.css" />
	</head>
	<body>
		<div class="body_div">
			<br/> <span class="body_text_title" style="color: orange;"> Login Form </span> <br/> <br/> <br/>
			<p>${formatError}</p>
                <p>${blockStatus}</p>
                <p>${passwordResetStatus}</p>
			<form action="loginForm" method="post">
				Email Id : <input type="text" name="userEmail" /> <br/> <br/>
				Password : <input type="password" name="password" /> <br/> <br/>
				<input type="submit" value="Login" /> 
			</form>

			<div class="signup-link">
                    <p>Not Registered? <a href="registerPage">Signup</a></p>
                </div>
			
		</div>
	</body>
</html>