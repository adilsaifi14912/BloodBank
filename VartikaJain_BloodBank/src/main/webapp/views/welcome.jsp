<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to HealthWealth BloodBank</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            color: #b30000;
        }
        .button {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px;
            font-size: 18px;
            color: #fff;
            background-color: #b30000;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .button:hover {
            background-color: #990000;
        }
        .image {
            margin-top: 20px;
            max-width: 100%;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to HealthWealth BloodBank</h1>
        <img src="data:image/jpeg;base64,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" alt="Blood Bank" class="image">
        <h3>Already a user?</h3>
        <form action="login" method="get">
            <button class="button">Login</button>
        </form>
        <h3>New User?</h3>
        <form action="signup" method="get">
            <button class="button">Sign Up</button>
        </form>
    </div>
</body>
</html>


