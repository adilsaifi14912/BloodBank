<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Agent Dashboard</title>
    <style>
        body {
            background-color: #f5f5f5;
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1, h2, h3 {
            color: #3498db;
        }

        p {
            color: #555;
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <h1>Agent Logged In</h1>
    <h2>Welcome!</h2>
    <h3>User Details:</h3>
    <p>Name: ${user.name}</p>
    <p>Username: ${user.username}</p>
    <p>Created On: ${user.createdOn}</p>
    <p>Created By: ${user.createdBy}</p>
    <p>DOB: ${user.dob}</p>
    <p>Blood Group: ${user.bloodGroup}</p>
</body>
</html>
