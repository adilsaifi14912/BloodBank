<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">

    <style>
         body {
                   font-family: Arial, sans-serif;
                   background-color: #f4f4f4;
                   margin: 0;
                   padding: 0;
               }

               .container {
                   max-width: 800px;
                   margin: 50px auto;
                   background: #fff;
                   padding: 20px;
                   border-radius: 8px;
                   box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
               }

               h2 {
                   text-align: center;
                   margin-bottom: 20px;
                   color: #333;
               }

               table {
                   width: 100%;
                   border-collapse: collapse;
                   margin-bottom: 20px;
               }

               th, td {
                   padding: 12px;
                   text-align: left;
                   border-bottom: 1px solid #ddd;
               }

               th {
                   background-color: #f2f2f2;
               }

               .top-buttons {
                   text-align: right;
                   margin-bottom: 20px;
               }

               .top-buttons button {
                   padding: 10px 20px;
                   background-color: #4CAF50;
                   color: white;
                   border: none;
                   border-radius: 5px;
                   cursor: pointer;
                   transition: background-color 0.3s ease;
               }

               .top-buttons button:hover {
                   background-color: #45a049;
               }

               .back-button {
                   text-align: center;
                   margin-top: 20px;
               }

               .back-button a {
                   text-decoration: none;
                   font-weight: bold;
                   color: #333;
                   padding: 10px 20px;
                   background-color: #4CAF50;
                   color: white;
                   border-radius: 5px;
                   display: inline-block;
                   transition: background-color 0.3s ease;
               }

               .back-button a:hover {
                   background-color: #45a049;
               }
            .filter-input {
                padding: 8px;
                margin-bottom: 10px;
                width: 100%;
                box-sizing: border-box;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            .sort-button {
                padding: 8px;
                margin-right: 5px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .sort-button:hover {
                background-color: #f2f2f2;
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }

            .details {
                font-size: 1.2em;
            }
    </style>
</head>
<body>
    <div class="container">
        <div class="header top-buttons">
                <div class="details">
                    <h2>${dto.role} Dashboard: Welcome ${dto.name}</h2>
                </div>
         <div class="back-button">
            <a href="createagent">Create Agent</a>
         </div>
        </div>
            <select class="form-select ,top-buttons" aria-label="Default select example">
              <option selected>Sort by</option>
              <option value="1">One</option>
              <option value="2">Two</option>
              <option value="3">Three</option>
            </select>
            <br/><br/>
           <select class="form-select" aria-label="Default select example">
             <option selected>Filter by</option>
             <option value="1">One</option>
             <option value="2">Two</option>
             <option value="3">Three</option>
           </select>

        <div class="back-button">
            <a href="logout">Log Out</a>
        </div>
    </div>


</body>
</html>
