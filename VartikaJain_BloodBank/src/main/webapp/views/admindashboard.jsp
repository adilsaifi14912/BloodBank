<!DOC TYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <!-- Include your CSS files here -->
    <link rel="stylesheet" href="styles.css">
    <style>
        /* Add your CSS styles here */
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
        h1 {
            margin-top: 0;
        }
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 20px;
        }
        .container form {
            margin-bottom: 10px;
        }
        button {
            padding: 15px 30px; /* Adjust padding to increase button size */
            font-size: 18px; /* Increase font size for better visibility */
        }
    </style>
</head>
<body>
    <header>
        <h1>Admin Dashboard</h1>
    </header>
    <h2>Admin logged in!</h2>
    <p><strong>UserName:</strong>${userModel.username}</p>
    <p><strong>CreatedOn:</strong> ${userModel.createdOn}</p>
    <p><strong>Date of Birth:</strong> ${userModel.dob}</p>
    <p><strong>Contact Number:</strong> ${userModel.contactNumber}</p>
    <p><strong>Address:</strong> ${userModel.address}</p>
    <p><strong>Blood Group:</strong> ${userModel.bloodGroup}</p>
    <p><strong>Total Coins:</strong> ${userModel.coin}</p><br>
    <div class="container">

        <form action="usermodel" method="get">
            <button type="submit">User Model Table</button>
        </form>
        <form action="addagent" method="get">
            <button type="submit">Add Agent</button>
        </form>
        <form action="seerequest" method="get">
            <button type="submit">Request Table</button>
        </form>
        <form action="seestock" method="get">
           <button type="submit">Blood Stock</button>
        </form>
        <form action="seebloodreport" method="get">
            <input type="hidden" name="username" value="${userModel.username}">
            <button type="submit">Request Reports</button>
        </form>
        <form action="coinreport" method="get">
                    <input type="hidden" name="username" value="${userModel.username}">
                    <input type="hidden" name="role" value="${userModel.role}">
                   <button type="submit">Coin Report</button>
                </form>
                <form action="logout" method="get">
                            <button type="submit">Logout</button>
                        </form>
    </div>
</body>
</html>
