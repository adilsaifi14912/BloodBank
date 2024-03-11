<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <style>
        /* CSS styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

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

        .user-details {
            margin-bottom: 20px;
        }

        .user-details p {
            margin: 10px 0;
        }

        .actions {
            text-align: center;
        }

        .actions a {
            display: inline-block;
            margin: 0 10px;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }

        .actions a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="header">
    <h1 class="logo">Blood Bank</h1>
    <p class="slogan">Saving Lives, One Donation at a Time</p>
    <p>${formatError}</p>
    <p>${status}</p>
</div>
<div class="container">
    <h2>User Profile</h2>
    <div class="user-details">
        <p><strong>Username:</strong> <%= request.getAttribute("username") %></p>
        <!-- Include other user details here -->
    </div>
    <div class="actions">
        <a href="editProfile">Edit Profile</a> <!-- Example link to edit profile page -->
        <a href="logout">Logout</a> <!-- Example link to logout -->
    </div>
</div>
</body>
</html>
