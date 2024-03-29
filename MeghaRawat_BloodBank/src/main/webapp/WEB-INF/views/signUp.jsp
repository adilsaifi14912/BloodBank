<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up Form</title>
    <style>
    h2{
    text-align: center;
    }
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        .signup-form {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .form-group input[type="text"],
        .form-group input[type="password"],
        .form-group input[type="date"],
        .form-group input[type="tel"] {
            width: calc(100% - 22px);
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
        }

        .form-group input[type="submit"] {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 5px;
            background-color: #007bff;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
        }

        .form-group input[type="submit"]:hover {
            background-color: #0056b3;
        }

        button[type="submit"] {
                    width: 100%;
                    padding: 10px;
                    border: none;
                    border-radius: 3px;
                    background-color: #007bff;
                    color: #fff;
                    cursor: pointer;
                }

                button[type="submit"]:hover {
                    background-color: #0056b3;
                }
    </style>
</head>
<body>
    <form action="addData" method="post" class="signup-form">
        <h2>Sign Up</h2>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>

        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div class="form-group">
            <label for="dob">Date of birth:</label>
            <input type="date" id="dob" name="dob" required>
        </div>

        <div class="form-group">
            <label for="phone">Phone Number:</label>
            <input type="tel" id="phone" name="phone" pattern="[0-9]{10}" required>
        </div>

        <button type="submit" class="btn">Sign Up</button>
    </form>
</body>
</html>
