<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blood Donation and Receiving Form</title>
    <style>
        body {
            background-color: #f8f8fa; /* light gray */
            font-family: 'Arial', sans-serif;
            color: #495057; /* dark gray */
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1 {
            color: #6a7eff; /* pastel blue */
        }

        label {
            color: #008080; /* teal */
            display: block;
            margin-top: 10px;
        }

        select,
        input[type="number"],
        input[type="text"] {
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #d3d3d3; /* light gray */
            border-radius: 4px;
            width: 30%;
            box-sizing: border-box;
        }

        select:focus,
        input[type="number"]:focus,
        input[type="text"]:focus {
            outline: none;
            border-color: #6a7eff; /* pastel blue */
        }

        input[type="submit"] {
            background-color: #77dd77; /* pastel green */
            color: #495057; /* dark gray */
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
        }

        input[type="submit"]:hover {
            background-color: #aaf0d1; /* light green */
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Blood Donation and Receiving Form</h1>
    <form action="bloodSubmit" method="post">

        <label for="type">Action:</label>
        <select id="type" name="type" required>
            <option value="donate">Donate Blood</option>
            <option value="receive">Receive Blood</option>
        </select>

        <label for="bloodGroup">Blood Type:</label>
        <select id="bloodGroup" name="bloodGroup" required>
            <option value="A+">A+</option>
            <option value="B+">B+</option>
            <option value="AB+">AB+</option>
            <option value="AB-">AB-</option>
            <option value="O+">O+</option>
            <option value="O-">O-</option>
        </select>

        <label for="quantity">Quantity (ml):</label>
        <input type="number" id="quantity" name="quantity" required>

        <label for="purpose">Purpose:</label>
        <input type="text" id="purpose" name="purpose" required>

        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
