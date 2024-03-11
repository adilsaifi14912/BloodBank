<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- signup.jsp -->
<!DOC TYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
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
            height: 100vh;
        }
        .container {
            text-align: center;
        }
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #8c1c1c;
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
        .login-link {
            text-decoration: none;
            color: #333;
            margin-top: 20px;
            display: inline-block;
        }
    </style>
</head>
<body>
<h1>Welcome to Blood Bank</h1>
    <div class="container">

        <h2>User Signup</h2>
        <form action="/bbp/success" method="post" id="signupForm" onsubmit="return validateForm()">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required minlength="3" maxlength="15">

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="dob">DOB:</label>
            <input type="date" id="dob" name="dob" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required minlength="6">

            <label for="phone">Phone Number:</label>
            <input type="tel" id="phone" name="phone" required pattern="[0-9]{10}">

            <input type="submit" value="Sign Up">
        </form>

        <script>
            function validateForm() {
                var phoneInput = document.getElementById("phone");
                if (!phoneInput.checkValidity()) {
                    alert("Please enter a valid 10-digit phone number.");
                    return false;
                }
                return true;
            }
        </script>

    </div>


    </script>
</body>
</html>
