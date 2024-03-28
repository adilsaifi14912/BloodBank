<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blood Request Rejection Details</title>
        <style>
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
              cursor: pointer;
              color: #3498db;
              font-size: 24px;
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
        </style>
    </head>
    <body>
        
        <!-- Info icon -->
        <span class="info-icon enduser-status" onclick="showRejectionDetails('Your blood request has been rejected due to unavailability of requested blood type.')">&#8505;</span>
        
        <!-- Popup -->
        <div id="enduser-status-popup" class="enduser-status-popup enduser-status">
            <div class="popup-heading">Rejection Details</div>
            <div class="popup-text" id="popup-reason"></div>
            <button class="popup-close-button" onclick="closePopup()">Close</button>
        </div>
        
        <script>
            // Function to open the popup
            function openPopup() {
              document.getElementById("enduser-status-popup").style.display = "block";
            }
            
            // Function to close the popup
            function closePopup() {
              document.getElementById("enduser-status-popup").style.display = "none";
            }
            
            // Function to show rejection details
            function showRejectionDetails(reason) {
              // Set the rejection details
              document.getElementById("popup-reason").innerText = reason;
            
              // Open the popup
              openPopup();
            }
        </script>
    
    </body>
</html>
