<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="java.util.List" %>
 <%@ page import="com.insightgeeks.bloodbank.dto.SignupDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Profile</title>
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
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .profile-info {
            margin-bottom: 20px;
        }

        .profile-info label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .profile-info p {
            padding: 10px;
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

        .edit-profile-link {
            text-align: center;
            margin-top: 20px;
        }

        .edit-profile-link a {
            color: #007bff;
            text-decoration: none;
        }

        .edit-profile-link a:hover {
            text-decoration: underline;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>

<div class="header">
    <h1 class="logo">Blood Bank</h1>
    <p class="slogan">Saving Lives, One Donation at a Time</p>
</div>
<div class="container">
    <h3>Admin user has logged in</h3>
    <div class="profile-info">
        <p><strong>Username:</strong> ${user.getUsername()}</p>
        <p><strong>ID:</strong> ${user.getId()}</p>
        <p><strong>Date of Birth:</strong> ${user.getDateOfBirth()}</p>
        <p><strong>Phone Number:</strong>${user.getPhoneNumber()}</p>
        <p><strong>Address:</strong>${user.getAddress()}</p>
    </div>

    <h3>Signup Users</h3>
    <table>
        <thead>
            <tr>
                <th>Username</th>
                <th>Date of Birth</th>
                <th>Phone Number</th>
                <th>Address</th>
                <th>Blood Group</th>
            </tr>
        </thead>
        <tbody>

            <%
            List<SignupDTO> allUsers = (List<SignupDTO>) request.getAttribute("signedupUsers");
            for (SignupDTO endUser : allUsers) { %>
                <tr>
                    <td><%= endUser.getUsername() %></td>
                    <td><%= endUser.getDateOfBirth() %></td>
                    <td><%= endUser.getPhoneNumber() %></td>
                    <td><%= endUser.getAddress() %></td>
                    <td><%= endUser.getBloodGroup() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
