<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <title>Customer Dashboard</title>
        <style>
            /* Reset styles */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
    
            body {
                font-family: Arial, sans-serif;
                line-height: 1.6;
                background-color: #f7f7f7;
                color: #333;
            }
    
            .container {
                display: flex;
                flex-direction: column;
                min-height: 100vh;
            }
    
            /* Header styles */
            header {
                background-color: #333;
                color: #fff;
                padding: 10px;
            }
    
            .navbar {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
    
            .logo a {
                color: #fff;
                text-decoration: none;
                font-size: 24px;
                font-weight: bold;
            }
    
            .user-info {
                display: flex;
                align-items: center;
            }
    
            .user-info span {
                margin-right: 10px;
            }
    
            .logout {
                color: #fff;
                text-decoration: none;
                padding: 5px 10px;
                background-color: #ff6347;
                border-radius: 4px;
            }
    
            /* Main content styles */
            .main {
                display: flex;
                flex: 1;
            }
    
            .sidebar {
                background-color: #333;
                color: #fff;
                padding: 20px;
                width: 200px;
            }
    
            .sidebar ul {
                list-style-type: none;
                padding: 0;
            }
    
            .sidebar li {
                margin-bottom: 10px;
            }
    
            .sidebar a {
                color: #fff;
                text-decoration: none;
                padding: 10px;
                display: block;
            }
    
            .sidebar a:hover,
            .sidebar a.active {
                border-radius: 10px;
                background-color: #555;
            }
    
            .sidebar a.active {
                background-color: #d32f2f;
            }
    
            .main-content {
                flex: 1;
                padding: 65px;
            }
    
            .dashboard-container, {
                max-width: 800px;
                margin: 0 auto;
                background-color: #fff;
                padding: 30px;
                border-radius: 5px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            }
    
            h2 {
                text-align: center;
                color: #d32f2f;
                margin-bottom: 30px;
            }
    
            .info-section {
                margin-bottom: 30px;
            }
    
            .info-section h3 {
                margin-bottom: 10px;
            }
    
            .info-section p {
                margin-bottom: 5px;
            }
    
            /* My CSS */
            .signup-container {
                max-width: 700px;
                margin: 0 auto;
                padding: 25px;
                background-color: #f9f9f9;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
    
            .signup-container h2 {
                text-align: center;
                margin-bottom: 20px;
            }
    
            .signup-container h4 {
                text-align: center;
                margin-bottom: 10px;
            }
    
            form {
                display: flex;
                flex-direction: column;
            }
    
            .form-group {
                display: flex;
                justify-content: space-between;
                margin-bottom: 15px;
            }
    
            .form-group input,
            .form-group select {
                flex: 1;
                padding: 10px;
                margin-right: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
    
            .form-group input[type="date"] {
                width: calc(50% - 5px); /* Adjusting for the date input */
            }
    
            button[type="submit"] {
                padding: 10px;
                background-color: #9B4444;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
    
            button[type="submit"]:hover {
                background-color: #d32f2f;
            }
    /* CSS for filter & sorting */
                .action-buttons {
                    margin-bottom: 20px;
                    margin-left: 50px;
                }
    
                .action-buttons form {
                    display: inline-block;
                    margin-right: 20px;
                }
    
                .action-buttons select {
                    padding: 8px;
                    border: 1px solid #ccc;
                    border-radius: 4px;
                }
    
                .action-buttons input[type="text"] {
                    padding: 8px;
                    width: 150px;
                    border: 1px solid #ccc;
                    border-radius: 4px;
                    margin-right: 5px;
                }
    
                .action-buttons button {
                    padding: 8px 15px;
                    background-color: #4CAF50;
                    color: white;
                    border: none;
                    border-radius: 4px;
                    cursor: pointer;
                }
    
                .action-buttons button:hover {
                    background-color: #45a049;
                }
                h1 {
                      text-align: center;
                      margin-bottom: 5px;
                      color: #2c3e50;
                  }
                 
               
                  table {
                      width: 100%;
                      border-collapse: collapse;
                  }
                  th, td {
                      padding: 10px;
                      text-align: left;
                      border-bottom: 2px solid #bdc3c7;
                      border-right: 0.1px solid #bdc3c7;
                  }
                  th {
                      background-color: #2c3e50;
                      color: #ecf0f1;
                      font-weight: bold;
                      text-transform: uppercase;
                  }
                  td {
                      background-color: #ecf0f1;
                  }
    
                  .agent-form{
                    width:50%;
                    margin: auto;
                    height: 70%
                  }
                  .enduser-list{
                    width:90%;
                    margin:auto;
                  }
                  form {
                          display: flex;
                          flex-direction: column;
                      }
                      .form-row {
                          display: flex;
                          justify-content: space-between;
                          margin-bottom: 15px;
                      }
                      .form-row label,
                      .form-row input,
                      .form-row textarea {
                          flex: 1;
                      }
                      label {
                          margin-bottom: 8px;
                          font-weight: bold;
                      }
                      input[type="text"],
                      input[type="email"],
                      input[type="date"],
                      input[type="number"],
                      textarea {
                          padding: 10px;
                          border: 1px solid #ccc;
                          border-radius: 5px;
                          font-size: 16px;
                      }
                      input[type="submit"] {
                          background-color: #007bff;
                          color: #fff;
                          border: none;
                          padding: 12px 20px;
                          border-radius: 5px;
                          font-size: 16px;
                          cursor: pointer;
                      }
                      input[type="submit"]:hover {
                          background-color: #0056b3;
                      }
                      /* CSS for Table Status */
                      
                
                      /* endUser-status design popup */
                /* Styling for the popup */
                .enduser-status-popup {
                  display: none;
                  position: fixed;
                  top: 50%;
                  left: 50%;
                  transform: translate(-50%, -50%);
                  background-color: #ffffff;
                  padding: 20px;
                  border: 2px solid #3498db;
                  border-radius: 10px;
                  box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.75);
                  z-index: 9999;
                  animation: fadeIn 0.5s ease-in-out;
                  max-width: 400px;
                  width: 80%;
                  text-align: center;
                }
              
                /* Styling for the info icon */
                .info-icon.enduser-status {
                    padding-bottom: 12px;
                    cursor: pointer;
                    color: black;
                    font-size: 12px; /* Adjust the size of the icon */
                    display: inline-block;
                    width: 20px; /* Set the width and height of the circular area */
                    height: 20px;
                    line-height: 18px;
                    text-align: center;
                    border-radius: 50%; /* Create a circular shape */
                    border: 2px solid black; /* Border color */
                  }
              
                /* Styling for the heading */
                .popup-heading {
                  font-size: 20px;
                  color: #333333;
                  margin-bottom: 15px;
                }
              
                /* Styling for the reason text */
                .popup-text {
                  font-size: 16px;
                  color: #666666;
                  margin-bottom: 20px;
                }
              
                /* Styling for the close button */
                .popup-close-button {
                  background-color: #3498db;
                  color: #ffffff;
                  border: none;
                  padding: 10px 20px;
                  border-radius: 5px;
                  cursor: pointer;
                  font-size: 16px;
                  transition: background-color 0.3s ease;
                }
              
                .popup-close-button:hover {
                  background-color: #2980b9;
                }
              
                /* Keyframes for fadeIn animation */
                @keyframes fadeIn {
                  from { opacity: 0; }
                  to { opacity: 1; }
                }
                
               /* Button styling */
               .btn {
                   padding: 10px 10px;
                   font-size: 14px;
                   font-weight: bold;
                   text-decoration: none;
                   text-align: center;
                   border-radius: 4px;
                   transition: background-color 0.3s ease;
                   border: 1px solid transparent; /* Add border */
               }
    
               .accept-btn {
                   background-color: #28a745; /* Green color for accept */
                   color: #fff;
               }
    
               .reject-btn {
                   background-color: #dc3545; /* Red color for reject */
                   color: #fff;
               }
    
               .rejected-btn {
                   background-color: #6c757d; /* Gray color for rejected */
                   color: #fff;
               }
    
               .accepted-btn {
                   background-color: #007bff; /* Blue color for accepted */
                   color: #fff;
               }
    
               /* Hover effect */
               .btn:hover {
                   opacity: 0.8;
               }
    
               /* Active effect */
               .btn:active {
                   transform: translateY(1px);
               }
    
               /* Add border color on hover and focus */
               .btn:hover,
               .btn:focus {
                   border-color: #adb5bd; /* Light gray */
               }
    
    
            /* Footer styles */
            footer {
                background-color: #333;
                color: #fff;
                padding: 10px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <header>
                <nav class="navbar">
                    <div class="logo">
                        <a href="#">Blood Bank</a>
                    </div>
                    <div class="user-info">
                        <span>Welcome, ${data.name}</span>
                        <a class="logout" href="/logout">Logout</a>
                    </div>
                </nav>
            </header>