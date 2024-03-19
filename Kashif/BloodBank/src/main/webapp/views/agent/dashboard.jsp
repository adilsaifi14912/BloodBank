 <jsp:include page="header.jsp" />
 <div class="main">
            <aside class="sidebar">
                <ul>
                    <li><a href="/dashboard" class="active">Dashboard</a></li>
                    <li><a href="/dashboard/enduser-account-creation">Create User</a></li>
                    <li><a href="/dashboard/enduser-lists">EndUsers List</a></li>

                </ul>
            </aside>
            <div class="main-content">
                <div class="dashboard-container">
                    <h2>Agent Dashboard</h2>
                     <div class="info-section">
                          <h3><ul>Profile<ul></h3>
                          <p><strong>Name:</strong> ${data.name}</p>
                          <p><strong>Username:</strong> ${data.username}</p>
                          <p><strong>Created At:</strong> ${data.creationTime}</p>
                          <p><strong>Created By:</strong> ${data.createdBy}</p>
                          <p><strong>DOB:</strong> ${data.dob}</p>
                          <p><strong>Blood Group:</strong> ${data.bloodGroup}</p>
                    </div>
                   <h2 style="color:green"> ${success} </h2>

                </div>
            </div>
        </div>
<jsp:include page="footer.jsp" />