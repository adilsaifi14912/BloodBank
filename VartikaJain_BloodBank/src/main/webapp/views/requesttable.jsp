<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOC TYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <!-- Include your CSS files here -->
    <link rel="stylesheet" href="styles.css">
    <style>
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
        nav {
            background-color: #444;
            color: #fff;
            padding: 10px;
        }
        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            text-align: center;
        }
        nav ul li {
            display: inline;
            margin-right: 20px;
        }
        nav ul li a {
            text-decoration: none;
            color: #fff;
            font-weight: bold;
        }
        main {
            margin: 20px;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            margin-top: 0;
        }
        section {
            margin-bottom: 20px;
        }
        section p {
            margin: 5px 0;
        }
        footer {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }
        .btn.accept-btn {
            background-color: green;
            color: white;
        }
        .btn.reject-btn {
            background-color: red;
            color: white;
        }
        .btn.accept-btn:hover {
            background-color: darkgreen;
        }
        .btn.reject-btn:hover {
            background-color: darkred;
        }
    </style>
</head>
<body>
    <h2>User Request Table</h2>
    <form action="filter" method="get">
    <label for="filterBy">Filter By:</label>
                <select id="filterBy" name="filterBy">
                    <option value="">All</option>
                    <option value="status">Status</option>
                    <option value="createdBetween">Created Between</option>
                    <option value="username">Username</option>
                    <!-- Add more filtering options here if needed -->
                </select>
                <!-- Added input fields for date range and username -->
                <div id="createdBetweenFilters" style="display: none;">
                    <label for="startDate">Start Date:</label>
                    <input type="date" id="startDate" name="startDate">
                    <label for="endDate">End Date:</label>
                    <input type="date" id="endDate" name="endDate">
                </div>
                <div id="statusFilter" style="display: none;">
                        <label>Status:</label>
                        <label><input type="radio" name="statusFilter" value="Pending"> Pending</label>
                        <label><input type="radio" name="statusFilter" value="Accepted"> Accepted</label>
                        <label><input type="radio" name="statusFilter" value="Rejected"> Rejected</label>
                    </div>
                <div id="usernameFilter" style="display: none;">
                                <label for="username">Username:</label>
                                <input type="text" id="username" name="username">
                            </div>
                            <button type="submit">Apply</button>
                            </form>
    <table border="1">
        <thead>
            <tr>
                <th>ReqID</th>
                <th>Username</th>
                <th>Created By</th>
                <th>Created On</th>
                <th>Blood Group</th>
                <th>Request Type</th>
                <th>Quantity</th>
                <th>Dob</th>
                <th>Age</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${userRequestList}">
                <tr>
                    <td>${user.ReqId}</td>
                    <td>${user.username}</td>
                    <td>${user.createdBy}</td>
                    <td>${user.createdOn}</td>
                    <td>${user.bloodGroup}</td>
                    <td>${user.type}</td>
                    <td>${user.quantity}</td>
                    <td>${user.dob}</td>
                    <td>${user.age}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.status eq 'Pending'}">
                                <div class="button-group">
                                    <form action="statusupdate" method="POST">
                                        <input type="hidden" name="status" value="accepted">
                                        <input type="hidden" name="reqId" value="${user.ReqId}">
                                        <button type="submit" class="btn accept-btn">Accept</button>
                                    </form>
                                    <form action="statusupdate" method="POST" onsubmit="return validateDeclineForm(this);">
                                        <input type="hidden" name="status" value="rejected">
                                        <input type="hidden" name="reqId" value="${user.ReqId}">
                                        <!-- Add remark input -->
                                        <input type="text" name="remark" placeholder="Enter remark" required>
                                        <button type="submit" class="btn reject-btn">Reject</button>
                                    </form>
                                </div>
                            </c:when>
                            <c:when test="${user.status eq 'rejected'}">
                                <span style="color: red;">Rejected</span>
                                <!-- Display remark if available -->
                                <c:if test="${not empty user.remark}">
                                    <p>Remark: ${user.remark}</p>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <span style="color: green;">Accepted</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <script>
            function validateDeclineForm(form) {
                var remarks = form.elements["remarks"].value;
                if (!remarks.trim()) {
                    alert("Remarks are compulsory when rejecting a request.");
                    return false;
                }
                return true;
            }
        document.getElementById('filterBy').addEventListener('change', function() {
            var selectedValue = this.value;
            var createdBetweenFilters = document.getElementById('createdBetweenFilters');
            var usernameFilter = document.getElementById('usernameFilter');
            var statusFilter = document.getElementById('statusFilter');

            if (selectedValue === 'createdBetween') {
                createdBetweenFilters.style.display = "block"; // Change to block to display
                usernameFilter.style.display = "none";
                statusFilter.style.display = "none";
            } else if (selectedValue === 'status') {
                statusFilter.style.display = "block"; // Change to block to display
                createdBetweenFilters.style.display = "none";
                usernameFilter.style.display = "none";
            } else if (selectedValue === 'username') {
                createdBetweenFilters.style.display = "none";
                usernameFilter.style.display = "block"; // Change to block to display
                statusFilter.style.display = "none";
            } else {
                createdBetweenFilters.style.display = "none";
                usernameFilter.style.display = "none";
                statusFilter.style.display = "none";
            }
        });
    </script>

</body>
</html>
