
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Coin Report</title>
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

    </style>
</head>
<body>
    <div class="container">
        <h1>Coins Report</h1>
        <div class="user-details">
            <table>
                <thead>
                    <tr>
                        <th>User</th>
                        <th>Blood Group</th>
                        <th>End User Coins</th>
                        <th>Agent Coins</th>
                        <th>Admin Coins</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="coin" items="${coinReport}">
                        <tr>
                            <td>${coin.username}</td>
                            <td>${coin.bloodGroup}</td>
                            <td>${coin.userCoins}</td>
                            <td>${coin.agentCoins}</td>
                            <td>${coin.adminCoins}</td>
                            <td>${coin.status}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
