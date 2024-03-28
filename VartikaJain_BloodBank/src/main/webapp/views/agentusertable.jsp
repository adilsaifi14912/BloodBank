<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agent Dashboard</title>
    <!-- Include your CSS files here -->
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #333;
            color: #fff;
            padding: 20px;
            text-align: center;
        }
        nav {
            background-color: #444;
            color: #fff;
            padding: 10px;
        }
        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            text-align: center;
        }
        nav ul li {
            display: inline;
            margin-right: 20px;
        }
        nav ul li a {
            text-decoration: none;
            color: #fff;
            font-weight: bold;
        }
        main {
            margin: 20px;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            margin-top: 0;
        }
        section {
            margin-bottom: 20px;
        }
        section p {
            margin: 5px 0;
        }
        footer {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }
        button {
             padding: 10px 20px; /* Adjust padding to increase button size */
             font-size: 16px; /* Increase font size for better visibility */
        }
    </style>
    <body>
        <header>
            <h1>Agent Dashboard</h1>
        </header>
                <h2>User Model Table</h2>
                    <form action="sortby" method="get">
                        <label for="sortBy">Sort By:</label>
                        <select id="sortBy" name="sortBy">
                            <option value="username">Username</option>
                            <option value="bloodGroup">Blood Group</option>
                            <!-- Add more sorting options here if needed -->
                        </select>
                        <button type="submit">Apply</button>
                            </form>
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
                <form id="logoutForm" action="logout" method="get">
                    <button type="submit">Logout</button>
                </form>
    </body>
</html>