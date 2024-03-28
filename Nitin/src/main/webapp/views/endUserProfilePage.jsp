<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>End User Profile</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        /* CSS styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            color: #333;
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
            margin-left:90px;
            float:left;
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
        }

        h3 {
            text-align: center;
            color: #dc3545; /* Blood red color */
            margin-bottom: 20px;
        }

        .profile-info {
            margin-bottom: 20px;
        }

        .profile-info label {
            font-weight: bold;
            color: #007bff; /* Blue color */
            text-transform: uppercase;
            font-size: 16px;
            display: inline-block;
            width: 150px;
        }

        .profile-info p {
            font-size: 16px;
            display: inline-block;
            margin: 0;
        }

        .btn {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            display: block;
            margin: 20px auto;
            text-align: center;
            max-width: 200px;
            text-decoration: none;
        }

        .btn:hover {
            background-color: #0056b3;
        }

               .btn.btn-danger {
                   background-color: #dc3545; /* Blood red color */
                   color: #fff;
                   padding: 15px 25px; /* Increased padding */
                   border: none;
                   border-radius: 8px;
                   cursor: pointer;
                   font-size: 18px; /* Increased font size */
                   display: inline-block;
                   margin-right: 10px; /* Added margin between buttons */
                   margin-left:150px;
               }
               a{
                color:white;
               }

               .logout{
               margin-left:20px;
               padding:5px
               }
    </style>
</head>
<body>

<div class="header">
    <h1 class="logo">Blood Bank</h1>
    <p class="slogan">Saving Lives, One Donation at a Time</p>
</div>

<div class="container">
    <h3>End User Profile</h3>
    <div class="profile-info">
        <label>Username:</label>
        <p>${user.getUsername()}</p>
    </div>
    <div class="profile-info">
        <label>ID:</label>
        <p>${user.getId()}</p>
    </div>
    <div class="profile-info">
        <label>Date of Birth:</label>
        <p>${user.getDateOfBirth()}</p>
    </div>
    <div class="profile-info">
        <label>Phone Number:</label>
        <p>${user.getPhoneNumber()}</p>
    </div>
    <div class="profile-info">
        <label>Address:</label>
        <p>${user.getAddress()}</p>
    </div>
    <div class="profile-info">
         <label>BloodGroup</label>
         <p>${user.getBloodGroup()}</p>
    </div>
     <div class="profile-info">
          <label>Address:</label>
          <p>${user.getCreatedOn()}</p>
     </div>
       <div class="profile-info">
               <label>Coins:</label>
               <p>${user.getCoins()}</p>
       </div>

</div>

<button type="button" class="btn btn-danger ml-10"><a href="bloodRequirement">Donate/ReceiveBlood</a></button>
<button type="button" class="btn btn-danger ml-10"><a href="myRequests">My Requests</a></button>
<button type="button" class="btn btn-danger ml-10 logout"><a href="logout">Logout</a></button>


</body>
</html>
