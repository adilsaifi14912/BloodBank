<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.insightgeeks.bloodbank.dto.SignupDTO" %>
<%@ page import="com.insightgeeks.bloodbank.dto.BloodStockDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Admin Profile</title>
<style>
    /* CSS styles */
    .header {
        background-color: #dc3545; /* Blood red color */
        color: #fff;
        padding: 20px;
        text-align: center;
    }

    .logo {
        font-size: 36px;
        font-weight: bold;
        margin: 0;
    }

    .slogan {
        font-size: 18px;
        margin-top: 5px;
    }

    body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
        margin: 0;
        padding: 0;
    }

    .container {
        max-width: 500px;
        float:left
        margin: 50px auto;
        background-color: #fff;
        border-radius: 8px;
        padding: 10px; /* Adjusted padding */
        box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
        position: relative; /* Add position relative */
        float: left;
    }

    h2 {
        text-align: center;
        color: #333;
    }

    .profile-info {
        margin-bottom: 10px; /* Adjusted margin */
    }

    .profile-info label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
    }

    .profile-info p {
        padding: 5px; /* Adjusted padding */
        font-size: 16px;
        margin: 0; /* Adjusted margin */
    }

    .btn {
        background-color: #007bff;
        color: #fff;
        padding: 8px 16px; /* Adjusted padding */
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
        margin-right: 10px; /* Adjusted margin */
        margin-bottom: 10px; /* Adjusted margin */
    }

    .btn:hover {
        background-color: #0056b3;
    }

    .edit-profile-link {
        text-align: center;
        margin-top: 20px;
    }

    .edit-profile-link a {
        color: #007bff;
        text-decoration: none;
    }

    .edit-profile-link a:hover {
        text-decoration: underline;
    }

    .btn.colour {
        background-color: white;
        border-color: white;
        text-decoration: none;
    }

    .btn.blue {
        background-color: #f5f5dc; /* Warm white */
        border-color: #f5f5dc; /* Warm white */
        color: #007bff; /* Blue text */
        text-decoration: none; /* No text decoration */
    }

    .btn.blue:hover {
        background-color: #e0e0d1; /* Lighter warm white on hover */
        border-color: #e0e0d1; /* Lighter warm white on hover */
        color: #0056b3; /* Darker blue text on hover */
    }

    .pad {
        padding: 8px;
    }
</style>

</head>
<body>

<div class="header">
    <h1 class="logo">Blood Bank</h1>
    <p class="slogan">Saving Lives, One Donation at a Time</p>
</div>

<div class="container">
    <h3>Admin user has logged in</h3>
    <div class="profile-info">
        <p><strong>Username:</strong> ${user.getUsername()}</p>
        <p><strong>ID:</strong> ${user.getId()}</p>
        <p><strong>Date of Birth:</strong> ${user.getDateOfBirth()}</p>
        <p><strong>Phone Number:</strong>${user.getPhoneNumber()}</p>
        <p><strong>Address:</strong>${user.getAddress()}</p>
    </div>
</div>
        <%
        List<SignupDTO> users = (List<SignupDTO>) request.getAttribute("signedupUsers");
        if(users != null)
        {
            session.setAttribute("signedupUsers",users);
        }
        %>
<button type="button" class="btn btn-primary mt-5 ml-5 p-2"> <a href="createAgent"  class="btn blue"> Create Agent </a></button>
<button type="button" class="btn btn-primary mt-5"><a href="logout"  class="btn blue pad">Logout</a></button>
<button type="button" class="btn btn-primary mt-5"><a href="signupUsers"  class="btn blue pad">Signup Users</a></button>
<button type="button" class="btn btn-primary mt-5"><a href="myRequests" class="btn blue pad">Show Requests</a></button>
<button type="button" class="btn btn-primary mt-5"><a href="requestsReport" class="btn blue pad">Requests Report</a></button>

<%
    List<BloodStockDTO> bloodStockList = (List<BloodStockDTO>) session.getAttribute("bloodStockList");
%>
<div class="container">
    <h3>Blood Stock Status</h3>
    <div class="profile-info">
        <c:forEach var="bloodStock" items="${bloodStockList}">
            <p><strong>Blood Group:</strong> ${bloodStock.bloodGroup}, <strong>Units:</strong> ${bloodStock.units}</p>
        </c:forEach>
    </div>
</div>
<div class="container">
    <h3>Coins Stats</h3>
    <div class="profile-info">
        <p>Total-Coins : ${totalCoins} </p>
        <p>Agent-Commision-Coins : ${totalCommission} </p>
        <p>Total-Users-coins : ${totalUserCoins} </p>
        <p>Admin-coins : ${user.getCoins()}</p>
    </div>
</div>

</body>
</html>
