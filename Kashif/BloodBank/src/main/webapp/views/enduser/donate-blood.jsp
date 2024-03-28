<jsp:include page="header.jsp"/>
<div class="main">
    <aside class="sidebar">
        <ul>
            <li><a href="/dashboard">Dashboard</a></li>
            <li><a class="active" href="/dashboard/donate-blood">Donate Blood</a></li>
            <li><a href="/dashboard/receive-blood">Receive Blood</a></li>
            <li><a href="/dashboard/status">Status</a></li>
        </ul>
    </aside>
    
    <div class="main-content">
        <h3 style="color:red; text-align:center"> ${errorMsg} </h3>
        <div class="donation-form">
            <h2>Donate Blood</h2>
            <form action="/dashboard/donate-blood" method="POST">
                <div class="form-group">
                    <label for="bloodGroup">Blood Group:</label>
                    <input disabled id="bloodGroup" name="bloodGroup" type="text" value=${data.bloodGroup}>
                    <input id="type" name="type" type="hidden" value="donor">
                </div>
                <div class="form-group">
                    <label for="quantity">Quantity :</label>
                    <input id="quantity" name="quantity" placeholder="Enter quantity" required type="number">
                </div>
                <button type="submit">Donate</button>
            </form>
        </div>
        <h3 style="color :green; text-align:center">${success} </h3>
    </div>
   
</div>

<jsp:include page="footer.jsp"/>