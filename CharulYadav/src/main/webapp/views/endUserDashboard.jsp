<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>End User Dashboard</title>
    <style>
        body {
            background-color: #f8f8f8;
            color: #333;
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
        }

        h1, h2, h3 {
            color: #6c757d;
        }

        h1 {
            background-color: #fefefe;
            padding: 20px;
            margin: 0;
        }

        p {
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <h1>End User Logged In</h1>
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
