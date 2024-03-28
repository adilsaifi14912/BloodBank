<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
    <style>
        /* Reset styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            background-color: #f7f7f7;
            color: #333;
        }

        .container {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        /* Header styles */
        header {
            background-color: #333;
            color: #fff;
            padding: 10px;
        }

        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .logo a {
            color: #fff;
            text-decoration: none;
            font-size: 24px;
            font-weight: bold;
        }

        .user-info {
            display: flex;
            align-items: center;
        }

        .user-info span {
            margin-right: 10px;
        }

        .logout {
            color: #fff;
            text-decoration: none;
            padding: 5px 10px;
            background-color: #ff6347;
            border-radius: 4px;
        }

        /* Main content styles */
        .main {
            display: flex;
            flex: 1;
        }

        .sidebar {
            background-color: #333;
            color: #fff;
            padding: 20px;
            width: 200px;
        }

        .sidebar ul {
            list-style-type: none;
            padding: 0;
        }

        .sidebar li {
            margin-bottom: 10px;
        }

        .sidebar a {
            color: #fff;
            text-decoration: none;
            padding: 10px;
            display: block;
        }

        .sidebar a:hover,
        .sidebar a.active {
            border-radius: 10px;
            background-color: #555;
        }

        .sidebar a.active {
            background-color: #d32f2f;
        }

        .main-content {
            flex: 1;
            padding: 65px;
        }

        .dashboard-container, {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #d32f2f;
            margin-bottom: 30px;
        }

        .info-section {
            margin-bottom: 30px;
        }

        .info-section h3 {
            margin-bottom: 10px;
        }

        .info-section p {
            margin-bottom: 5px;
        }

        /* --------------  My CSS ---------------*/

        /* Donate/Receive Blood */
        .donation-form {
            text-align: end;
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .donation-form h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
        }


        input[type="text"],
        input[type="number"] {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button[type="submit"] {
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }

        /* CSS for Table Status */
            table {
                    width: 100%;
                    border-collapse: collapse;
                }
                th, td {
                    padding: 8px;
                    text-align: left;
                    border-bottom: 1px solid #ddd;
                }
                tr:nth-child(even) {
                    background-color: #f2f2f2;
                }
                .accepted {
                    background-color: #c9f9c9;
                }
                .rejected {
                    background-color: #ffb3b3;
                }
                .pending {
                    background-color: #ffff99;
                }
                .blood-group-info {
                    display: inline-block;
                    margin-bottom: 15px;
                    padding: 10px;
                    text-align: center;
                    background-color: #f5f5f5;
                    color: #333;
                    border: 1px solid #ddd;
                    border-radius: 5px;
                }
                .accepted-info {
                    background-color: #c9f9c9;
                    color: #228B22;
                }
                /* Styling thead */
                .table-header {
                    background-color: #007bff;
                    color: white;
                }
                .table-header th {
                    padding: 10px;
                    font-weight: bold;
                    text-transform: uppercase;
                    border-bottom: 2px solid #ddd; /* increase border size */
                }

        /* Footer styles */
        footer {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <header>
            <nav class="navbar">
                <div class="logo">
                    <a href="#">Blood Bank</a>
                </div>
                <div class="user-info">
                    <span>Welcome, ${data.name}</span>
                    <a href="/logout" class="logout">Logout</a>
                </div>
            </nav>
        </header>