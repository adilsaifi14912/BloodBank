<jsp:include page="header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<div class="main">
    <aside class="sidebar">
        <ul>
            <li><a href="/dashboard">Dashboard</a></li>
            <li><a href="/dashboard/agent-account-creation">Register Agent</a></li>
            <li><a href="/dashboard/alluser-lists">User Lists</a></li>
            <li><a href="/dashboard/blood-request">Requests</a></li>
            <li><a  href="/dashboard/blood-report">Blood Report</a></li>
            <li><a  href="/dashboard/available-stock" class="active">Available Stock</a></li>
            <li><a href="/dashboard/coins-report" >Coins Report</a></li>
        
        </ul>
    </aside>
    
    
    <main class="content">
        <table>
            <thead>
                <tr>
                    <th>Blood Group</th>
                    <th>Available Stock</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="stock" items="${stocks}" varStatus="loop">
                    <tr>
                        <td>${stock.bloodGroup}</td>
                        <td>${stock.unit}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td style="font-weight:bold">Total Available Stock</td>
                    <td style="font-weight:bold">${totalStock}</td>
                </tr>
            </tbody>
        </table>
    
    
    
    
    
    </main>
</div>
<jsp:include page="footer.jsp"/>
