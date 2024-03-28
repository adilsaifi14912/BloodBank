<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<div class="main">
    <aside class="sidebar">
        <ul>
            <li><a href="/dashboard">Dashboard</a></li>
            <li><a href="/dashboard/agent-account-creation">Register Agent</a></li>
            <li><a href="/dashboard/alluser-lists">User Lists</a></li>
            <li><a href="/dashboard/blood-request">Requests</a></li>
            <li><a class="active" href="/dashboard/blood-report">Blood Report</a></li>
            <li><a  href="/dashboard/available-stock" >Available Stock</a></li>
            <li><a href="/dashboard/coins-report" >Coins Report</a></li>
        
        </ul>
    </aside>
    
    <main class="content">
        <h1>Report Blood Group for Accepted and Rejected</h1>
        <h3 style="color:green; text-align:center"> ${operation} </h3>
        <div class="action-buttons">
        
        
        </div>
        <div class="enduser-list">
            <table>
                <thead>
                    <tr>
                        <th colspan="4" style="background:#acbca5; text-align:center; color:black"><strong>Accepted Blood Group</strong></th>
                    </tr>
                    <tr>
                        <th>Blood Group</th>
                        <th>Units</th>
                        <th>Coins</th>
                        <th>Total Request</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${reportAcceptedBG}" var="data" varStatus="loop">
                        <tr>
                            <td <c:if test="${loop.last}" >style="font-weight:bold"</c:if>>${data.bloodGroup}</td>
                            <td  <c:if test="${loop.last}" >style="font-weight:bold"</c:if>>${data.quantity}</td>
                            <td  <c:if test="${loop.last}" >style="font-weight:bold"</c:if>>${data.coins}</td>
                            <td  <c:if test="${loop.last}" >style="font-weight:bold"</c:if>>${data.requests}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            
            <table>
                <thead>
                    <tr>
                        <th colspan="4" style="background:#98746e; text-align:center; color:black"><strong>Rejected Blood Group</strong></th>
                    </tr>
                    <tr>
                        <th>Blood Group</th>
                        <th>Units</th>
                        <th>Coins</th>
                        <th>Total Request</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${reportRejectedBG}" var="data" varStatus="loop">
                        
                        <tr>
                            <td <c:if test="${loop.last}" >style="font-weight:bold"</c:if>>${data.bloodGroup}</td>
                            <td  <c:if test="${loop.last}" >style="font-weight:bold"</c:if>>${data.quantity}</td>
                            <td  <c:if test="${loop.last}" >style="font-weight:bold"</c:if>>${data.coins}</td>
                            <td  <c:if test="${loop.last}" >style="font-weight:bold"</c:if>>${data.requests}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        
        </div>
    
    </main>
</div>
<jsp:include page="footer.jsp"/>
