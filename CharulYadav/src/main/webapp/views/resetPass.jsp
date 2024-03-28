<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password</title>
    <style>
        body {
            background-color: #f8f8fa; /* light gray */
            font-family: 'Arial', sans-serif;
            color: #495057; /* dark gray */
            margin: 0;
            padding: 0;
            text-align: center;
        }

        .login-container {
            background-color: #fff; /* white */
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* shadow */
            padding: 20px;
            margin: 20px auto;
            width: 300px;
        }

        h2 {
            color: #6a7eff; /* pastel blue */
        }

        label {
            color: #008080; /* teal */
            display: block;
            margin-top: 10px;
            text-align: left;
        }

        input[type="text"],
        input[type="password"],
        input[type="submit"] {
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #d3d3d3; /* light gray */
            border-radius: 4px;
            width: 100%;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #77dd77; /* pastel green */
            color: #495057; /* dark gray */
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
            padding: 10px 20px;
        }

        input[type="submit"]:hover {
            background-color: #aaf0d1; /* light green */
        }
    </style>
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
