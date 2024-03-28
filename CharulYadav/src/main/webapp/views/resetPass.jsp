<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password</title>
</head>
<body>
    <div class="login-container">
        <h2>Reset Password</h2>
        <form action="/updatePassword" method="post">
            <label for="username">Username:</label><br>
            <input type="text" id="username" name="username" required><br>
            <label for="currentPassword">Current Password:</label><br>
            <input type="password" id="password" name="password" required><br>
            <label for="newPassword">New Password:</label><br>
            <input type="password" id="newPassword" name="newPassword" required><br><br>
            <input type="submit" value="Update Password">
        </form>
    </div>
</body>
</html>