<!DOCTYPE html>
 <html lang="en">
 <head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>SignUp</title>
     <style>
         body {
             font-family: Arial, sans-serif;
             background-color: #f2f2f2;
             margin: 0;
             padding: 0;
             display: flex;
             justify-content: center;
             align-items: center;
             height: 100vh;
         }

         .signup-container {
             background-color: #ffffff;
             border-radius: 8px;
             padding: 20px;
             box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
             width: 350px;
         }

         h2 {
             text-align: center;
             margin-bottom: 20px;
         }

         label {
             font-weight: bold;
         }

         input[type="text"],
         input[type="password"],
         input[type="tel"],
         textarea,
         input[type="submit"] {
             width: 100%;
             padding: 10px;
             margin-bottom: 15px;
             border: 1px solid #ccc;
             border-radius: 5px;
             box-sizing: border-box;
         }

         input[type="submit"] {
             background-color: #007bff;
             color: #ffffff;
             cursor: pointer;
         }

         input[type="submit"]:hover {
             background-color: #0056b3;
         }
     </style>
 </head>
 <body>
     <div class="signup-container">
         <h2>Sign Up</h2>
         <!-- Form for submitting signup data -->
         <form action="agentSignupDetails" method="post">
             <label for="username">Username:</label><br>
             <input type="text" id="username" name="username" required><br>
             <label for="bloodgroup">BloodGroup:</label><br>
             <input type="text" id="bloodGroup" name="bloodGroup" required><br>
             <label for="dob">Date of Birth (MM/DD/YYYY):</label><br>
             <input type="date" id="dob" name="dob" required><br>
             <label for="contact_number">Contact Number:</label><br>
             <input type="tel" id="contactNumber" name="contactNumber" pattern="[0-9]*" required><br>
             <label for="address">Address:</label><br>
             <textarea id="address" name="address" rows="4" cols="50" required></textarea><br>
             <input type="submit" value="Submit">
         </form>
     </div>
 </body>
 </html>
