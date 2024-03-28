<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Blood Request Status</title>
</head>
<body>

<h1>User Blood Request Status</h1>

<h2>Blood Request By User</h2>

<table border="1">
    <thead>
        <tr>
            <th>Blood Type</th>
            <th>Quantity</th>
            <th>Status</th>
            <!-- Add more columns as needed -->
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${bloodRequestByUser}" var="request">
            <tr>
                <td>${request.getRequestType()}</td>
                <td>${request.getQuantity()}</td>
                <td>${request.getStatus()}</td>
                <!-- Add more cells to display other details -->
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>

