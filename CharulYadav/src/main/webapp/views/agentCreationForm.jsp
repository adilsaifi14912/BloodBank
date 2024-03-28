<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Agent</title>
    <style>
        body {
            background-color: #f8f8fa; /* light gray */
            font-family: 'Arial', sans-serif;
            color: #495057; /* dark gray */
        }

        h1 {
            color: #6a7eff; /* pastel blue */
        }

        label {
            color: #008080; /* teal */
        }

        input[type="text"],
        input[type="date"] {
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #d3d3d3; /* light gray */
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #77dd77; /* pastel green */
            color: #495057; /* dark gray */
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #aaf0d1; /* light green */
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Create Agent</h1>
    <form action="createAgent" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="name">Name:</label>
        <input type="text" id="name" name="name">

        <label for="dob">DOB:</label>
        <input type="date" id="dob" name="dob" required>

        <label for="bloodGroup">Blood Group:</label>
        <input type="text" id="bloodGroup" name="bloodGroup" required>

        <label for="commission">Commission:</label>
        <input type="text" id="commission" name="commission" required><br>

        <input type="submit" value="Create Agent">
    </form>
</div>
</body>
</html>
