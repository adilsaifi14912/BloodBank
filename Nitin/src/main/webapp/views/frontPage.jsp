<!DOCTYPE html>
<html>
<head>
    <title>Basic JSP Front Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
            background-image: url('https://img.freepik.com/free-vector/flat-horizontal-banner-template-world-blood-donor-day-awareness_23-2150412674.jpg?size=626&ext=jpg&ga=GA1.1.1809166333.1710993845&semt=ais');
            background-size:cover;
        }

        .heading-bar {
            background-color: #c7251a; /* Red-pink background */
            color: #fff; /* White text */
            text-align: center;
            padding: 20px 0; /* Adjust padding as needed */
        }

        .logo {
            font-size: 36px; /* Adjust font size as needed */
            margin: 0;
        }

        .slogan {
            font-size: 18px; /* Adjust font size as needed */
            margin: 0;
            margin-top: 5px; /* Adjust margin as needed */
        }

        .container-middle {
            display: flex;
            justify-content: center;
            align-items: center;
            height: calc(100vh - 100px); /* Adjust height based on your needs */
        }

        .center-wrapper {
            text-align: center; /* For older browsers that don't support flexbox */
        }

        .button-container {
            background-color:#f8f8ba; /* White background */
            border-radius: 10px; /* Rounded corners */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Drop shadow effect */
            padding: 40px; /* Adjust padding as needed */
            width: fit-content; /* Adjust width based on content */
        }

        .btn-login,
        .btn-signup {
            display: inline-block;
            width: 150px;
            padding: 15px 30px;
            margin: 10px;
            font-size: 18px; /* Adjust font size as needed */
            text-align: center;
            text-decoration: none;
            color: #fff;
            border: none;
            border-radius: 25px; /* Rounded corners */
            cursor: pointer;
        }

        .btn-login {
            background-color: #c7251a; /* Red-pink background */
        }

        .btn-signup {
            background-color: #c7251a; /* Pink background */
        }

        .btn-login:hover,
        .btn-signup:hover {
            opacity: 0.8; /* Reduce opacity on hover */
        }

        .alfa-slab one-regular {
          font-family: "Alfa Slab One", serif;
          font-weight: 400;
          font-style: normal;
        }

    </style>
</head>
<body>
    <div class="heading-bar">
        <h1 class="logo alpha-slab one-angular">Blood Bank</h1>
        <p class="slogan alpha-slab one-angular">Saving Lives, One Donation at a Time</p>
    </div>

    <div class="container-middle">
        <div class="center-wrapper">
            <div class="button-container">
                <button class="btn btn-login alpha-slab one-angular">Login</button>
                <button class="btn btn-signup alpha-slab one-angular">Sign Up</button>
            </div>
        </div>
    </div>
</body>
</html>
