<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>User Profile</title>
		<link rel="stylesheet" type="text/css" href="/css/style.css" />
	</head>
	<body>

	<h2> End User Profile </h2>
		<div class="body_div">
			<br/> <span class="body_text_title" style="color: teal;"></span> <br/> <br/> <br/>
			<b> Email : </b> ${user.getUserEmail()} <br/> <br/>
			<b> Gender : </b> ${user.getGender()} <br/> <br/>
			<b> City : </b> ${user.getCity()} <br/> <br/>



		</div>

		<img src="" alt="Image Description">
	</body>
</html>