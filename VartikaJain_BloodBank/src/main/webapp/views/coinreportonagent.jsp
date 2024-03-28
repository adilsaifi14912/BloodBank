<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Coin Report</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Coin Report</h2>
    <table>
        <thead>
            <tr>
                <th>Username</th>
                <th>Rate</th>
                <th>End User Coin</th>
                <th>Agent Coin</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="entry" items="${CoinReport}">
                <tr>
                    <td>${entry.username}</td>
                    <td>${entry.rate}</td>
                    <td>${entry.endUserCoin}</td>
                    <td>${entry.agentCoin}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
