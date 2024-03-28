<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>End User Dashboard</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        h1, h2, h3 {
            text-align: center;
            margin-top: 20px;
        }

        h1 {
            color: #007bff; /* End User Dashboard heading color */
        }

        p {
            margin-bottom: 10px;
            text-align: center;
        }

        button {
                    padding: 10px 20px;
                    background-color: #007bff;
                    color: #fff;
                    border: none;
                    border-radius: 5px;
                    cursor: pointer;
                    transition: background-color 0.3s ease;
                }

                button:hover {
                    background-color: #0056b3;
                }
    </style>
</head>
<body>
    <div class="container">
        <h1>End User Dashboard</h1>
        <h2>End User Logged In</h2>
        <div class="user-details">
            <h3>User Details:</h3>
            <p>Username: ${userModel.username}</p>
            <p>Name: ${userModel.name}</p>
            <p>Role: ${userModel.role}</p>
            <p>Created Date and Time: ${userModel.created_date_time}</p>
            <p>Created By: ${userModel.createdBy}</p>
            <p>Date of Birth: ${userModel.dob}</p>
            <p>Coins:${userModel.coins}</p>
        </div>

        <form action="createRequest" style="text-align: center">
             <button type="submit">Add Request</button>
        </form>
        <form action="endUserRequestStatus"  method="post" style="text-align: center">

            <button type ="submit">Show Request Status</button>
        </form>
        <form action="logout" style="text-align: right;">
            <button type="submit">Logout</button>
        </form>
    </div>
</body>
</html>
