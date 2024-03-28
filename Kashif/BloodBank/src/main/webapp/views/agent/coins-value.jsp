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
            <li><a href="/dashboard/blood-report">Blood Report</a></li>
            <li><a class="active" href="/dashboard/coins-value">Coins Value</a></li>
            <li><a href="/dashboard/coins-report" >Coins Report</a></li>
        
        </ul>
    </aside>
    <div style="margin:20px auto">
        <main class="content">
            
            <div style="display: inline-block; margin-right: 20px;">
                <table style="border-collapse: collapse; width: 300px;">
                    <thead>
                        <tr>
                            <th colspan="3" style="background-color: #acbca5; text-align: center; color: black;"><strong>Donor</strong></th>
                        </tr>
                        <tr>
                            <th>Blood Group</th>
                            <th>Unit</th>
                            <th>Coins</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="entry" items="${coinPrice}">
                            <tr>
                                <td>${entry.key}</td>
                                <td>1</td>
                                <td>${entry.value}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <div style="display: inline-block;">
                <table style="border-collapse: collapse; width: 300px;">
                    <thead>
                        <tr>
                            <th colspan="3" style="background-color: #acbca5; text-align: center; color: black;"><strong>Receiver</strong></th>
                        </tr>
                        <tr>
                            <th>Blood Group</th>
                            <th>Unit</th>
                            <th>Coins</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="entry" items="${coinPrice}">
                            <tr>
                                <td>${entry.key}</td>
                                <td>1</td>
                                <td>${entry.value + 10}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>
    </div>

</div>
<jsp:include page="footer.jsp"/>
