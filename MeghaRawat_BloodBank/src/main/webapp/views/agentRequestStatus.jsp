<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Requests</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        h1 {
            text-align: center;
            color: #c00; /* Admin Dashboard heading color */
            margin-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        form {
            text-align: center;
            margin-top: 10px;
        }

        input[type="text"] {
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-right: 10px;
        }

        button {
            padding: 8px 15px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
            margin-right: 10px;
            margin-bottom: 5px;
        }

        input[name="remark"] {
            margin-right: 10px;
            margin-bottom: 10px;
        }


        button[type="submit"] {
            background-color: #4CAF50;
            color: white;
        }

        button[type="submit"]:hover {
            background-color: #45a049;
        }

    </style>
</head>
<body>
    <div class="container">
        <h1>Requests Table</h1>
        <div class="user-details">
            <!-- Filter Form -->
                                    <form action="/agentRequestStatus" method="post">
                                        <label for="startDate">Start Date:</label>
                                        <input type="date" id="startDate" name="startDate">

                                        <label for="endDate">End Date:</label>
                                        <input type="date" id="endDate" name="endDate">

                                        <label for="endUsername">End User Username:</label>
                                                            <input type="text" id="endUsername" name="endUsername">

                                        <button type="submit">Filter</button>
                                    </form>
                        <table>
                            <thead>
                                <tr>
                                    <th>Username</th>
                                    <th>Created_At</th>
                                    <th>Agent</th>
                                    <th>Blood Group</th>
                                    <th>Quantity</th>
                                    <th>Type</th>
                                    <th>Status</th>
                                    <th>Dob</th>
                                    <th>Remark</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" items="${bloodBankRequests}">
                                    <tr>
                                        <td>${user.username}</td>
                                        <td>${user.createdAt}</td>
                                        <td>${user.agent}</td>
                                        <td>${user.bloodGroup}</td>
                                        <td>${user.quantity}</td>
                                        <td>${user.type}</td>
                                        <td>${user.status}</td>
                                        <td>${user.dob}</td>
                                        <td>${user.remark}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
        </div>
    </div>
</body>
</html>
