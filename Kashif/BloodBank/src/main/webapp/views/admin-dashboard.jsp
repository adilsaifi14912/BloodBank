<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blood Bank Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .admin-panel {
            display: inline-block;
            float: left;
            margin: 20px; /* Adjust margin as needed */
        }
        .navbar {
            background-color: #2c3e50;
            overflow: hidden;
            text-align: center;
        }
        .navbar a {
            display: inline-block;
            color: #ecf0f1;
            text-align: center;
            padding: 1px 2px;
            text-decoration: none;
        }
        .navbar a:hover {
            background-color: #34495e;
        }
        .navbar .logout {
            margin: 12px;
            float: right;
        }
        .navbar .logout a {
            background-color: grey;
            border: none;
            border-radius: 5px;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            margin: 0;
        }
        .navbar .logout a:hover {
            background-color: #c0392b;
        }
        .container {
            max-width: 1200px;
            margin: 20px auto;
            background-color: #ecf0f1;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 5px;
            color: #2c3e50;
        }
        .admin-profile {
            margin-bottom: 30px;
            padding: 20px;
            background-color: #34495e;
            color: #ecf0f1;
            border-radius: 10px;
        }
        .admin-profile h2 {
            margin-bottom: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #bdc3c7;
        }
        th {
            background-color: #2c3e50;
            color: #ecf0f1;
            font-weight: bold;
            text-transform: uppercase;
        }
        td {
            background-color: #ecf0f1;
        }
    </style>
</head>
<body>
<div class="navbar">
    <div class="admin-panel">
        <h1 style="color: #ecf0f1; display: inline;">AdminPanel</h1>
    </div>
    <div class="logout">
        <a href="/login" style="display: inline-block; float: right;">Logout</a>
    </div>
</div>
<div class="container">
    <div class="admin-profile">
        <h2>Admin Profile</h2>
        <p><strong>Name:</strong> ${data.name}</p>
        <p><strong>Username:</strong> ${data.username}</p>
        <p><strong>Created At:</strong> ${data.creationTime}</p>
        <p><strong>Created By:</strong> ${data.createdBy}</p>
        <p><strong>DOB:</strong> ${data.dob}</p>
        <p><strong>Blood Group:</strong> ${data.bloodGroup}</p>
    </div>
    <h1>Blood Bank Dashboard (Only EndUser List)</h1>
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Username</th>
            <th>Creation Time</th>
            <th>CreatedBy</th>
            <th>DOB</th>
            <th>Blood Group</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${allUsers}">
            <tr>
                <td>${user.name}</td>
                <td>${user.username}</td>
                <td>${user.creationTime}</td>
                <td>${user.createdBy}</td>
                <td>${user.dob}</td>
                <td>${user.bloodGroup}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${empty allUsers}">
        <p>No users found</p>
    </c:if>
</div>
</body>
</html>
