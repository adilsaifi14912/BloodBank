<!DOCTYPE html>
<html>
    <head>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f7f7f7;
                color: #333;
            }
    
            .navbar {
                background-color: #d32f2f;
                padding: 10px 0;
            }
    
            .navbar-brand {
                color: #fff;
                font-size: 24px;
                font-weight: bold;
                margin-left: 20px;
                text-decoration: none;
            }
    
            .container {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                max-width: 800px;
                margin: 50px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            }
    
            h1 {
                color: #d32f2f;
                margin-bottom: 20px;
                font-size: 32px;
            }
    
            p {
                margin-bottom: 30px;
                font-size: 18px;
            }
    
            .button-container {
                display: flex;
                justify-content: center;
                gap: 20px;
            }
    
            .button {
                padding: 12px 30px;
                font-size: 18px;
                cursor: pointer;
                text-align: center;
                text-decoration: none;
                color: #fff;
                background-color: #d32f2f;
                border: none;
                border-radius: 25px;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
                transition: all 0.3s ease 0s;
            }
    
            .button:hover {
                background-color: #c62828;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.4);
            }
    
            .button:active {
                transform: scale(0.95);
            }
    
            a {
                text-decoration: none;
                color: #fff;
            }
    
            .footer {
                margin-top: 40px;
                font-size: 14px;
                color: #777;
            }
        </style>
    </head>
    <body>
        
        <div class="navbar">
            <a href="#" class="navbar-brand">Blood Donation Organization</a>
        </div>
        
        <div class="container">
            <h1>Welcome to Our Blood Donation Website</h1>
            <p>Join us in our mission to save lives. Donate blood or find a donor today.</p>
            <div class="button-container">
                <a href="/signup" class="button">Sign Up</a>
                <a href="/login" class="button">Login</a>
            </div>
            <div class="footer">
                <p>&copy; 2024 Blood Donation Organization. All rights reserved.</p>
            </div>
        </div>
    
    </body>
</html>
