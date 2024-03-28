<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="in.sp.main.dto.BloodBankDTO" %>
<html>
<head>
    <title>Admin Dashboard - Blood Stock</title>
    <style>
        table {
            width: 50%;
            border-collapse: collapse;
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
    </style>
</head>
<body>
    <h1>Admin Dashboard - Blood Stock</h1>
    <table>
        <thead>
            <tr>
                <th>Blood Group</th>
                <th>Quantity</th>
                <th>Last update </th>
            </tr>
        </thead>
        <tbody>
            <%
                List<BloodBankDTO> allBloodRequests = (List<BloodBankDTO>) request.getAttribute("bloodRequests");
                Map<String, Integer> bloodStockMap = new HashMap<>();
                for (BloodBankDTO bloodRequest : allBloodRequests) {
                    if (bloodRequest.getRequestType().equals("donation")) {
                        String bloodGroup = bloodRequest.getBloodGroup();
                        int quantity = (int) bloodRequest.getQuantity(); // Cast to int
                        bloodStockMap.put(bloodGroup, bloodStockMap.getOrDefault(bloodGroup, 0) + quantity);
                    }
                }

                // Iterate over blood stock map to display each blood group and its quantity
                for (Map.Entry<String, Integer> entry : bloodStockMap.entrySet()) {
            %>
                <tr>
                    <td><%= entry.getKey() %></td>
                    <td><%= entry.getValue() %></td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <button><a href="/BackToAdminDashboard">Back</a></button>
</body>
</html>
