<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	String name = (String) session.getAttribute("session_name");
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="/css/style.css" />
	</head>
	<body>
		
		<%
			if(name != null)
			{
				%>
					<div class="menubar_div">
						<a href="homePage">Home</a>
						<a href="aboutUsPage">About Us</a>
						<a href="contactUsPage">Contact Us</a>
						<a href="profilePage"> <img alt="Profile Pic" src="/images/profile_pic.png" height="50" /> <% out.print(name); %></a>
						<a href="logoutPage">Logout</a>
					</div>
				<%
			}
			else
			{
				%>
					<div class="menubar_div">
						<a href="homePage">Home</a>
						<a href="aboutUsPage">About Us</a>
						<a href="contactUsPage">Contact Us</a>
						<a href="loginPage">Login</a>
						<a href="registerPage">Register</a>
					</div>
				<%
			}
		%>
		
	</body>
</html>