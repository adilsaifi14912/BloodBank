<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.insightgeeks.bloodbank.dto.SignupDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Agent</title>
    <style>
        /* CSS styles */
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
            max-width: 920px;
            margin: 50px auto;
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
            text-align: center; /* Center align the content */
        }

        .create-agent-form {
            margin-top: 20px;
            text-align: left; /* Left align form elements */
        }

        .create-agent-form label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .create-agent-form input[type="text"],
        .create-agent-form input[type="date"],
        .create-agent-form input[type="tel"],
        .create-agent-form select { /* Added select element */
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .create-agent-form input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .create-agent-form input[type="submit"]:hover {
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
        <button type="button" class="btn btn-primary mt-5 fr"><a href="/admin" class="btn blue pad">Back</a></button>

<div class="container">
    <h2>Create Agent</h2>
    <form class="create-agent-form" action="userSignup" method="post">
        <label for="agentName">Agent Name:</label>
        <input type="text" id="agentName" name="username" required>

        <label for="dateOfBirth">Date of Birth:</label>
        <input type="date" id="dateOfBirth" name="dateOfBirth" required>

        <label for="phoneNumber">Phone Number:</label>
        <input type="number" id="phoneNumber" name="phoneNumber"required>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required>

        <label for="commission">Commission:</label>
        <input type="number" id="commission" name="commision" required>

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
        <input type="hidden" id="confirmPassword" name="confirmPassword" value="sample">
        <input type="hidden" id="password" name="password" value="sample">


        <input type="submit" value="Create Agent">
    </form>
</div>

</body>
</html>
