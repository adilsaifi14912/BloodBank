<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Blood Report</title>
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
        <h1>Blood Report</h1>
        <div class="user-details">
            <table>
                <thead>
                    <tr>
                        <th>Blood Group</th>
                        <th>Coins value</th>
                        <th>Units</th>
                        <th>Accept</th>
                        <th>Decline</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="blood" items="${bloodReport}">
                        <tr>
                            <td>${blood.bloodGroup}</td>
                            <td>${blood.coinValue}</td>
                            <td>${blood.units}</td>
                            <td>${blood.acceptCount}</td>
                            <td>${blood.declineCount}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
