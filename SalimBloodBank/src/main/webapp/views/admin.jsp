<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
    <h1>Admin Dashboard - List of Signed-up Users</h1>
    <table border="1">
        <tr>
            <th>User ID</th>
            <th>Username</th>
            <!-- Add more columns as needed -->
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <!-- Display more user information as needed -->
            </tr>
        </c:forEach>
    </table>
</body>
</html>
