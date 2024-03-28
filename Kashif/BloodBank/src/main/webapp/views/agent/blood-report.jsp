<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<div class="main">
    <aside class="sidebar">
        <ul>
            <li><a href="/dashboard">Dashboard</a></li>
            <li><a href="/dashboard/enduser-account-creation">Create User</a></li>
            <li><a href="/dashboard/enduser-lists">EndUsers List</a></li>
            <li><a href="/dashboard/status" >Status</a></li>
            <li><a class="active" href="/dashboard/blood-report">Blood Report</a></li>
            <li><a href="/dashboard/coins-value">Coins Value</a></li>
            <li><a href="/dashboard/coins-report" >Coins Report</a></li>
        
        
        </ul>
    </aside>
    <div style="margin:20px auto">
        <main class="content">
            <h2>Report Blood Group for Accepted and Rejected</h2>
            <h3 style="color:green; text-align:center">${operation}</h3>
            <div class="action-buttons"></div>
            <div class="enduser-list">
                <table style="margin: 0 auto;">
                    <thead>
                        <tr>
                            <th colspan="6" style="background:#98746e; text-align:center; color:black"><strong>Accepted Blood Group</strong></th>
                        </tr>
                        <tr>
                            <th>Blood Group</th>
                            <th>Total Request</th>
                            <th>Units</th>
                            <th>User Coins</th>
                            <th>Commission</th>
                            <th>Agent Coins</th>
                           
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${reportAcceptedBG}" var="data" varStatus="loop">
                            <tr>
                                <td<c:if test="${loop.last}" > style="font-weight:bold"</c:if>>${data.bloodGroup}</td>
                                <td<c:if test="${loop.last}" > style="font-weight:bold"</c:if>>${data.requests}</td>
                                <td<c:if test="${loop.last}" > style="font-weight:bold"</c:if>>${data.quantity}</td>
                                <td<c:if test="${loop.last}" > style="font-weight:bold"</c:if>>${data.coins}</td>
                                <td<c:if test="${loop.last}" > style="font-weight:bold"</c:if>>${data.commission}</td>
                                <td<c:if test="${loop.last}" > style="font-weight:bold"</c:if>>${data.agentCoins}</td>
                                
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <br>
                <table style="margin: 0 auto;">
                    <thead>
                        <tr>
                            <th colspan="6" style="background:#98746e; text-align:center; color:black"><strong>Rejected Blood Group</strong></th>
                        </tr>
                        <tr>
                            <th>Blood Group</th>
                            <th>Total Request</th>
                            <th>Units</th>
                            <th>User Coins</th>
                            <th>Commission</th>
                            <th>Agent Coins</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${reportRejectedBG}" var="data" varStatus="loop">
                            <tr>
                                <td<c:if test="${loop.last}" > style="font-weight:bold"</c:if>>${data.bloodGroup}</td>
                                <td<c:if test="${loop.last}" > style="font-weight:bold"</c:if>>${data.requests}</td>
                                <td<c:if test="${loop.last}" > style="font-weight:bold"</c:if>>${data.quantity}</td>
                                <td<c:if test="${loop.last}" > style="font-weight:bold"</c:if>>${data.coins}</td>
                                <td<c:if test="${loop.last}" > style="font-weight:bold"</c:if>>${data.commission}</td>
                                <td<c:if test="${loop.last}" > style="font-weight:bold"</c:if>>${data.agentCoins}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <br><br>
            </div>
        </main>
    </div>

</div>
<jsp:include page="footer.jsp"/>
