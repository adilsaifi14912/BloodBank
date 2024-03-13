<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>End User Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #8c1c1c;
            color: #fff;
            padding: 20px;
            text-align: center;
            margin-bottom: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            margin-top: 0;
            font-size: 28px;
        }
        h2 {
            font-size: 24px;
            color: #8c1c1c;
            margin-bottom: 20px;
        }
        p {
            margin: 10px 0;
            color: #555;
        }
        strong {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <header>
        <h1>End User Dashboard</h1>
    </header>
    <div class="container">
        <h2>End User logged in!</h2>
        <p><strong>Username:</strong> ${user.username}</p>
        <p><strong>Created On:</strong> ${user.createdOn}</p>
        <p><strong>Date of Birth:</strong> ${user.dob}</p>
        <p><strong>Contact Number:</strong> ${user.phone}</p>
        <p><strong>Address:</strong> ${user.address}</p>
        <p><strong>Blood Group:</strong> ${user.bloodGroup}</p>
    </div>
</body>
</html>
