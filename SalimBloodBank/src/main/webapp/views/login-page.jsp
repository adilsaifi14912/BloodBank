<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login</title>
	</head>
	<body>
		<jsp:include page="header.jsp" />
		<p>${status}</p>
		<jsp:include page="login.jsp" />
		
		<jsp:include page="footer.jsp" />
	</body>
</html>