<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOC TYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
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
        button {
             padding: 10px 20px; /* Adjust padding to increase button size */
             font-size: 16px; /* Increase font size for better visibility */
        }
    </style>
    <body>
        <header>
            <h1>Admin Dashboard</h1>
        </header>
            <h2>User Request Report</h2>
            <table border="1">
              <thead>
                    <tr>
                        <th>Blood Group</th>
                        <th>Accepted</th>
                        <th>Rejected</th>
                        <th>Coin</th>
                    </tr>
                    </thead>
                    <tbody>
                                    <c:forEach var="user" items="${userRequestReport}">
                                        <tr>
                                            <td>${user.BloodGroup}</td>
                                            <td>${user.Accepted}</td>
                                            <td>${user.Rejected}</td>
                                            <td>${user.CoinValue}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                <form id="logoutForm" action="logout" method="get">
                    <button type="submit">Logout</button>
                </form>
    </body>
</html>