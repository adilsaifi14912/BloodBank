<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="in.sp.main.dto.SignupDTO" %>

<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
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

    .dropdown-container {
        margin-top: 30px; /* Adjust margin as needed */
    }

    .fr{
        float:right;
    }
</style>
</head>
<body>
<div class="container">
    <h1>Signup Users</h1>
    <button type="button" class="btn btn-primary mt-5 fr"><a href="/admin" class="btn blue pad">Back</a></button>

    <div class="dropdown-container">
        <div class="dropdown show">
            <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="sortByDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Sort By
            </a>
            <div class="dropdown-menu" aria-labelledby="sortByDropdown">
                <a class="dropdown-item" href="/sort/nameAsc">Name-Asc</a>
                <a class="dropdown-item" href="/sort/createdByAsc">CreatedBy-Asc</a>
                <a class="dropdown-item" href="/sort/bloodGroupAsc">Blood Group-Asc</a>
                <a class="dropdown-item" href="/sort/nameDesc">Name-Desc</a>
                <a class="dropdown-item" href="/sort/createdByDesc">CreatedBy-Desc</a>
                <a class="dropdown-item" href="/sort/bloodGroupDesc">Blood Group-Desc</a>
            </div>
        </div>
    </div>
    <form>
    <div class="dropdown-container">
        <div class="dropdown show">
            <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="otherDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Filter
            </a>
            <div class="dropdown-menu" aria-labelledby="otherDropdown">
                <a class="dropdown-item" href="#" onclick="applyFilter('name')">Name</a>
                <a class="dropdown-item" href="#" onclick="applyFilter('date')">Created-On</a>
                <a class="dropdown-item" href="#" onclick="applyFilter('agent')">Agent</a>
            </div>
            Filter-value : <input type="text" id="filterValue" required>
        </div>
    </div>
    </form>

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
                List<RegisterDTO> allUsers = (List<RegisterDTO>) session.getAttribute("signedupUsers");
                for (RegisterDTO endUser : allUsers) { %>
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
                List<RegisterDTO> allUsers = (List<RegisterDTO>) request.getAttribute("sortedUsers");
                for (RegisterDTO endUser : allUsers) { %>
                    <tr>
                        <td><%= endUser.getUsername() %></td>
                        <td><%= endUser.getDateOfBirth() %></td>
                        <td><%= endUser.getPhoneNumber() %></td>
                        <td><%= endUser.getCity() %></td>
                        <td><%= endUser.getBloodGroup() %></td>
                        <td><%= endUser.getCreatedBy() %></td>
                    </tr>
            <% }
            } %>
        </tbody>
    </table>

</div>
</body>
<script>
    function applyFilter(filterOption) {
        var filterValue = document.getElementById('filterValue').value.trim();
        if (filterValue === "") {
            alert("Please enter a value for filtering.");
            return; // Stop further execution if the value is empty
        }
        var url = "/filter/" + filterOption + "?filterValue=" + encodeURIComponent(filterValue);
        window.location.href = url; // Redirect to the constructed URL
    }
</script>

</html>
