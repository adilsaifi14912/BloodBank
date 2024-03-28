<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            background-color: #f8f8fa; /* Light gray */
            font-family: 'Arial', sans-serif;
            color: #495057; /* Dark gray */
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1, h2, h3 {
            color: #6a7eff; /* Pastel blue */
        }

        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd; /* Light gray */
        }

        th {
            background-color: #ffcccb; /* Pastel pink */
        }

        tr:nth-child(even) {
            background-color: #f0f8ff; /* Light blue */
        }

        button {
            background-color: #77dd77; /* Pastel green */
            color: #495057; /* Dark gray */
            border: none;
            border-radius: 4px;
            cursor: pointer;
            padding: 10px 20px;
            margin-top: 20px;
        }

        button:hover {
            background-color: #aaf0d1; /* Light green */
        }
    </style>
</head>
<body>
    <h3>List of All Users Coins:</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Username</th>
                <th>Blood Group</th>
                <th>User Coins</th>
                <th>Agent Coins</th>
                <th>Admin Coins</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="signedUpUser" items="${userList}" varStatus="index">
                <tr>
                    <td>${signedUpUser.username}</td>
                    <td>${signedUpUser.bloodGroup}</td>
                    <td>${signedUpUser.userCoins}</td>
                    <td>${signedUpUser.agentCoins}</td>
                    <td>${signedUpUser.adminCoins}</td>
                    <td>${signedUpUser.status}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <form action="/" method="post">
        <button type="submit">Logout</button>
    </form>
</body>
</html>
