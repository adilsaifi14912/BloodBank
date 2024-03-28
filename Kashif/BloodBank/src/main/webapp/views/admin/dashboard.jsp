<jsp:include page="header.jsp"/>

<div class="main">
    <aside class="sidebar">
        <ul>
            <li><a href="/dashboard" class="active">Dashboard</a></li>
            <li><a href="/dashboard/agent-account-creation">Register Agent</a></li>
            <li><a href="/dashboard/alluser-lists">User Lists</a></li>
            <li><a href="/dashboard/blood-request">Requests</a></li>
            <li><a  href="/dashboard/blood-report">Blood Report</a></li>
            <li><a  href="/dashboard/available-stock" >Available Stock</a></li>
            <li><a href="/dashboard/coins-report" >Coins Report</a></li>
        
        </ul>
    </aside>
    
    <main class="content">
        <h1>Welcome to Admin Dashboard</h1>
        <div class="admin-profile">
            <h2>Admin Profile</h2>
            <p><strong>Name:</strong> ${data.name}</p>
            <p><strong>Username:</strong> ${data.username}</p>
            <p><strong>Created At:</strong> ${data.creationTime}</p>
            <p><strong>Created By:</strong> ${data.createdBy}</p>
            <p><strong>DOB:</strong> ${data.dob}</p>
            <p><strong>Blood Group:</strong> ${data.bloodGroup}</p>
            <br>
            <h2 style="text-align: center"> Total Coins:  ${adminTotalCoins}</h2>
        
        </div>
    
    </main>
</div>
<jsp:include page="footer.jsp"/>
