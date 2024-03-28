<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.insightgeeks.bloodbank.dto.BloodRequestSummaryDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agent Profile</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        /* CSS styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: #dc3545; /* Blood red color */
            color: #fff;
            padding: 20px;
            text-align: center;
            margin-bottom: 20px;
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

        .container {
            max-width: 600px;
            margin: 0 150px;
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
            float:left
        }

        h3 {
            text-align: center;
            color: #dc3545; /* Blood red color */
            margin-bottom: 20px;
        }

        .profile-info {
            margin-bottom: 20px;
        }

        .profile-info p {
            font-size: 16px;
            margin: 5px 0;
            color: #007bff; /* Blue color */
        }

        .profile-info p:first-child {
            margin-top: 20px;
        }

        .profile-info p:last-child {
            margin-bottom: 20px;
        }

            .btn.colour {
                background-color: white;
                border-color: white;
                text-decoration: none;
            }

            .mg{
            margin-top:20px;
            }

    </style>
</head>
<body>

<div class="header">
    <h1 class="logo">Blood Bank</h1>
    <p class="slogan">Saving Lives, One Donation at a Time</p>
</div>

<div class="container">
    <h3>Agent User Profile</h3>
    <div class="profile-info">
        <p>Username: ${user.getUsername()}</p>
        <p>ID: ${user.getId()}</p>
        <p>Date of Birth: ${user.getDateOfBirth()}</p>
        <p>Phone Number: ${user.getPhoneNumber()}</p>
        <p>Address: ${user.getAddress()}</p>
        <p>Blood Group : ${user.getDateOfBirth()}</p>
        <p>Created On : ${user.getCreatedOn()}</p>
        <p>Commission Rate : ${user.getCommision()} %</p>
    </div>
</div>
<div class="container">
    <h3>Coins Stats</h3>
    <div class="profile-info">
        <p>Total-Coins : ${totalCoins} </p>
        <p>Agent-Commision-Coins : ${totalCommission} </p>
        <p>Total-Users-coins : ${totalUserCoins} </p>
    </div>
</div>
<table border="1">
        <thead>
            <tr>
                <th>Blood Group</th>
                <th>Total Coins</th>
                <th>Commission Earned</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="summary" items="${bloodRequestSummaryList}">
                <tr>
                    <td>${summary.bloodGroup}</td>
                    <td>${summary.totalCoins}</td>
                    <td>${summary.commission}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<button type="button" class="btn btn-primary colour mg"><a href="createUser" class="btn blue pad">Create User</a></button>
<button type="button" class="btn btn-primary colour mg"><a href="myRequests">Requests</a></button>
<button type="button" class="btn btn-primary colour mg"><a href="logout" class="btn blue pad">Logout</a></button>
<button type="button" class="btn btn-primary colour mg"><a href="agentCreatedUsers"  class="btn blue pad">Signup Users</a></button>
<button type="button" class="btn btn-primary colour mg"><a href="requestsReport" class="btn blue pad">Requests Report</a></button>


</body>
</html>
