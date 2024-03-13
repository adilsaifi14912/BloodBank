<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Update Password</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
    }
    .container {
        max-width: 400px;
        margin: 50px auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    }
    h2 {
        text-align: center;
        margin-bottom: 30px;
        color: #2c3e50;
    }
    form {
        margin-bottom: 20px;
    }
    label {
        display: block;
        margin-bottom: 10px;
        color: #2c3e50;
    }
    input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    input[type="submit"] {
        width: 100%;
        background-color: #3498db;
        color: #fff;
        border: none;
        padding: 10px;
        border-radius: 5px;
        cursor: pointer;
    }
    input[type="submit"]:hover {
        background-color: #2980b9;
    }
</style>
</head>
<body>

<div class="container">
    <h3 style="color:red"> ${errorMsg} </h2>
    <h2>Update Password</h2>
    <form action="/update-password" method="POST">
        <label for="current-password">Current Password</label>
        <input type="password" id="current-password" name="currentPassword" required>

        <label for="new-password">New Password</label>
        <input type="password" id="new-password" name="newPassword" required>

        <label for="confirm-password">Confirm New Password</label>
        <input type="password" id="confirm-password" name="confirmPassword" required>

        <input type="submit" value="Update Password">
    </form>
</div>

</body>
</html>
