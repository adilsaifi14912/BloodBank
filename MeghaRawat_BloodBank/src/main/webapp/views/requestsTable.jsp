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
                <form action="/showRequest" method="post">
                    <label for="startDate">Start Date:</label>
                    <input type="date" id="startDate" name="startDate">

                    <label for="endDate">End Date:</label>
                    <input type="date" id="endDate" name="endDate">

                    <label for="agentUsername">Agent Username:</label>
                    <input type="text" id="agentUsername" name="agentUsername">

                    <label for="endUsername">End User Username:</label>
                    <input type="text" id="endUsername" name="endUsername">

                    <button type="submit">Filter</button>
                </form>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Username</th>
                        <th>Agent</th>
                        <th>Created_At</th>
                        <th>Status</th>
                        <th>Quantity</th>
                        <th>Type</th>
                        <th>Blood Group</th>
                        <th>Dob</th>
                        <th>Age</th>
                        <th>Action</th>
                        <th>Remark</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${bloodBankRequests}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.agent}</td>
                            <td>${user.createdAt}</td>
                            <td>${user.status}</td>
                            <td>${user.quantity}</td>
                            <td>${user.type}</td>
                            <td>${user.bloodGroup}</td>
                            <td>${user.dob}</td>
                            <td>${user.age}</td>
                            <td>
                            <form action="processRequest" method="post">
                                            <input type="hidden" name="requestId" value="${user.id}">
                                            <input type="hidden" name="username" value="${user.username}">
                                            <input type="hidden" name="bloodGroup" value="${user.bloodGroup}">
                                            <input type="hidden" name="quantity" value="${user.quantity}">
                                            <input type="hidden" name="type" value="${user.type}">
                                            <input type="hidden" name="updatedAt" value="${user.updatedAt}">
                                            <c:choose>
                                                <c:when test="${user.status != 'Approved' && user.status != 'Rejected'}">
                                                    <button type="submit" name="action" value="approve">Approve</button>
                                                    <button type="submit" name="action" value="reject">Reject</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <!-- Display status if request is already processed -->
                                                    ${user.status}
                                                </c:otherwise>
                                            </c:choose>
                                        </form>
                                    </td>
                            <td>
                                <form action="addRemark" method="post">
                                    <input type="hidden" name="requestId" value="${user.id}">
                                    <input type="text" name="remark" placeholder="Enter remark">
                                    <button type="submit">Submit Remark</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
