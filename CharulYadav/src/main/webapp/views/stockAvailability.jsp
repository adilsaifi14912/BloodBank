<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Stock Availability</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8fa; /* Light gray */
            color: #495057; /* Dark gray */
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1 {
            color: #008080; /* Teal */
        }

        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
        }

        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd; /* Light gray */
            text-align: left;
        }

        th {
            background-color: #f2f2f2; /* Light gray */
        }

        tr:nth-child(even) {
            background-color: #f0f0f0; /* Light gray */
        }
    </style>
</head>
<body>
    <h1>Stock Availability</h1>
    <table>
        <thead>
            <tr>
                <th>Blood Group</th>
                <th>Units Available</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="entry" items="${unitsByBloodGroup}">
                <tr>
                    <td>${entry.key}</td>
                    <td>${entry.value}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
