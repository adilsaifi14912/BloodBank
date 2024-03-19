 <jsp:include page="header.jsp" />
 <div class="main">
            <aside class="sidebar">
                <ul>
                    <li><a href="/dashboard" class="active">Dashboard</a></li>
                    <li><a href="/dashboard/donate-blood">Donate Blood</a></li>
                    <li><a href="/dashboard/receive-blood">Receive Blood</a></li>
                    <li><a href="/dashboard/receive-blood">Status</a></li>
                </ul>
            </aside>
            <div class="main-content">
                <div class="dashboard-container">
                    <h2>Normal User Dashboard</h2>
                    <div class="info-section">
                          <h3><ul>Profile<ul></h3>
                          <p><strong>Name:</strong> ${data.name}</p>
                          <p><strong>Username:</strong> ${data.username}</p>
                          <p><strong>Created At:</strong> ${data.creationTime}</p>
                          <p><strong>Created By:</strong> ${data.createdBy}</p>
                          <p><strong>DOB:</strong> ${data.dob}</p>
                          <p><strong>Blood Group:</strong> ${data.bloodGroup}</p>
                    </div>
                </div>
            </div>
 </div>
<jsp:include page="footer.jsp" />