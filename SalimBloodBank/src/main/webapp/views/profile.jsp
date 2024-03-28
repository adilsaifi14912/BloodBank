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

		<h2>Total Coins : ${userTotalCoins}</h2>

          <form action="bloodStatus" method="get">
              <button type="submit">Blood Request status</button>
          </form>
          <br>
		 <button ><a href="/logout">Logout </a></button>
			<br/> <span class="body_text_title" style="color: teal;"></span> <br/> <br/> <br/>
			<b> Email : </b> ${user.getUserEmail()} <br/> <br/>
			<b> Gender : </b> ${user.getGender()} <br/> <br/>
			<b> Blood Group : </b> ${user.getBloodGroup()} <br/> <br/>
			<b> City : </b> ${user.getCity()} <br/> <br/>


            <button ><a href="donateAndReceive">Blood Service </a></button>
        </div>

		<img src="" alt="Image Description">
	</body>
</html>