<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main">
    <aside class="sidebar">
        <ul>
            <li><a href="/dashboard">Dashboard</a></li>
            <li><a href="/dashboard/enduser-account-creation">Create User</a></li>
            <li><a href="/dashboard/enduser-lists">EndUsers List</a></li>
            <li><a href="/dashboard/status">Status</a></li>
            <li><a href="/dashboard/blood-report">Blood Report</a></li>
            <li><a href="/dashboard/coins-value">Coins Value</a></li>
            <li><a class="active" href="/dashboard/coins-report">Coins Report</a></li>
        
        </ul>
    </aside>
    <div class="main-content">
        <div class="blood-group-info">
            <p style="margin: 0;"><strong> Coins Report with Commission </strong></p>
        </div>
        
        <table>
            <thead class="table-header">
                <tr>
                    <th>Serial No.</th>
                    <th>Username</th>
                    <th>Blood Group</th>
                    <th>User Coins</th>
                    <th>Commission</th>
                    <th>Agent Coins</th>
                
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${agentCoinReport}" var="request" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${request.username}</td>
                        <td>${request.bloodGroup}</td>
                        <td>${request.endUserCoins}</td>
                        <td>${request.commission}%</td>
                        <td>${request.agentCoins}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="3"><strong>Total</strong></td>
                    <td><strong>${endUserTotalCoins}</strong></td>
                    <td></td>
                    <td><strong>${agentTotalCoins}</strong></td>
                </tr>
            </tbody>
        </table>
    
    </div>
    
    </main>
</div>
<jsp:include page="footer.jsp"/>
