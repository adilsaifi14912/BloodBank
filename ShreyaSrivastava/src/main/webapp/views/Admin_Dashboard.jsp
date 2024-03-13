<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- admin_dashboard.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <!-- Add your CSS stylesheets or link to Bootstrap CSS here -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        h1, h2 {
            text-align: center;
            margin-top: 20px;
            color: #8c1c1c;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
    </style>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <!-- Add your CSS stylesheets or link to Bootstrap CSS here -->
    <style>
        /* Add your custom styles here */
    </style>
</head>
<body>
    <h1>Admin Dashboard</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Username</th>
                <th>Created On</th>
                <th>Created By</th>
                <th>DOB</th>
                <th>Blood Group</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${user.username}</td>
                <td>${user.createdOn}</td>
                <td>${user.createdby}</td>
                <td>${user.dob}</td>
                <td>${user.bloodGroup}</td>
            </tr>
        </tbody>
    </table>

     <h2>User Model Table</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Address</th>
                        <th>Blood Group</th>
                        <th>Contact Number</th>
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
                        <c:if test="${not loop.first}">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.address}</td>
                                <td>${user.bloodGroup}</td>
                                <td>${user.phone}</td>
                                <td>${user.createdOn}</td>
                                <td>${user.createdby}</td>
                                <td>${user.dob}</td>
                                <td>${user.password}</td>
                                <td>${user.role}</td>
                                <td>${user.username}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
    </body>
</html>
