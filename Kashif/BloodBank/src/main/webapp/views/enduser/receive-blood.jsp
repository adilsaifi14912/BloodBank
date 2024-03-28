<jsp:include page="header.jsp" />
 <div class="main">
            <aside class="sidebar">
                <ul>
                    <li><a href="/dashboard">Dashboard</a></li>
                    <li><a href="/dashboard/donate-blood">Donate Blood</a></li>
                    <li><a href="/dashboard/receive-blood" class="active">Receive Blood</a></li>
                    <li><a href="/dashboard/status">Status</a></li>
                </ul>
            </aside>
            <div class="main-content">
                <div class="donation-form">
                    <h2>Receive Blood</h2>
                    <h3 style="color:red; text-align:center" > ${errorMsg}
                    <form action="/dashboard/receive-blood" method="POST">
                        <div class="form-group">
                            <label for="bloodGroup">Blood Group:</label>
                            <input type="text" id="bloodGroup" name="bloodGroup" value=${data.bloodGroup} disabled>
                            <input type="hidden" id="type" name="type" value="receiver" >

                        </div>
                        <div class="form-group">
                            <label for="quantity">Quantity (in ml):</label>
                            <input type="number" id="quantity" name="quantity" placeholder="Enter quantity" required>
                        </div>
                        <button type="submit">Request for Blood</button>
                    </form>
                    <h2 style="color :green">${success}
                </div>
            </div>
 </div>
<jsp:include page="footer.jsp" />