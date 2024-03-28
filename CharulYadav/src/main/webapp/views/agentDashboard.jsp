<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Agent Dashboard</title>
    <style>
        body {
            background-color: #f8f8fa; /* light gray */
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1, h2, h3 {
            color: #ff7675; /* pastel blue */
        }

        p {
            color: #555; /* dark gray */
            margin: 10px 0;
        }

        button, .btn {
            background-color: #87CEEB;
            color: #495057; /* dark gray */
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
            text-decoration: none;
            display: inline-block;
        }

        button:hover, .btn:hover {
            background-color: #aaf0d1; /* light green */
        }
    </style>
</head>
<body>
    <h1>Agent Logged In</h1>
    <h2>Welcome!</h2>
    <h3>User Details:</h3>
    <p>Name: ${user.name}</p>
    <p>Username: ${user.username}</p>
    <p>Created On: ${user.createdOn}</p>
    <p>Created By: ${user.createdBy}</p>
    <p>DOB: ${user.dob}</p>
    <p>Blood Group: ${user.bloodGroup}</p>
    <p>Total Coins: ${user.coinValue}</p>
    <form action="checkRequestsAG">
        <button type="submit">Check Requests</button>
    </form>
    <form action="requestReportAG">
        <button type="submit">Requests Report</button>
    </form>
    <form action="agentCoinReport">
        <button type="submit">Agent Coin Report</button>
    </form>
    <form action="bloodGroupAgentRate">
        <button type="submit">Agent Rate by BloodGroup</button>
    </form>
    <a href="endUserCreationFormByAgent" class="btn">Create EndUser</a>
    <a href="/" class="btn">Log Out</a>
</body>
</html>
