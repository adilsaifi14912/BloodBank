<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>End User Dashboard</title>
    <style>
        body {
            background-color: #f8f8fa; /* light gray */
            color: #495057; /* dark gray */
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1, h2, h3 {
            color: #ff7675; /* pastel blue */
        }

        h1 {
            background-color: #fefefe; /* light gray */
            padding: 20px;
            margin: 0;
        }

        p {
            margin: 10px 0;
        }

        .btn {
            background-color: #87CEEB;
            color: #495057; /* dark gray */
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin-top: 10px;
        }

        .btn:hover {
            background-color: #aaf0d1; /* light green */
        }

        .logout-btn {
            background-color: #ff9999; /* pastel red */
            color: #495057; /* dark gray */
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin-top: 10px;
        }

        .logout-btn:hover {
            background-color: #ffb6c1; /* light pink */
        }
    </style>
</head>
<body>
    <h1>End User Logged In</h1>
    <h2>Welcome!</h2>
    <h3>User Details:</h3>
    <p>Name: ${user.name}</p>
    <p>Username: ${user.username}</p>
    <p>Created On: ${user.createdOn}</p>
    <p>Created By: ${user.createdBy}</p>
    <p>DOB: ${user.dob}</p>
    <p>Blood Group: ${user.bloodGroup}</p>
    <p>Total Coins: ${user.coinValue}</p>
    <a href="endUserBloodBankInventory" class="btn">Donate/Receive Blood</a>
    <form action="checkRequestsEU" method="post">
        <button type="submit" class="btn">Check Requests</button>
    </form>
    <a href="/" class="logout-btn">Logout</a>
</body>
</html>
