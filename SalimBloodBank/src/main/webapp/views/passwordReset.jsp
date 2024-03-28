<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Password Reset</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>
    <div class="body_div">
        <br/> <span class="body_text_title" style="color: orange;"> Password Reset Form </span> <br/> <br/> <br/>
        <p>${passwordResetStatus}</p>
        <form action="passwordReset" method="post">
            Email Id : <input type="text" name="userEmail" /> <br/> <br/>
            New Password : <input type="password" name="password" /> <br/> <br/>
            Confirm Password : <input type="password" name="confirmPassword" /> <br/> <br/>
            <input type="submit" value="Reset Password" />
        </form>

        <div class="signup-link">
            <p>Remember your password? <a href="loginPage">Login</a></p>
        </div>

    </div>
</body>
</html>
