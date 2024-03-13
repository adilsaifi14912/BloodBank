<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Blood Bank User Profile</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #2c3e50;
        color: #ecf0f1;
    }
    .navbar {
        background-color: #34495e;
        overflow: hidden;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 10px 20px;
    }
    .navbar a {
        color: #ecf0f1;
        text-align: center;
        text-decoration: none;
        margin: 0 10px;
    }
    .container {
        max-width: 600px;
        margin: 20px auto;
        background-color: #34495e;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    }
    h1 {
        text-align: center;
        margin-bottom: 30px;
    }
    .user-profile {
        padding: 20px;
        background-color: #2c3e50;
        border-radius: 10px;
    }
    .user-profile h2 {
        margin-bottom: 10px;
    }
    .user-profile p {
        margin-bottom: 5px;
    }
</style>
</head>
<body>

<div class="navbar">
    <a href="#">Role: Normal User</a>
    <h1>Blood Bank</h1>
    <div>
        <a href="/login">Logout</a>
    </div>
</div>

<div class="container">
    <div class="user-profile">
        <h2>User Profile</h2>
        <p><strong>Name:</strong> ${data.name}</p>
        <p><strong>Username:</strong> ${data.username}</p>
        <p><strong>Created At:</strong> ${data.creationTime}</p>
        <p><strong>Created By:</strong> ${data.createdBy}</p>
        <p><strong>DOB:</strong> ${data.dob}</p>
        <p><strong>Blood Group:</strong> ${data.bloodGroup}</p>
    </div>

</div>

</body>
</html>
