<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            background-color: #f8f8fa; /* light gray */
            font-family: 'Arial', sans-serif;
            color: #495057; /* dark gray */
            text-align: center;
        }

        h1, h2, h3 {
            color: #ff7675; /* pastel blue */
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #ffb6c1; /* pastel pink */
        }

        tr:nth-child(even) {
            background-color: #add8e6; /* light blue */
        }

        p {
            margin-bottom: 10px;
        }

        button {
            background-color: #87CEEB;
            color: #495057; /* dark gray */
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            margin-top: 10px;
        }

        button:hover {
            background-color: #aaf0d1; /* light green */
        }
    </style>
</head>
<body>
    <h1>Admin Logged In</h1>
    <h2>Welcome!</h2>
    <h3>Admin Details:</h3>
    <p>Name: ${user.name}</p>
    <p>Username: ${user.username}</p>
    <p>Created On: ${user.createdOn}</p>
    <p>Created By: ${user.createdBy}</p>
    <p>DOB: ${user.dob}</p>
    <p>Blood Group: ${user.bloodGroup}</p>
    <form action="usersList" method="post">
        <button type="submit">Check Users List</button>
    </form>
    <form action="usersCoins" method="post">
        <button type="submit">Users Coins</button>
    </form>
    <form action="checkRequestsAdmin" method="post">
        <button type="submit">Check Requests</button>
    </form>
    <form action="checkStock" method="post">
        <button type="submit">Check Stock Availability</button>
    </form>
    <form action="agentCreationForm" method="post">
        <button type="submit">Create Agent</button>
    </form>
    <form action="requestReportAdmin" method="post">
        <button type="submit">Requests Report</button>
    </form>
    <!-- Logout Button -->
    <form action="/" method="post">
        <button type="submit">Logout</button>
    </form>
</body>
</html>
