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
    <h1>Admin Logged In</h1>
    <h2>Welcome!</h2>
    <h3>List of Signed-up Users:</h3>
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
                <c:if test="${!index.first}">
                    <tr>
                        <td>${signedUpUser.name}</td>
                        <td>${signedUpUser.username}</td>
                        <td>${signedUpUser.createdOn}</td>
                        <td>${signedUpUser.createdBy}</td>
                        <td>${signedUpUser.dob}</td>
                        <td>${signedUpUser.bloodGroup}</td>
                    </tr>
                </c:if>
                </c:forEach>
            </tbody>
        </table>
    <h3>User Details:</h3>
    <p>Name: ${user.name}</p>
    <p>Username: ${user.username}</p>
    <p>Created On: ${user.createdOn}</p>
    <p>Created By: ${user.createdBy}</p>
    <p>DOB: ${user.dob}</p>
    <p>Blood Group: ${user.bloodGroup}</p>
</body>
</html>
