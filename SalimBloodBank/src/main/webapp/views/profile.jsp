<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.List" %>
<%@ page import="in.sp.main.dto.RegisterDTO" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Profile</title>
		<link rel="stylesheet" type="text/css" href="/css/style.css" />
	</head>
	<body>
		<div class="body_div">
			<br/> <span class="body_text_title" style="color: teal;"> Welcome : ${session_name} </span> <br/> <br/> <br/>
			<b> Email : </b> ${session_email} <br/> <br/>
			<b> Gender : </b> ${session_gender} <br/> <br/>
			<b> City : </b> ${session_city} <br/> <br/>
					<%
            		    List<RegisterDTO> users = (List<RegisterDTO>)request.getAttribute("signedupUsers");
            		    for(RegisterDTO user:users)
            		    {
            		%>
            		<p> <%= user.getUsername()%> | <%= user.getUserEmail()%> | <%= user.getCity()%></p>

            		<% }%>


		</div>
<<<<<<< HEAD:MdSalimBloodBank/src/main/webapp/views/profile.jsp

=======
>>>>>>> 7924172da61526155fa97a2c46105d829955dc17:SalimBloodBank/src/main/webapp/views/profile.jsp
		<img src="" alt="Image Description">
	</body>
</html>