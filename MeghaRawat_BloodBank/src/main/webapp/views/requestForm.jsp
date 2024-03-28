<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Request Blood Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        .form-container {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .form-title {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .form-group select,
        .form-group input[type="number"],
        .form-group input[type="text"] {
            width: calc(100% - 22px); /* Adjusted width */
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
        }

        .form-submit button[type="submit"] {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 5px;
            background-color: #007bff;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .form-submit button[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="form-container">
    <!-- Request Form -->
    <form action="addRequest" method="post">
        <h2 class="form-title">Request Blood</h2>
        <div class="form-group">
            <label for="type">Type:</label>
            <select id="type" name="type">
                <option value="donate">Donate Blood</option>
                <option value="request">Request Blood</option>
            </select>
        </div>
        <div class="form-group">
            <label for="bloodGroup">Blood Group:</label>
            <select id="bloodGroup" name="bloodGroup">
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
        <div class="form-group">
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" min="1" required>
        </div>
        <div class="form-submit">
            <button type="submit">Submit</button>
        </div>
    </form>
</div>

</body>
</html>
