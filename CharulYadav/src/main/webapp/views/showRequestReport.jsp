<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Request Report</title>
    <style>
        body {
            background-color: #f8f8fa; /* Light gray */
            font-family: 'Arial', sans-serif;
            color: #495057; /* Dark gray */
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1 {
            color: #6a7eff; /* Pastel blue */
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #d3d3d3; /* Light gray */
        }

        th {
            background-color: #ffb6c1; /* Pastel pink */
        }

        tr:nth-child(even) {
            background-color: #add8e6; /* Light blue */
        }
    </style>
</head>
<body>
    <h1>Request Report</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Blood Group</th>
                <th>Coin Value</th>
                <th>Units</th>
                <th>Accept</th>
                <th>Decline</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestReport}" var="report">
                <tr>
                    <td>${report.bloodGroup}</td>
                    <td>${report.coinValue}</td>
                    <td>${report.units}</td>
                    <td>${report.acceptCount}</td>
                    <td>${report.declineCount}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
