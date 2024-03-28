<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blood Requests Report</title>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0;
            }
            .container {
                max-width: 1000px;
                margin: 20px auto;
            }
            .table {
                color: #fff;
                background-color: #dc3545;
                border-spacing: 30px; /* Increased spacing between rows and columns */
            }
            .table thead th {
                border-color: #dc3545;
            }
            .table-striped tbody tr:nth-of-type(odd) {
                background-color: #ff9999;
            }
            .table-hover tbody tr:hover {
                background-color: #ff6666;
            }
            .blood-group {
                font-weight: bold;
            }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center text-danger">Blood Requests Report</h1>
    <button type="button" class="btn btn-primary mt-5">
    <c:if test="${user.getRole().equals('admin')}">
        <a href="/admin" class="btn blue pad">Back</a>
    </c:if>

        <c:if test="${user.getRole().equals('agent')}">
            <a href="/agent" class="btn blue pad">Back</a>
        </c:if>

    </button>
    <div class="table-responsive">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Blood Group</th>
                <th>Approved Requests</th>
                <th>Rejected Requests</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="bloodGroupEntry" items="${bloodReport}">
                <tr>
                    <td class="blood-group">${bloodGroupEntry.key}</td>
                    <td>${bloodGroupEntry.value.approved.count} Requests (${bloodGroupEntry.value.approved.units} Units, ${bloodGroupEntry.value.approved.coins} Coins)</td>
                    <td>${bloodGroupEntry.value.rejected.count} Requests (${bloodGroupEntry.value.rejected.units} Units, ${bloodGroupEntry.value.rejected.coins} Coins)</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
