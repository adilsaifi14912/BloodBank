<!-- welcome.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to the Blood Bank</title>
    <!-- Add your CSS stylesheets or link to Bootstrap CSS here -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            text-align: center;
        }
        h1 {
            color: #8c1c1c;
            font-size: 48px;
            margin-bottom: 20px;
        }
        p {
            color: #333;
            font-size: 20px;
            margin-bottom: 40px;
        }
        .btn {
            text-decoration: none;
            background-color: #8c1c1c;
            color: #fff;
            padding: 10px 20px;
            font-size: 18px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            margin-right: 10px;
        }
        .btn:hover {
            background-color: #6b1515;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to the Blood Bank</h1>
        <p>Your source for donating and receiving blood donations</p>
        <a href="login" class="btn">login</a>
        <a href="signup" class="btn">signup</a>
    </div>
</body>
</html>
