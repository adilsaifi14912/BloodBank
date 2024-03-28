<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- end_user_creation_form.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>End User Creation</title>
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

        input[type="text"],
        input[type="date"],
        input[type="bloodGroup"],
        input[type="hidden"] {
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #d3d3d3; /* light gray */
            border-radius: 4px;
            width: 20%;
            box-sizing: border-box;
        }

        input[type="text"]:focus,
        input[type="date"]:focus,
        input[type="bloodGroup"]:focus,
        input[type="hidden"]:focus {
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
    <h1>End User Creation</h1>
    <form action="endUserCreationForm" method="post">

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required minlength="3" maxlength="15">

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="dob">DOB:</label>
        <input type="date" id="dob" name="dob" required>

        <label for="bloodGroup">Blood Group:</label>
        <input type="text" id="bloodGroup" name="bloodGroup" required>

        <input type="hidden" id="role" name="role" value="enduser">

        <input type="submit" value="Create End User">
    </form>
</div>
<script>
    document.getElementById('endUserCreationForm').addEventListener('input', function() {
        document.getElementById('role').value = 'enduser';
    });
</script>
</body>
</html>
