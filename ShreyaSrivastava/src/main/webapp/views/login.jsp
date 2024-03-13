<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- login.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- Add your CSS stylesheets or link to Bootstrap CSS here -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            color: #333; /* Added */
        }
        .container {
            text-align: center;
            background-color: #fff; /* Added */
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 400px; /* Added */
        }
        h1 {
            margin-bottom: 20px; /* Added */
        }
        form {
            text-align: left; /* Added */
        }
        label {
            display: block; /* Added */
            margin-bottom: 5px; /* Added */
            color: #666; /* Added */
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #8c1c1c; /* Changed to red */
            color: #fff;
            padding: 10px 20px;
            font-size: 18px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #6b1515;
        }
        .signup-link {
            text-decoration: none;
            color: #333;
            margin-top: 20px;
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome to Blood Bank</h1>
    <h2>User Login</h2>
    <form action="userLogin" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <input type="submit" value="Login">
    </form>
</div>
</body>
</html>
