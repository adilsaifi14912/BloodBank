 <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agent Logged In</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
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

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
            color: #333;
        }

        .user-details {
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f9f9;
        }

        .user-details div {
            margin-bottom: 10px;
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
        <h2>${dto.role} Logged In</h2>

        <div class="user-details">
            <div>
                <label>Name:</label>
                <div>${dto.name}</div>
            </div>
            <div>
                <label>Username:</label>
                <div>${dto.username}</div>
            </div>
            <div>
                <label>Created On:</label>
                <div>${dto.createdOn}</div>
            </div>
            <div>
                <label>Created By:</label>
                <div>${dto.createdBy}</div>
            </div>
            <div>
                <label>DOB:</label>
                <div>${dto.dob}</div>
            </div>
            <div>
                <label>Blood Group:</label>
                <div>${dto.bloodGroup}</div>
            </div>
        </div>

        <div class="back-button">
            <a href="/">Log Out</a>
        </div>
    </div>
</body>
</html>
