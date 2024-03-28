<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agent Dashboard</title>
    <!-- Font Awesome CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h2 {
            margin-bottom: 20px;
            color: #333;
        }

        .dropdown {
            position: relative;
            margin-bottom: 20px;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
            z-index: 1;
            border: 1px solid #ddd;
            border-radius: 5px;
            min-width: 160px;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown-content a:hover {
            background-color: #f1f1f1;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .add-agent-button {
            text-decoration: none;
            font-weight: bold;
            color: #fff;
            padding: 10px 20px;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .add-agent-button:hover {
            background-color: #0056b3;
        }

        .logout-button {
            margin-top: auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }
        .button-container {
            display: inline-block; /* Display buttons in one line */
        }

        .add-agent-button {
            margin-right: 10px; /* Add space between buttons */
        }


    </style>
</head>
<body>
    <div class="container">
        <h2>Welcome to Admin Dashboard!!</h2>
        <div class="button-container">
            <a href="/createAgent" class="add-agent-button">Create Agent</a>
            <a href="/userBloodRequest" class="add-agent-button">User Request</a>
            <a href="/bloodStock" class="add-agent-button">BloodStock</a>
            <a href="/report" class="add-agent-button">Requests Report</a>
            <a href="/coinReport" class="add-agent-button">Coin Report</a><br><br><br>
            <a href="/logout" class="add-agent-button">Log Out</a>
        </div><br><br>

         <div class="action-buttons">
                    <!-- Sorting options -->
                    <form  action="endUserList" method="post">
                        <select name="sortBy">
                        <option value="select" >SortBy</option>
                            <option value="name">Sort by Name</option>
                            <option value="createdBy">Sort CreatedBy</option>
                            <option value="bloodGroup">Sort by Blood Group </option>
                        </select>
                        <button type="submit">Sort</button>


                    <!-- Filtering options -->
                        <select id="filterBy" name="filterBy">
                            <option value="select" >FilterBy</option>
                            <option value="notLoggedIn">Not Logged In Users</option>
                            <option value="byAgent">By Agent</option>
                            <option value="byUsername">By Username</option>
                            <option value="createdBetween">By Created Between</option>
                        </select>
                        <input type="text" id="username" name="username" placeholder="Enter Username" style="display: none;">
                        <input type="date" id="startDate" name="startDate" placeholder="Start Date" style="display: none;">
                        <input type="date" id="endDate" name="endDate" placeholder="End Date" style="display: none;">
                        <button type="submit">Filter</button>
                    </form>
                </div><br>
                <div class="enduser-list">
                    <table>
                        <thead>
                            <tr>
                                <th>Serial No.</th>
                                <th>Name</th>
                                <th>Username</th>
                                <th>Created Time</th>
                                <th>CreatedBy</th>
                                <th>DOB</th>
                                <th>Blood Group</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${usersList}"  varStatus="loop">
                                    <tr>
                                        <td>${loop.index+1}</td>
                                        <td>${user.name}</td>
                                        <td>${user.username}</td>
                                        <td>${user.createdOn}</td>
                                        <td>${user.createdBy}</td>
                                        <td>${user.dob}</td>
                                        <td>${user.bloodGroup}</td>
                                    </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

    </div>

    <!-- Font Awesome JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
     <script>
            document.getElementById("filterBy").addEventListener("change", function() {
                var selectedOption = this.value;
                var usernameInput = document.getElementById("username");
                var startDateInput = document.getElementById("startDate");
                var endDateInput = document.getElementById("endDate");

                if (selectedOption === "byUsername") {
                    usernameInput.style.display = "inline-block";
                    startDateInput.style.display = "none";
                    endDateInput.style.display = "none";
                } else if (selectedOption === "createdBetween") {
                    startDateInput.style.display = "inline-block";
                    endDateInput.style.display = "inline-block";
                    usernameInput.style.display = "none";
                } else {
                    startDateInput.style.display = "none";
                    endDateInput.style.display = "none";
                    usernameInput.style.display = "none";
                }
            });
        </script>
</body>
</html>

