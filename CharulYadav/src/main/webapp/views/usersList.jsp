<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            background-color: #f8f8fa;
            font-family: 'Arial', sans-serif;
            color: #495057;
        }

        h1, h2, h3 {
            color: #008080;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #ffcccb;
        }

        tr:nth-child(even) {
            background-color: #f0f8ff;
        }

        p {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <h3>List of Signed-up Users:</h3>
    <form action="sortAndFilter" method="get" id="filterForm">
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
            <option value="Users">Users</option>
            <option value="createdOn">Created On</option>
        </select>
        <div id="createdOnFilters" style="display: none;">
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
        <table border="1">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Username</th>
                    <th>Created On</th>
                    <th>Created By</th>
                    <th>DOB</th>
                    <th>Blood Group</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="signedUpUser" items="${userList}" varStatus="index">
                    <tr>
                        <td>${signedUpUser.name}</td>
                        <td>${signedUpUser.username}</td>
                        <td>${signedUpUser.createdOn}</td>
                        <td>${signedUpUser.createdBy}</td>
                        <td>${signedUpUser.dob}</td>
                        <td>${signedUpUser.bloodGroup}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form action="/" method="post">
            <button type="submit">Logout</button>
        </form>
    <script>
        document.getElementById("filterBy").addEventListener("change", function() {
            var createdOnFilters = document.getElementById("createdOnFilters");
            if (this.value === "createdOn") {
                createdOnFilters.style.display = "block";
            } else {
                createdOnFilters.style.display = "none";
            }
        });
    </script>
</body>
</html>