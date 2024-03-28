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
			<br/> <span class="body_text_title" style="color: green"> Registration Form </span> <br/> <br/> <br/>
			<p>${formatError}</p>
			<form action="agentRegForm" method="post">
				Name : <input type="text" name="username" /> <br/> <br/>
				Email Id : <input type="text" name="userEmail" /> <br/> <br/>
				 <input type="hidden" name="password" value="test"/> <br/> <br/>
				Gender : <input type="radio" name="gender" value="Male" /> Male <input type="radio" name="gender" value="Female" /> Female <br/> <br/>
				City : <select name="city">
							<option>Select City</option>
							<option value="Delhi">Delhi</option>
							<option value="Mumbai">Mumbai</option>
							<option value="Pune">Pune</option>
							<option value="Chandigarh">Chandigarh</option>
						</select> <br/>
				PhoneNumber : <input type="number" name="phoneNumber"> <br><br>
				Date of Birth : <input type="date" name="dateOfBirth"> <br><br>
                        <select id="bloodGroup" name="bloodGroup">
                            <option value="A+">A+</option>
                            <option value="A-">A-</option>
                            <option value="B+">B+</option>
                            <option value="B-">B-</option>
                            <option value="AB+">AB+</option>
                            <option value="AB-">AB-</option>
                            <option value="O+">O+</option>
                            <option value="O-">O-</option>
                        </select>

				<input type="submit" value="Register" />

			</form>
			<div class="form-group">
                            <p>Already a user? <a href="loginPage">Login</a></p>
                        </div>
		</div>
	</body>
</html>