<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Request Report</title>
    <style>
        body {
            background-color: #f8f8fa; /* light gray */
            font-family: 'Arial', sans-serif;
            color: #495057; /* dark gray */
            margin: 0;
            padding: 0;
        }

        h1 {
            color: #6a7eff; /* pastel blue */
            text-align: center;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ccc; /* light gray */
        }

        th {
            background-color: #ffb6c1; /* pastel pink */
        }

        tr:nth-child(even) {
            background-color: #add8e6; /* light blue */
        }
    </style>
</head>
<body>
    <h1>Request Report</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Blood Group</th>
                <th>Commission Rate</th>
                <th>Coins (EU)</th>
                <th>Agent Coins</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestReport}" var="report">
                <tr>
                    <td>${report.bloodGroup}</td>
                    <td>10%</td>
                    <td>${report.Coins}</td>
                    <td>${report.agentCoins}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
