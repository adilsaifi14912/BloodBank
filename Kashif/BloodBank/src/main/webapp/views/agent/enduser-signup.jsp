<jsp:include page="header.jsp" />
<div class="main">
    <aside class="sidebar">
        <ul>
            <li><a href="/dashboard">Dashboard</a></li>
            <li><a href="/dashboard/enduser-account-creation" class="active">Create User</a></li>
            <li><a href="/dashboard/enduser-lists">EndUsers List</a></li>

        </ul>
    </aside>
    <div class="main-content">
        <div class="dashboard-container">
            <h2>Sign Up</h2>
            <h4 style="color:red">${errorMsg}</h4>
            <form action="#" method="POST">
                <div class="form-group">
                    <input type="text" id="username" name="username" placeholder="Username" required>
                    <input type="email" id="email" name="email" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <input type="text" id="name" name="name" placeholder="Name" required>
                    <select id="bloodGroup" name="bloodGroup" required>
                        <option value="" disabled selected>Blood Group</option>
                        <option value="A+">A+</option>
                        <option value="A-">A-</option>
                        <option value="B+">B+</option>
                        <option value="B-">B-</option>
                        <option value="AB+">AB+</option>
                        <option value="AB-">AB-</option>
                        <option value="O+">O+</option>
                        <option value="O-">O-</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="date" id="dob" name="dob" required>
                    <input type="text" id="address" name="address" placeholder="Address" required>
                </div><br><br><br>
                <button type="submit">Create Account</button>
            </form>
            <h2 style="color:green"> ${success} </h2>

        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />