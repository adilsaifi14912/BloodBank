<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #fde4e4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 80vh;
        }
        h1 {
            color: #6b5b95;
            text-align: center;
        }
        h2 {
            color: #6b5b95;
            text-align: center;
        }

        form {
            background-color: #f9ece9;
            padding: 10px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #6b5b95;
        }

        input {
            width: 80%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #caa3a3;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: royalBlue;
            color: #fff;
            cursor: pointer;
        }

        h3 {
            margin-top: 20px;
            color: #6b5b95;
        }

        a {
            color: #ff7675;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <form action="userLogin" method="post" id="signupForm">
        <h1> BLOOD BANK </h1>
        <h2>Login</h2>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <input type="submit" value="Login">
        <h3>New User? Click here to Sign Up</h3>
        <a href="signup">Signup</a>
    </form>
</body>
</html>
