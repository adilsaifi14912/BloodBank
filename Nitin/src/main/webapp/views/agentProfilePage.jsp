<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agent Profile</title>
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
            margin-bottom: 20px;
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
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
        }

        h3 {
            text-align: center;
            color: #dc3545; /* Blood red color */
            margin-bottom: 20px;
        }

        .profile-info {
            margin-bottom: 20px;
        }

        .profile-info p {
            font-size: 16px;
            margin: 5px 0;
            color: #007bff; /* Blue color */
        }

        .profile-info p:first-child {
            margin-top: 20px;
        }

        .profile-info p:last-child {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="header">
    <h1 class="logo">Blood Bank</h1>
    <p class="slogan">Saving Lives, One Donation at a Time</p>
</div>

<div class="container">
    <h3>Agent User Profile</h3>
    <div class="profile-info">
        <p>Username: ${user.getUsername()}</p>
        <p>ID: ${user.getId()}</p>
        <p>Date of Birth: ${user.getDateOfBirth()}</p>
        <p>Phone Number: ${user.getPhoneNumber()}</p>
        <p>Address: ${user.getAddress()}</p>
        <p>Blood Group : ${user.getDateOfBirth()}</p>
        <p>Created On : ${user.getCreatedOn()}</p>
    </div>
</div>

</body>
</html>
