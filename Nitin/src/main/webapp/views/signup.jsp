  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup Page</title>
    <style>

     .header {
                background-color: #dc3545; /* Blood red color */
                color: #fff;
                padding: 20px;
                text-align: center;
            }

            .logo {
                font-size: 36px;
                font-weight: bold;
                margin: 0;
            }

            .slogan {
                font-size: 18px;
                margin-top: 5px;
            }

        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 520px;
            margin: 50px auto;
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .form-group input[type="text"],
        .form-group input[type="password"],
        .form-group input[type="date"],
        .form-group input[type="tel"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        .form-group select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        .btn {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <div class="header">
            <h1 class="logo">Blood Bank</h1>
            <p class="slogan">Saving Lives, One Donation at a Time</p>
            <p> ${formatError} </p>
    </div>
    <div class="container">
        <h2>Signup</h2>
        <form action="userSignup" method="post">


            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>


            <div class="form-group">
                <label for="parentId">Parent ID:</label>
                <input type="number" id="parentId" name="parentId">
            </div>


            <div class="form-group">
                <label for="password">Set-Password:</label>
                <input type="password" id="setPassword" name="setpassword" required>
            </div>


            <div class="form-group">
                  <label for="password">Confirm-Password:</label>
                  <input type="password" id="recheckPassword" name="recheckpassword" required>
            </div>


            <div class="form-group">
                <label for="dob">Date of Birth:</label>
                <input type="date" id="dob" name="dateOfBirth" required>
            </div>


            <div class="form-group">
                <label for="phone">Phone Number:</label>
                <input type="number" id="phone" name="phoneNumber">
            </div>


            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address">
            </div>


            <div class="form-group">
                <button type="submit" class="btn">Signup</button>
            </div>
        </form>
            <div class="form-group">
                <p>Already a user? <a href="login">Login</a></p>
            </div>
    </div>
</body>

</html>
