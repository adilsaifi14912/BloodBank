<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <style>
        body {
            font-family: 'Quicksand', sans-serif;
            background-color: #f9e8e8;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 80vh;
        }

        form {
            background-color: #fdfdfd;
            padding: 10px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
        }

        h2 {
            text-align: center;
            color: #5e5e5e;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #5e5e5e;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            box-sizing: border-box;
            border: 1px solid #cccccc;
            border-radius: 4px;
            background-color: #f7f7f7;
        }

        input[type="submit"] {
            background-color: #ffb6b9;
            color: #ffffff;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #ff8f94;
        }

        div {
            margin-top: 16px;
            text-align: center;
            color: #5e5e5e;
        }

        a {
            text-decoration: none;
            color: #93a2cc;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <form action="register" method="post" id="signupForm">
        <h2>Sign Up</h2>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="name">Name:</label>
        <input type="text" id="name" name="name">

        <label for="dob">DOB:</label>
        <input type="date" id="dob" name="dob" required>

        <label for="dob">Blood Group:</label>
        <input type="bloodGroup" id="bloodGroup" name="bloodGroup" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password">

        <input type="submit" value="Sign Up">
        <div th:if="${message}">
            <h3>Already have an account?</h3>
            <a href="/">Login</a>
        </div>
    </form>
</body>
</html>
