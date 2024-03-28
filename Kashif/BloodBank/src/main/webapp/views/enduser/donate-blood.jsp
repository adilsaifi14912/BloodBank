 <jsp:include page="header.jsp" />
 <div class="main">
            <aside class="sidebar">
                <ul>
                    <li><a href="/dashboard">Dashboard</a></li>
                    <li><a href="/dashboard/donate-blood" class="active">Donate Blood</a></li>
                    <li><a href="/dashboard/receive-blood">Receive Blood</a></li>
                    <li><a href="/dashboard/status">Status</a></li>
                </ul>
            </aside>
            <div class="main-content">
                <div class="donation-form">
                    <h2>Donate Blood</h2>
                    <h3 style="color:red; text-align:center" > ${errorMsg}
                    <form action="/dashboard/donate-blood" method="POST">
                        <div class="form-group">
                            <label for="bloodGroup">Blood Group:</label>
                            <input type="text" id="bloodGroup" name="bloodGroup" value=${data.bloodGroup} disabled>
                            <input type="hidden" id="type" name="type" value="donor" >
                        </div>
                        <div class="form-group">
                            <label for="quantity">Quantity (in ml):</label>
                            <input type="number" id="quantity" name="quantity" placeholder="Enter quantity" required>
                        </div>
                        <button type="submit">Donate</button>
                    </form>
                    <h2 style="color :green">${success}
                </div>
            </div>
 </div>
<jsp:include page="footer.jsp" />