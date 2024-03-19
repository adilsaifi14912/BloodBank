<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agent Dashboard</title>
    <style>
         body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f4f4;
                    margin: 0;
                    padding: 0;
                }

                .container {
                    max-width: 800px;
                    margin: 50px auto;
                    background: #fff;
                    padding: 20px;
                    border-radius: 8px;
                    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                }

                h2 {
                    text-align: center;
                    margin-bottom: 20px;
                    color: #333;
                }

                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-bottom: 20px;
                }

                th, td {
                    padding: 12px;
                    text-align: left;
                    border-bottom: 1px solid #ddd;
                }

                th {
                    background-color: #f2f2f2;
                }

                .back-button {
                    text-align: center;
                    margin-top: 20px;
                }

                .back-button a {
                    text-decoration: none;
                    font-weight: bold;
                    color: #333;
                    padding: 10px 20px;
                    background-color: #4CAF50;
                    color: white;
                    border-radius: 5px;
                    display: inline-block;
                }

                .back-button a:hover {
                    background-color: #45a049;
                }
    </style>
</head>
<body>
    <div class="container">
        <h2>Welcome to Agent Dashboard!!</h2>
           <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Username</th>
                    <th>Created On</th>
                    <th>Created By</th>
                    <th>DOB</th>
                    <th>Blood Group</th>
                    <th>Role</th> 
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${list}">
                    <tr>
                        <td>${user.name}</td>
                        <td>${user.username}</td>
                        <td>${user.createdOn}</td>
                        <td>${user.createdBy}</td>
                        <td>${user.dob}</td>
                        <td>${user.bloodGroup}</td>
                        <td>${user.role}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="back-button">
            <a href="/">Log Out</a>
        </div>
    </div>
</body>
</html>
