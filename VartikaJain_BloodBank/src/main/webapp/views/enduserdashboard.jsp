<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOC TYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>End User Dashboard</title>
    <!-- Include your CSS files here -->
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #333;
            color: #fff;
            padding: 20px;
            text-align: center;
        }
        nav {
            background-color: #444;
            color: #fff;
            padding: 10px;
        }
        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            text-align: center;
        }
        nav ul li {
            display: inline;
            margin-right: 20px;
        }
        nav ul li a {
            text-decoration: none;
            color: #fff;
            font-weight: bold;
        }
        main {
            margin: 20px;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            margin-top: 0;
        }
        section {
            margin-bottom: 20px;
        }
        section p {
            margin: 5px 0;
        }
        footer {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }
        .button-container {
            text-align: center;
        }
        button {
            padding: 10px 20px; /* Adjust padding to increase button size */
            font-size: 16px; /* Increase font size for better visibility */
            margin: 5px; /* Add some margin between buttons */
        }
    </style>
</head>
<body>
    <header>
        <h1>End User Dashboard</h1>
    </header>
                <p><strong>UserName:</strong>${userModel.username}</p>
                <p><strong>CreatedOn:</strong> ${userModel.createdOn}</p>
                <p><strong>Date of Birth:</strong> ${userModel.dob}</p>
                <p><strong>Contact Number:</strong> ${userModel.contactNumber}</p>
                <p><strong>Address:</strong> ${userModel.address}</p>
                <p><strong>Blood Group:</strong> ${userModel.bloodGroup}</p><br>
                <p><strong>Total Coins:</strong> ${userModel.coin}</p><br>
    <div class="button-container">
        <h2>End User logged in!</h2>
        <!-- User information -->

        <form action="makerequest" method="get">
            <button type="submit">Request Service</button>
        </form>
        <form action="seemyrequests" method="get">
            <button type="submit">See Requests</button>
        </form>
        <form action="coinreport" method="get">
            <button type="submit">Coin Report</button>
        </form>
        <form action="logout" method="get">
                    <button type="submit">Logout</button>
                </form>
    </div>
</body>
</html>
