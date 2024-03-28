<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="in.sp.main.dto.RegisterDTO" %>
<%@ page import="in.sp.main.entities.UserModel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="in.sp.main.dto.BloodBankDTO" %>
<html>
<head>
    <title>Agent Dashboard</title>
         <div class="profile-info">
             <p><strong>Username:</strong> ${user.getUsername()}</p>
             <p><strong>Date of Birth:</strong> ${user.getDateOfBirth()}</p>
             <p><strong>Phone Number:</strong>${user.getPhoneNumber()}</p>
             <p><strong>Address:</strong>${user.getCity()}</p>
         </div>
<form action="createAgent" method="get">
    <button type="submit">Create User </button>
</form>

<form action="coins" method="get">
    <button type="submit">Coins</button>
</form>


    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* CSS for table */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<form action="showBloodRequests" method="get">
    <button type="submit">Blood Request</button>
</form>

<form action="sortByName" method="get">
    <button type="submit">Sort By Name</button>
</form>

<form action="sortByBloodGroup" method="get">
    <button type="submit">Sort by BloodGroup</button>
</form>

<form action="bloodRequestsAgentDashboard" method="get">
    <button type="submit">Approved User(5.2)</button>
</form>

<!-- Table to display users -->
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

    <% if (request.getAttribute("sortedUsers") == null) {
        List<RegisterDTO> allUser = (List<RegisterDTO>) session.getAttribute("signedupUsers");
        for (RegisterDTO endUser : allUser) { %>
            <tr>
                <td><%= endUser.getUsername() %></td>
                <td><%= endUser.getDateOfBirth() %></td>
                <td><%= endUser.getPhoneNumber() %></td>
                <td><%= endUser.getCity() %></td>
                <td><%= endUser.getBloodGroup() %></td>
            </tr>
    <% }
    } else {
        List<UserModel> allUsers = (List<UserModel>) request.getAttribute("sortedUsers");
        for (UserModel endUser : allUsers) { %>
            <tr>
                <td><%= endUser.getUsername() %></td>
                <td><%= endUser.getDateOfBirth() %></td>
                <td><%= endUser.getPhoneNumber() %></td>
                <td><%= endUser.getCity() %></td>
                <td><%= endUser.getBloodGroup() %></td>

            </tr>
    <% }
    }

    if (request.getAttribute("filteredUsers") != null) {
        List<UserModel> users = (List<UserModel>) request.getAttribute("filterUsers");
        for (UserModel user : users) { %>
            <tr>
                <td><%= user.getUsername() %></td>
                <td><%= user.getDateOfBirth() %></td>
                <td><%= user.getPhoneNumber() %></td>
                <td><%= user.getCity() %></td>
                <td><%= user.getBloodGroup() %></td>

            </tr>
    <% }
    } %>

    </tbody>
</table>

 <table>
      <table>
          <thead>
              <tr>
                  <th>Blood Group</th>
                  <th>Quantity</th>
                  <th>Rate</th>
                  <th>Coins</th>

              </tr>
          </thead>
          <h2>Report  :(3,6)   Total Blood Request, Commission and Coins</h2>
            <tbody>
                 <%
                     List<BloodBankDTO> allBloodRequests = (List<BloodBankDTO>) request.getAttribute("agentCoins");
                     Map<String, Integer> rateMap = new HashMap<>();
                     // Define the rate for each blood group
                     // Add rates for other blood groups as needed

                     Map<String, Integer> bloodStockMap = new HashMap<>();
                     for (BloodBankDTO bloodRequest : allBloodRequests) {
                         if (bloodRequest.getRequestType().equals("donation")) {
                             String bloodGroup = bloodRequest.getBloodGroup();
                             int coins=bloodRequest.getCoins();
                             int quantity = (int) bloodRequest.getQuantity(); // Cast to int
                             double rate = coins*0.10; // Get rate for blood group
                             bloodStockMap.put(bloodGroup, bloodStockMap.getOrDefault(bloodGroup, 0) + quantity);
                 %>
                     <tr>
                         <td><%= bloodGroup %></td>
                         <td><%= quantity %></td>
                         <td><%= rate %></td>
                         <td><%= coins %></td>
                     </tr>
                 <%
                         }
                     }
                 %>
             </tbody>
         </table>

    </table>





<!-- Total sum of blood stock -->
<div>Total Blood Stock: <%= request.getAttribute("totalBloodStock") %></div>
<!-- Logout button -->
<button class="btn btn-primary"><a href="logout" style="color: white;">Logout</a></button>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
