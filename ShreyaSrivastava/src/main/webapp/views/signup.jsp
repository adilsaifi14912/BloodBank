<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- signup.jsp -->
<!DOCTYPE html>
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
            min-height: 100vh;
            color: #333;
        }
        .container {
            text-align: center;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 400px;
        }
        h1 {
            margin-bottom: 20px;
        }
        form {
            text-align: left;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #666;
        }
        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="date"],
        input[type="tel"],
        select {
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
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome to Blood Bank</h1>
    <h2>User Signup</h2>
    <form id="signupForm" action="success" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required minlength="3" maxlength="15">

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required minlength="6">

        <label for="dob">DOB:</label>
        <input type="date" id="dob" name="dob" required>

        <label for="phone">Phone Number:</label>
        <input type="text" id="phone" name="phone" required pattern="[0-9]{10}">

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required>

        <label for="bloodGroup">Blood Group:</label>
        <select id="bloodGroup" name="bloodGroup" required>
            <option value="A+">A+</option>
            <option value="A-">A-</option>
            <option value="B+">B+</option>
            <option value="B-">B-</option>
            <option value="AB+">AB+</option>
            <option value="AB-">AB-</option>
            <option value="O+">O+</option>
            <option value="O-">O-</option>
        </select>

        <label for="choice">Donor/Receiver:</label>
                <select id="choice" name="choice" required>
                    <option value="Donor">Donor</option>
                    <option value="Receiver">Receiver</option>
                </select>

        <!-- Role field automatically set to "END USER" -->
        <input type="hidden" id="role" name="role" value="END USER">

        <input type="submit" value="Sign Up">
    </form>
</div>

<script>
    // Set the role field to "END USER" when other details are entered
    document.getElementById('signupForm').addEventListener('input', function() {
        document.getElementById('role').value = 'END USER';
    });
</script>

</body>
</html>
