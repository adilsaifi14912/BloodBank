<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.insightgeeks.bloodbank.dto.BloodRequestDTO" %>
<%@ page import="java.time.Period" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        /* Modal styles */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
            padding-top: 60px;
        }

        .modal-content {
            background-color: #fefefe;
            margin: 5% auto; /* 15% from the top and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 80%; /* Could be more or less, depending on screen size */
            border-radius: 8px; /* Rounded corners */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Add shadow */
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
        }

        textarea {
            width: 100%;
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #ccc;
            resize: vertical; /* Allow vertical resizing */
            box-sizing: border-box; /* Include padding and border in textarea's total width */
        }

        input[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        input[type="submit"]:focus {
            outline: none;
        }
    </style>
</head>
<body>
<h1>Blood Requests</h1>

<form action="bloodRequestsDateFilter" method="get">
    From : <input type="date" name="from">
    To : <input type="date" name="to">
    <input type="submit" value="search">
</form>

<form action="bloodRequestsStatusFilter" method="get">
    <select name="status">
        <option value="approved">Approved</option>
        <option value="rejected">Rejected</option>
        <option value="pending">Pending</option>
    </select>
    <input type="submit" value="Apply status filter">
</form>
<div>
    <button><a href="myRequests">All Requests</a></button>
</div>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Type</th>
        <th>Quantity</th>
        <th>Created On</th>
        <th>Blood Group</th>
        <th>Status</th>

    </tr>
    </thead>
    <tbody>
    <%
        List<BloodRequestDTO> bloodRequests = (List<BloodRequestDTO>) request.getAttribute("bloodRequests");
        for (BloodRequestDTO bloodRequest : bloodRequests) { %>
        <tr>
            <td><%= bloodRequest.getId() %></td>
            <td><%= bloodRequest.getType() %></td>
            <td><%= bloodRequest.getQuantity() %></td>
            <td><%= bloodRequest.getCreatedOn() %></td>
            <td><%= bloodRequest.getBloodGroup() %></td>
            <td><%= bloodRequest.getStatus() %></td>

        </tr>
        <% } %>
    </tbody>
</table>

<button type="button" class="btn btn-primary mt-5">
    <a href="/user" class="btn blue pad">Back</a>
</button>
</body>

</html>
