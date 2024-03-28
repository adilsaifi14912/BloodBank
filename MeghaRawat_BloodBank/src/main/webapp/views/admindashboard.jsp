<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
    <h1>Admin Logged In</h1>
    <h2>Admin Details:</h2>
            <p>Username: ${userModel.username}</p>
            <p>Name: ${userModel.name}</p>
            <p>Role: ${userModel.role}</p>

    <h2>User Details:</h2>
        <table border="1">
            <tr>
                <th>Username</th>
                <th>Name</th>
                <th>Phone</th>
            </tr>
            <c:forEach var="user" items="${userModelList}">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.name}</td>
                    <td>${user.phone}</td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>
