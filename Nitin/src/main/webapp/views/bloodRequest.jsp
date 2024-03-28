<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Donate/Receive Blood</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        /* CSS styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            color: #333;
        }

        .header {
            background-color: #dc3545; /* Blood red color */
            color: #fff;
            padding: 20px;
            text-align: center;
            margin-bottom: 20px;
        }

        .logo {
            font-size: 36px;
            font-weight: bold;
            margin: 0;
        }

        .slogan {
            font-size: 18px;
            margin-top: 5px;
        }

        .container {
            max-width: 600px;
            margin-left:450px;
            float:left;
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
        }

        h3 {
            text-align: center;
            color: #dc3545; /* Blood red color */
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            color: #007bff; /* Blue color */
            text-transform: uppercase;
            font-size: 16px;
        }

        input[type="text"],
        input[type="number"],
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .btn {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            display: block;
            margin: 20px auto;
            text-align: center;
            max-width: 200px;
            text-decoration: none;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        .btn.btn-danger {
            background-color: #dc3545; /* Blood red color */
            color: #fff;
            padding: 15px 25px; /* Increased padding */
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 18px; /* Increased font size */
            display: inline-block;
            margin-right: 10px; /* Added margin between buttons */
            margin-left:200px;
        }
        .small-btn {
            padding: 1px; /* Adjust padding to make the button smaller */
        }

        .small-btn a {
            font-size: 14px; /* Adjust font size of the link within the button */
        }

        .small-btn a:hover {
            text-decoration: none; /* Remove underline on hover */
        }

    </style>
</head>
<body>

<div class="header">
    <h1 class="logo">Blood Bank</h1>
    <p class="slogan">Saving Lives, One Donation at a Time</p>
    <p> ${formatError} </p>
</div>
    <button type="button" class=""><a href="/user" class="btn blue pad">Back</a></button>

<div class="container">
    <h3>Donate/Receive Blood</h3>
    <form action="bloodRequirement" method="post">
        <div class="form-group">
            <label for="type">Type:</label>
            <select name="type" id="type">
                <option value="donate">Donate</option>
                <option value="receive">Receive</option>
            </select>
        </div>
        <div class="form-group">
            <label for="quantity">Quantity (in units - 1 unit=450ml):</label>
            <input type="number" id="quantity" name="quantity" required>
        </div>
            <div class="form-group">
            <label for="bloodGroup">Blood Group:</label>
            <select name="bloodGroup" id="bloodGroup" required>
                <option value="A+">A+</option>
                <option value="A-">A-</option>
                <option value="B+">B+</option>
                <option value="B-">B-</option>
                <option value="AB+">AB+</option>
                <option value="AB-">AB-</option>
                <option value="O+">O+</option>
                <option value="O-">O-</option>
            </select>
        </div>
        <button type="submit" class="btn btn-danger"> Request</button>
    </form>
</div>

</body></html>
