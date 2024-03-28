<footer>
    <p>&copy; 2024 Blood Bank. All rights reserved @Kashif.</p>
</footer>
</div>
<script>
    document.getElementById("filterOption").addEventListener("change", function() {
        var selectedOption = this.value;
        var usernameInput = document.getElementById("usernameInput");
        var agentInput = document.getElementById("agentInput");
        var startDateInput = document.getElementById("startDate");
        var endDateInput = document.getElementById("endDate");
        var statusSelect = document.getElementById("statusSelect"); // New line to get status select input

        if (selectedOption === "byUsername") {
            usernameInput.style.display = "inline-block";
            agentInput.style.display = "none";
            startDateInput.style.display = "none";
            endDateInput.style.display = "none";
            statusSelect.style.display = "none"; // Hide status select when not selected
        } else if (selectedOption === "createdBetween") {
            startDateInput.style.display = "inline-block";
            endDateInput.style.display = "inline-block";
            agentInput.style.display = "none";
            usernameInput.style.display = "none";
            statusSelect.style.display = "none"; // Hide status select when not selected
        } else if (selectedOption === "byAgent") {
            agentInput.style.display = "inline-block";
            endDateInput.style.display = "none";
            startDateInput.style.display = "none";
            usernameInput.style.display = "none";
            statusSelect.style.display = "none"; // Hide status select when not selected
        } else if (selectedOption === "byStatus") { // New condition for status filter
            statusSelect.style.display = "inline-block";
            usernameInput.style.display = "none";
            agentInput.style.display = "none";
            startDateInput.style.display = "none";
            endDateInput.style.display = "none";
        } else {
            agentInput.style.display = "none";
            startDateInput.style.display = "none";
            endDateInput.style.display = "none";
            usernameInput.style.display = "none";
            statusSelect.style.display = "none"; // Hide status select when not selected
        }
    });
</script>

<script>
    // Function to toggle the display of the popup and overlay
    function togglePopup(id) {
        var messagePopup = document.getElementById('messagePopup');
        var overlay = document.getElementById('overlay');
      
        if (messagePopup.style.display === 'block') {
            messagePopup.style.display = 'none';
            overlay.style.display = 'none';
        } else {
            messagePopup.style.display = 'block';
            overlay.style.display = 'block';
            
            // Set the value of reqId
            var reqIdInput = document.getElementById('reqId');
            reqIdInput.value = id; // added the value here
        }
    }
    
    // Close the popup if user clicks outside of it or on the overlay
    window.addEventListener('click', function(event) {
        var messagePopup = document.getElementById('messagePopup');
        var overlay = document.getElementById('overlay');
      
        if (event.target === overlay) {
            messagePopup.style.display = 'none';
            overlay.style.display = 'none';
        }
    });
</script>


</body>
</html>