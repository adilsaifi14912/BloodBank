<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Password</title>
</head>
<body>
    <h1>Update Password</h1>
    <c:if test="${not empty successMessage}">
        <p style="color: green;">${successMessage}</p>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>
    <form action="/updatePassword" method="post">
        <label for="currentPassword">Current Password:</label>
        <input type="password" id="currentPassword" name="currentPassword" required><br>

        <label for="newPassword">New Password:</label>
        <input type="password" id="newPassword" name="newPassword" required><br>

        <input type="submit" value="Update Password">
    </form>
</body>
</html>
