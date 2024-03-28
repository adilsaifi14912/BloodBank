 <jsp:include page="header.jsp" />
 <div class="main">
            <aside class="sidebar">
                <ul>
                    <li><a href="/dashboard" class="active">Dashboard</a></li>
                    <li><a href="/dashboard/enduser-account-creation">Create User</a></li>
                    <li><a href="/dashboard/enduser-lists">EndUsers List</a></li>
                    <li><a href="/dashboard/status" >Status</a></li>
                    <li><a href="/dashboard/blood-report">Blood Report</a></li>
                    <li><a href="/dashboard/coins-value">Coins Value</a></li>
                    <li><a href="/dashboard/coins-report" >Coins Report</a></li>
                </ul>
            </aside>
            <div class="main-content">
                <div class="dashboard-container">
                    <h2>Agent Dashboard</h2>
                     <div class="info-section">
                          <h3><ul>Profile<ul></h3>
                          <p><strong>Name:</strong> ${data.name}</p>
                          <p><strong>Username:</strong> ${data.username}</p>
                          <p><strong>Commission:</strong> ${data.commission} %</p>
                          <p><strong>Created At:</strong> ${data.creationTime}</p>
                          <p><strong>Created By:</strong> ${data.createdBy}</p>
                          <p><strong>DOB:</strong> ${data.dob}</p>
                          <p><strong>Blood Group:</strong> ${data.bloodGroup}</p>
                         <br>
                         <h2 style="text-align: center"> Total Coins:  ${agentTotalCoins}</h2>
                     
                     </div>
                   
                   
                </div>
            </div>
        </div>
<jsp:include page="footer.jsp" />