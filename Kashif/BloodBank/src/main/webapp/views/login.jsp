<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blood Bank</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>

        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
            background-color: #f0f0f0;
            font-family: Arial, sans-serif;
            background-image: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 500 500"><text x="50%" y="50%" dy=".35em" fill="rgba(0,0,0,0.1)" font-size="40" text-anchor="middle">Blood Bank</text></svg>');
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
        }

        .login-container {
            display: flex;
            flex-direction: column;
            width: 300px;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            background-color: rgba(255, 255, 255, 0.8);
            margin: auto; /* Center the login container */
            margin-top: 50px; /* Adjust the top margin */
        }

        .login-container h2 {
            margin-bottom: 20px;
        }

        .login-container input {
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .login-container button {
            padding: 10px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .login-container button:hover {
            background-color: #45a049;
        }

        /* Added navbar styles */
        .navbar {
            background-color: #333;
            padding: 10px 0;
            margin-bottom: 20px; /* Add margin to separate from content */
        }

        .navbar-brand {
            color: #fff;
            font-size: 24px;
        }

        .signup-btn {
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            padding: 8px 16px;
            color: #fff;
            cursor: pointer;
        }

        .signup-btn:hover {
            background-color: #0056b3;
        }

        /* Added footer styles */
        .footer {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 20px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

    </style>
</head>
<body>

<nav class="navbar">
    <div class="container">
        <a class="navbar-brand" href="/">Blood Bank</a>
        <a class="signup-btn" href="/signup">Sign Up</a>
    </div>
</nav>

<form action="/login" method="post" id="signupForm">
    <div class="login-container">
        <h4 style="color:blue"> ${success} </h4>
        <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <strong>Failed ${times}:</strong> ${errorMsg}
                <button style="color:black;background-color: grey;" type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        <h2>Login</h2>
        <input type="text" placeholder="Username" name="username" required>
        <input type="password" placeholder="Password" name="password" required>
        <button type="submit">Login</button>
    </div>
</form>

<footer class="footer">
    <div class="container">
        &copy; 2024 Blood Bank. All Rights Reserved.
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
