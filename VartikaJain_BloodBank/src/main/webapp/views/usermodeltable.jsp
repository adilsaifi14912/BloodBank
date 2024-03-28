<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <!-- Include your CSS files here -->
    <link rel="stylesheet" href="styles.css">
    <style>
        /* Add your CSS styles here */
        .hidden {
            display: none;
        }
    </style>
</head>
<body>
    <header>
        <h1>Admin Dashboard</h1>
        <h2>User Model Table</h2>
        <form action="sortandfilter" method="get">
            <label for="sortBy">Sort By:</label>
            <select id="sortBy" name="sortBy">
                <option value="username">Username</option>
                <option value="bloodGroup">Blood Group</option>
                <option value="createdby">Created By</option>
                <!-- Add more sorting options here if needed -->
            </select>
            <label for="filterBy">Filter By:</label>
            <select id="filterBy" name="filterBy">
                <option value="">All</option>
                <option value="agent">Agents</option>
                <option value="self">Self Created EndUser</option>
                <option value="aguser">Agent Created EndUser</option>
                <option value="createdBetween">Created Between</option>
                <option value="username">Username</option>
                <!-- Add more filtering options here if needed -->
            </select>
            <!-- Added input fields for date range and username -->
            <div id="createdBetweenFilters" style="display: none;">
                <label for="startDate">Start Date:</label>
                <input type="date" id="startDate" name="startDate">
                <label for="endDate">End Date:</label>
                <input type="date" id="endDate" name="endDate">
            </div>
            <div id="usernameFilter" style="display: none;">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username">
            </div>
            <button type="submit">Apply</button>
        </form>
    </header>
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
                <tr>
                    <td>${user.id}</td>
                    <td>${user.address}</td>
                    <td>${user.bloodGroup}</td>
                    <td>${user.contactNumber}</td>
                    <td>${user.createdOn}</td>
                    <td>${user.createdby}</td>
                    <td>${user.dob}</td>
                    <td>${user.password}</td>
                    <td>${user.role}</td>
                    <td>${user.username}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <div style="display: flex; justify-content: space-between;">
        <form action="logout" method="get">
            <button type="submit">Logout</button>
        </form>
    </div>

    <script>
        document.getElementById('filterBy').addEventListener('change', function() {
            var selectedValue = this.value;
            var createdBetweenFilters = document.getElementById('createdBetweenFilters');
            var usernameFilter = document.getElementById('usernameFilter');

            if (selectedValue === 'createdBetween') {
                createdBetweenFilters.style.display = "inline-block";
                usernameFilter.style.display = "none";
            } else if (selectedValue === 'username') {
                createdBetweenFilters.style.display = "none";
                usernameFilter.style.display = "inline-block";
            } else {
                createdBetweenFilters.style.display = "none";
                usernameFilter.style.display = "none";
            }
        });
    </script>
</body>
</html>
