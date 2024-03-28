<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>HomePage</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            padding: 30px;
            text-align: center;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }
        h1 {
            color: #c00;
            margin-bottom: 20px;
        }
        .btn {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
            margin-top: 20px;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #45a049;
        }
        .button-icon {
            vertical-align: middle;
            margin-right: 5px;
        }
        .blood-bank-image {
            width: 300px; /* Adjust the size as needed */
            height: auto;
            margin-bottom: 20px;
        }
        .signup-login-text {
            margin-bottom: 10px;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to Blood Bank</h1>
        <p class="signup-login-text">New User?<br> <a href="signup" class="btn">Sign Up</a></p>
        <p class="signup-login-text"><br>Already have an account?<br> <a href="login" class="btn">Login</a></p>
    </div>
</body>
</html>
