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
        }

        h3 {
            color: #6a7eff; /* pastel blue */
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
            background-color: #77dd77; /* pastel green */
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
    <h3>List of All Users Coins:</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>BloodGroup</th>
                    <th>User Coins</th>
                    <th>Agent Coins</th>
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
