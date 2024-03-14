<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>End User Dashboard</title>
</head>
<body>
    <h1>End User Logged In</h1>
    <h2>User Details:</h2>
        <p>Username: ${userModel.username}</p>
        <p>Name: ${userModel.name}</p>
        <p>Role: ${userModel.role}</p>
        <p>Created Date and Time: ${userModel.created_date_time}</p>
        <p>Created By: ${userModel.createdBy}</p>
        <p>Date of Birth: ${userModel.dob}</p>
</body>
</html>
