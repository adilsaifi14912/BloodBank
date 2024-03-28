<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blood Donation and Receiving Requests</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 8px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f8f8f8;
        }
        tr:hover {
            background-color: #f0f0f0;
        }
        button {
            background-color: #87CEEB;
            color: white;
            padding: 8px 16px;
            border: none;
            cursor: pointer;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #4682B4;
        }
        input[type="text"] {
            padding: 6px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }
    </style>
    <script>
        function validateDeclineForm(form) {
            var remarks = form.elements["remarks"].value;
            if (!remarks.trim()) {
                alert("Remarks are compulsory when declining a request.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h1>Blood Donation and Receiving Requests</h1>
    <!-- Filter Form -->
    <form action="/checkRequestsAdmin" method="post">
        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate">

        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate">

        <label for="agentUsername">Agent Username:</label>
        <input type="text" id="agentUsername" name="agentUsername">

        <label for="endUserUsername">End User Username:</label>
        <input type="text" id="endUserUsername" name="endUserUsername">

        <button type="submit">Filter</button>
    </form>

    <!-- Blood Bank Requests Table -->
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Created At</th>
                <th>Created By</th>
                <th>Status</th>
                <th>Type</th>
                <th>Blood Group</th>
                <th>DOB</th>
                <th>Age (in years)</th>
                <th>Quantity</th>
                <th>Actions</th>
                <th>Remarks</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="request" items="${bloodBankRequests}" varStatus="loop">
                <tr>
                    <td>${request.id}</td>
                    <td>${request.username}</td>
                    <td>${request.createdAt}</td>
                    <td>${request.createdBy}</td>
                    <td id="status${loop.index + 1}">${request.status}</td>
                    <td>${request.type}</td>
                    <td>${request.bloodGroup}</td>
                    <td>${request.dob}</td>
                    <td>${request.age}</td>
                    <td>${request.quantity}</td>
                    <td id="actionCell${loop.index + 1}">
                        <form id="acceptForm${loop.index + 1}" action="/acceptRequest" method="post">
                            <input type="hidden" name="requestId" value="${request.id}">
                            <input type="hidden" name="unitsDonated" value="${request.quantity}">
                            <input type="hidden" name="type" value="${request.type}">
                            <input type="hidden" name="bloodGroup" value="${request.bloodGroup}">
                            <input type="hidden" name="username" value="${request.username}">
                            <button type="submit" onclick="handleAccept(${loop.index + 1})">Accept</button>
                        </form>
                        <form id="declineForm${loop.index + 1}" action="/declineRequestWithRemarks" method="post" onsubmit="return validateDeclineForm(this);">
                            <input type="hidden" name="requestId" value="${request.id}">
                            <input type="text" name="remarks" placeholder="Enter remarks">
                            <button type="submit" onclick="handleDecline(${loop.index + 1})">Decline</button>
                        </form>
                    </td>
                    <td>${request.remarks}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <script>
        function handleAccept(index) {
            document.getElementById('acceptForm' + index).submit();
            disableButton('acceptBtn' + index);
            updateActionStatus('actionCell' + index, 'Accepted');
        }

        function handleDecline(index) {
            if (validateDeclineForm(document.getElementById('declineForm' + index))) {
                document.getElementById('declineForm' + index).submit();
                disableButton('declineBtn' + index);
                updateActionStatus('actionCell' + index, 'Declined');
            }
        }

        function disableButton(buttonId) {
            document.getElementById(buttonId).setAttribute('disabled', 'true');
        }

        function updateActionStatus(cellId, status) {
            document.getElementById(cellId).innerHTML = status;
        }
    </script>
</body>
</html>