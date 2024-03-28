<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        h1, h2, h3 {
            text-align: center;
            margin-top: 20px;
        }

        h1 {
            color: #c00; /* Admin Dashboard heading color */
        }

        p {
            margin-bottom: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        form {
            text-align: center;
            margin-top: 20px;
        }

        button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Admin Dashboard</h1>
        <h2>Admin Logged In</h2>
        <div class="admin-details">
            <h3>Admin Details:</h3>
            <p>Username: ${userModel.username}</p>
            <p>Name: ${userModel.name}</p>
            <p>Role: ${userModel.role}</p>
            <p>Coins:${userModel.coins}</p>
        </div>

        <form action="createAgent">
            <button type="submit">Create Agent</button>
        </form>

        <form action="showRequest" method="post">
            <button type="submit">Show Request</button>
        </form>

        <form action="sortFilter">
            <button type="submit">Users List</button>
        </form>

        <form action="adminBloodReport">
            <button type="submit">Blood Report</button>
        </form>

        <form action="bloodAvailable">
            <button type="submit">Blood Available</button>
        </form>

        <form action="adminCoinReport">
            <button type="submit">Coin Report</button>
        </form>

        <form action="logout" style="text-align: right;">
            <button type="submit">Logout</button>
        </form>

    </div>
</body>
</html>
