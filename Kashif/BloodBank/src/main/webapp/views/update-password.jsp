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
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    }
    h3 {
        text-align: center;
        margin-bottom: 20px;
        color: red;
    }
    h2 {
        text-align: center;
        margin-bottom: 30px;
        color: #333;
    }
    form {
        margin-bottom: 20px;
    }
    label {
        display: block;
        margin-bottom: 10px;
        color: #333;
    }
    input[type="password"] {
        width: 100%;
        padding: 12px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }
    input[type="submit"] {
        width: 100%;
        background-color: #4CAF50;
        color: #fff;
        border: none;
        padding: 15px 20px;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    input[type="submit"]:hover {
        background-color: #45a049;
    }
    .logout-btn {
        width: 100%;
        background-color: #e74c3c;
        color: #fff;
        border: none;
        padding: 15px 20px;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    .logout-btn:hover {
        background-color: #c0392b;
    }
</style>
</head>
<body>

<div class="container">
    <p style="text-align:center">Welcome ${data.name} !!</p>
    <a href="/logout" class="logout-btn">Logout</a>
    <h3>${errorMsg}</h3>
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
