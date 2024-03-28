<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
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

        .login-container {
            background-color: #ffffff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            font-weight: normal; /* Changed to normal */
        }

        input[type="text"],
        input[type="password"],
        input[type="tel"],
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #ffffff;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        /* Added CSS for radio buttons */
        .radio-container {
            display: flex;
            justify-content: space-between;
            margin-bottom: 15px;
        }

        .radio-container label {
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>BloodBank Service</h2>
        <p></p>
        <!-- Form for submitting login data -->
        <form action="userBloodRequest" method="post">
            <label for="bloodGroup">Blood Group:</label><br>
            <input type="text" id="bloodGroup" name="bloodGroup" required><br>
            <label for="type">Request Type:</label><br>
            <div class="radio-container">
                <label for="donate">
                    <input type="radio" id="donate" name="type" value="Donate" required>
                    Donate
                </label>
                <label for="receive">
                    <input type="radio" id="receive" name="type" value="Receive" required>
                    Receive
                </label>
            </div>
            <label for="quantity">Quantity:</label><br>
            <select id="quantity" name="quantity" required>
                <option value="">Select Quantity</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select><br><br>
            <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>
