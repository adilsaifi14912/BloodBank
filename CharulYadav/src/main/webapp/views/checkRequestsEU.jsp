<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blood Donation and Receiving Requests</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 8px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f8f8f8;
        }
        tr:hover {
            background-color: #f0f0f0;
        }
        button {
            background-color: #87CEEB;
            color: white;
            padding: 8px 16px;
            border: none;
            cursor: pointer;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #4682B4;
        }
        input[type="text"] {
            padding: 6px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }
    </style>
</head>
<body>
    <h1>Blood Donation and Receiving Requests</h1>
    <form action="/checkRequestsEU" method="post">
        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate">

        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate">
        <button type="submit">Filter</button>
    </form>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Created At</th>
                <th>User Created By</th>
                <th>Status</th>
                <th>Type</th>
                <th>Blood Group</th>
                <th>Quantity</th>
                <th>Remarks</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="request" items="${bloodBankRequests}" varStatus="loop">
                <tr>
                    <td>${request.id}</td>
                    <td>${request.username}</td>
                    <td>${request.createdAt}</td>
                    <td>${request.user_createdBy}</td>
                    <td id="status${loop.index + 1}">${request.status}</td>
                    <td>${request.type}</td>
                    <td>${request.bloodGroup}</td>
                    <td>${request.quantity}</td>
                    <td>${request.remarks}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>