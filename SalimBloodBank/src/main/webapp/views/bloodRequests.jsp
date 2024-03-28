<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="in.sp.main.dto.BloodBankDTO" %>
<html>
<head>
    <title>Admin Dashboard - Blood Requests</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .button-container {
            margin-bottom: 20px;
        }
        .button-container button {
            margin-right: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .action-buttons {
            display: flex;
            justify-content: space-between;
        }
        .action-buttons button {
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .action-buttons button.approve {
            background-color: #4CAF50;
            color: white;
        }
        .action-buttons button.reject {
            background-color: #f44336;
            color: white;
        }
        .action-buttons button[disabled] {
            background-color: #ccc;
            cursor: not-allowed;
        }
    </style>
</head>
<body>
    <div class="button-container">
      <form action="filterByStatus" method="get">
          <label for="status">Filter by Status:</label>
          <select id="status" name="status">
              <option value="pending">Pending</option>
              <option value="accepted">Approved</option>
              <option value="rejected">Rejected</option>
              <!-- Add more options as needed -->
          </select>
          <button type="submit">Filter</button>
      </form>
      <form action="filterByDate" method="get">
          <label for="startDate">Start Date:</label>
          <input type="date" id="startDate" name="startDate">
          <label for="endDate">End Date:</label>
          <input type="date" id="endDate" name="endDate">
          <button type="submit">Filter</button>
      </form>


    </div>

    <h1>Admin Dashboard - List of Blood Requests</h1>
    <table>
        <thead>
            <tr>
                <th>Blood Group</th>
                <th>Request Type</th>
                <th>Created At</th>
                <th>Created By</th>
                <th>Quantity</th> <!-- Added Quantity field in the table header -->
                <th>Status</th>
                <th>User ID</th>
                <th>Coins</th>
                <th>Actions</th> <!-- Added Actions column header -->
            </tr>
        </thead>
        <tbody>
           <%
           List<BloodBankDTO> allBloodRequests = (List<BloodBankDTO>) request.getAttribute("bloodRequests");
           int totalBloodStock = 0;
           for (BloodBankDTO bloodRequest : allBloodRequests) {
               if (bloodRequest.getRequestType().equals("donation")) {
                   totalBloodStock += bloodRequest.getQuantity();
               }
           %>
           <tr>
               <td><%= bloodRequest.getBloodGroup() %></td>
               <td><%= bloodRequest.getRequestType() %></td>
               <td><%= bloodRequest.getCreatedAt() %></td>
               <td><%= bloodRequest.getCreatedBy() %></td>
               <td><%= bloodRequest.getQuantity() %></td> <!-- Display quantity in the data row -->
               <td><%= bloodRequest.getStatus() %></td>
               <td><%= bloodRequest.getUserId() %></td>
               <td><%= bloodRequest.getCoins() %></td>
               <td class="action-buttons">
                   <% if (bloodRequest.getStatus().equals("pending")) { %>
                       <form action="rejectBloodRequest" method="get">
                           <input type="hidden" name="requestId" value="<%= bloodRequest.getId() %>">
                           <input type="hidden" name="coins" value="<%= bloodRequest.getCoins() %>">
                           <button type="submit" class="reject">Reject</button>
                       </form>
                       <form action="approveBloodRequest" method="get">
                           <input type="hidden" name="requestId" value="<%= bloodRequest.getId() %>">
                           <input type="hidden" name="coins" value="<%= bloodRequest.getCoins() %>">
                           <button type="submit" class="approve">Approve</button>
                       </form>
                   <% } else { %>
                       <!-- Display status without form -->
                       <%= bloodRequest.getStatus() %>
                   <% } %>
               </td>
           </tr>
           <% } %>
     <tr>
                <td colspan="3">Total Blood Stock</td>
                <td><%= totalBloodStock %></td>
                <td colspan="5"></td> <!-- Colspan should match the number of columns minus 1 -->
            </tr>
        </tbody>
    </table>

    <button><a href="/BackToAdminDashBoard">Back</a></button>
</body>
</html>
