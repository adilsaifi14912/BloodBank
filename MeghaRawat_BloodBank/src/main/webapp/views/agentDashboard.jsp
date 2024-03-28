<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Agent Dashboard</title>

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
            color: #007bff; /* Agent Dashboard heading color */
        }

        p {
            margin-bottom: 10px;
            text-align: center;
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
        <h1>Agent Dashboard</h1>
        <h2>Agent Logged In</h2>
        <div class="agent-details">
            <h3>Agent Details:</h3>
            <p>Username: ${userModel.username}</p>
            <p>Name: ${userModel.name}</p>
            <p>Role: ${userModel.role}</p>
            <p>Coins:${userModel.coins}</p>
            <p>Commission:${userModel.commission}</p>
        </div>


        <form action="createUser">
            <button type="submit">Create User</button>
        </form>

        <form action="agentRequestStatus" method="post">
            <button type ="submit">Show Request Status</button>
        </form>
        <form action="agentBloodReport">
                    <button type="submit">Blood Report</button>
                </form>

                <form action="agentCoinReport">
                            <button type="submit">Coin Report</button>
                        </form>
                        <form action="agentCoinsByRate">
                                                    <button type="submit">Coin By Rate</button>
                                                </form>

        <form action="logout" style="text-align: right;">
            <button type="submit">Logout</button>
        </form>
    </div>
</body>
</html>
