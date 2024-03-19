<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%@ page import="java.util.List" %>
 <%@ page import="in.sp.main.dto.RegisterDTO" %>
<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
    <h1>Admin Dashboard - List of Signed-up Users</h1>

    <div class="profile-info">
            <p><strong>Username:</strong> ${user.getUsername()}</p>
            <p><strong>Date of Birth:</strong> ${user.getDateOfBirth()}</p>
            <p><strong>Phone Number:</strong>${user.getPhoneNumber()}</p>
            <p><strong>Address:</strong>${user.getCity()}</p>
        </div>
<table>
        <thead>
            <tr>
                <th>Username</th>
                <th>Date of Birth</th>
                <th>Phone Number</th>
                <th>Address</th>
                <th>Blood Group</th>
            </tr>
        </thead>
        <tbody>

            <%
            List<RegisterDTO> allUsers = (List<RegisterDTO>) request.getAttribute("signedupUsers");
            for (RegisterDTO endUser : allUsers) { %>
                <tr>
                    <td><%= endUser.getUsername() %></td>
                    <td><%= endUser.getDateOfBirth() %></td>
                    <td><%= endUser.getPhoneNumber() %></td>
                    <td><%= endUser.getCity() %></td>
                    <td><%= endUser.getBloodGroup() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
