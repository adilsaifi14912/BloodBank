<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main">
    <aside class="sidebar">
        <ul>
            <li><a href="/dashboard">Dashboard</a></li>
            <li><a href="/dashboard/agent-account-creation">Register Agent</a></li>
            <li><a href="/dashboard/alluser-lists">User Lists</a></li>
            <li><a href="/dashboard/blood-request">Requests</a></li>
            <li><a href="/dashboard/blood-report">Blood Report</a></li>
            <li><a href="/dashboard/available-stock">Available Stock</a></li>
            <li><a class="active" href="/dashboard/coins-report">Coins Report</a></li>
        
        </ul>
    </aside>
    <main class="content">
        <p style="margin: 0;"><strong> Coins Report with Commission </strong></p>
        <div class="action-buttons">
        
        
        </div>
        <div class="enduser-list">
            <table>
                <thead class="table-header">
                    <tr>
                        <th>Serial No.</th>
                        <th>Username</th>
                        <th>Blood Group</th>
                        <th>User Coins</th>
                        <th>Agent Rate</th>
                        <th>Agent Commission</th>
                        <th>Admin Coins</th>
                    
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${agentCoinReport}" var="request" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${request.username}</td>
                            <td>${request.bloodGroup}</td>
                            <td>${request.endUserCoins}</td>
                            <td>${request.commission} %</td>
                            <td>${request.agentCoins}</td>
                            <td>${request.adminCoins}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3"><strong>Total</strong></td>
                        <td><strong>${endUserTotalCoins}</strong></td>
                        <td></td>
                        <td><strong>${agentTotalCoins}</strong></td>
                        <td><strong>${adminTotalCoins}</strong></td>
                    </tr>
                </tbody>
            </table>
        
        </div>
    
    </main>
</div>
<jsp:include page="footer.jsp"/>
