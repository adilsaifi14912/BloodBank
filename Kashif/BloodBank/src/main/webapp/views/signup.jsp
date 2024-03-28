<!DOC TYPE html>
<html>
    <head>
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                background-color: #f0f0f0;
                font-family: Arial, sans-serif;
                background-image: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 500 500"><text x="50%" y="50%" dy=".35em" fill="rgba(0,0,0,0.1)" font-size="40" text-anchor="middle">Blood Bank</text></svg>');
                background-repeat: no-repeat;
                background-position: center;
                background-size: cover;
            }
    
            .signup-container {
                display: flex;
                flex-direction: column;
                width: 300px;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                background-color: rgba(255, 255, 255, 0.8);
            }
    
            .signup-container h2 {
                margin-bottom: 20px;
            }
    
            .signup-container input, select {
                padding: 10px;
                margin-bottom: 10px;
                border-radius: 5px;
                border: 1px solid #ccc;
            }
    
            .signup-container button {
                padding: 10px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
    
            .signup-container button:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <form action="/registration" method="post" id="signupForm">
            <div class="signup-container">
                <h2>Sign Up</h2>
                <h4 style="color:red"> ${errorMsg} </h4>
                <input type="text" id="username" name="username" placeholder="Username" required>
                <input type="email" id="email" name="email" placeholder="Email" required>
                <input type="text" id="name" name="name" placeholder="Name" required>
                
                <select id="bloodGroup" name="bloodGroup" placeholder="Blood Group">
                    <option value="A+">A+</option>
                    <option value="A-">A-</option>
                    <option value="B+">B+</option>
                    <option value="B-">B-</option>
                    <option value="AB+">AB+</option>
                    <option value="AB-">AB-</option>
                    <option value="O+">O+</option>
                    <option value="O-">O-</option>
                </select>
                <input type="password" id="password" name="password" placeholder="Password" required>
                <input type="date" id="dob" name="dob" placeholder="Date of Birth" required>
                <input type="text" placeholder="Address" name="address" placeholder="Address" required>
                <button type="submit">Sign Up</button>
            
            </div>
        </form>
    </body>
</html>
