<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="in.sp.main.dto.RegisterDTO" %>
<%@ page import="in.sp.main.entities.UserModel" %>
<html>
<head>
    <title>Admin Dashboard</title>

<form action="createAgent" method="get">
    <button type="submit">Create Agent </button>
</form>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* CSS for table */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<h1>Admin Dashboard - List of Signed-up Users</h1>

<form action="bloodStock" method="get">
    <button type="submit">Blood Stock</button>
</form>

<form action="showBloodRequests" method="get">
    <button type="submit">Blood Request</button>
</form>

<form action="sortByName" method="get">
    <button type="submit">Sort By Name</button>
</form>

<form action="sortByCreatedBy" method="get">
    <button type="submit">Sort CreatedBy</button>
</form>

<form action="sortByBloodGroup" method="get">
    <button type="submit">Sort by BloodGroup</button>
</form>

<form action="filterByUsername" method="get">
    <input type="text" name="username" placeholder="Enter username to filter">
    <button type="submit">Filter by Username</button>
</form>
<form action="filterCreatedAt" method="get">
    <input type="text" name="username" placeholder="Enter username to filter">
    <button type="submit">Filter by CreatedAt</button>
</form>

<form action="filter" method="get">
    <input type="text" name="username" placeholder="Enter username to filter">
    <button type="submit">Filter by Username</button>
</form>
<form action="filterByAgent" method="get">
    <input type="text" name="username" placeholder="Enter username to filter">
    <button type="submit">Filter by AgentUser</button>
</form>
<form action="filterByNotLongedInUser" method="get">
    <input type="text" name="username" placeholder="Enter username to filter">
    <button type="submit">Filter by Not Longing</button>
</form>

<!-- Table to display users -->
<table>
    <thead>
        <tr>
            <th>Username</th>
            <th>Date of Birth</th>
            <th>Phone Number</th>
            <th>Address</th>
            <th>Blood Group</th>
            <th>Created By</th>
        </tr>
    </thead>
    <tbody>

    <% if (request.getAttribute("sortedUsers") == null) {
        List<RegisterDTO> allUser = (List<RegisterDTO>) session.getAttribute("signedupUsers");
        for (RegisterDTO endUser : allUser) { %>
            <tr>
                <td><%= endUser.getUsername() %></td>
                <td><%= endUser.getDateOfBirth() %></td>
                <td><%= endUser.getPhoneNumber() %></td>
                <td><%= endUser.getCity() %></td>
                <td><%= endUser.getBloodGroup() %></td>
                <td><%= endUser.getCreatedBy() %></td>
            </tr>
    <% }
    } else {
        List<UserModel> allUsers = (List<UserModel>) request.getAttribute("sortedUsers");
        for (UserModel endUser : allUsers) { %>
            <tr>
                <td><%= endUser.getUsername() %></td>
                <td><%= endUser.getDateOfBirth() %></td>
                <td><%= endUser.getPhoneNumber() %></td>
                <td><%= endUser.getCity() %></td>
                <td><%= endUser.getBloodGroup() %></td>
                <td><%= endUser.getCreatedBy() %></td>
            </tr>
    <% }
    }

    if (request.getAttribute("filteredUsers") != null) {
        List<UserModel> users = (List<UserModel>) request.getAttribute("filterUsers");
        for (UserModel user : users) { %>
            <tr>
                <td><%= user.getUsername() %></td>
                <td><%= user.getDateOfBirth() %></td>
                <td><%= user.getPhoneNumber() %></td>
                <td><%= user.getCity() %></td>
                <td><%= user.getBloodGroup() %></td>
                <td><%= user.getCreatedBy() %></td>
            </tr>
    <% }
    } %>

    </tbody>
</table>
<!-- Logout button -->
<button class="btn btn-primary"><a href="logout" style="color: white;">Logout</a></button>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
