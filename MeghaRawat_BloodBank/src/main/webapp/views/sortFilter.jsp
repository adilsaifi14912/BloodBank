<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Details</title>
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

            h3 {
                text-align: center;
                margin-top: 20px;
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

    <div class="user-details">
        <h3>User Model Table</h3>

        <form action="sortAndFilter" method="get">
            <label for="sortBy">Sort By:</label>
            <select id="sortBy" name="sortBy">
                <option value="username">Username</option>
                <option value="bloodGroup">Blood Group</option>
                <option value="createdBy">Created By</option>
            </select>

            <label for="filterBy">Filter By:</label>
            <select id="filterBy" name="filterBy">
                <option value="">All</option>
                <option value="agent">Agent</option>
                <option value="auto">Auto</option>
            </select>

            <!-- Additional filter for date range -->
            <label for="startDate">Start Date:</label>
            <input type="date" id="startDate" name="startDate">

            <label for="endDate">End Date:</label>
            <input type="date" id="endDate" name="endDate">

            <button type="submit">Apply</button>
        </form>

        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Address</th>
                    <th>Blood Group</th>
                    <th>Phone Number</th>
                    <th>Created On</th>
                    <th>Created By</th>
                    <th>DOB</th>
                    <th>Password</th>
                    <th>Role</th>
                    <th>Username</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="user" items="${userModelList}" varStatus="loop">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.address}</td>
                        <td>${user.bloodGroup}</td>
                        <td>${user.phone}</td>
                        <td>${user.created_date_time}</td>
                        <td>${user.createdBy}</td>
                        <td>${user.dob}</td>
                        <td>${user.password}</td>
                        <td>${user.role}</td>
                        <td>${user.username}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
