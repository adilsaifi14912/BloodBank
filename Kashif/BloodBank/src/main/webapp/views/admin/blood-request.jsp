<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main">
    <aside class="sidebar">
        <ul>
            <li><a href="/dashboard">Dashboard</a></li>
            <li><a href="/dashboard/agent-account-creation">Register Agent</a></li>
            <li><a href="/dashboard/alluser-lists">User Lists</a></li>
            <li><a class="active" href="/dashboard/blood-request">Requests</a></li>
            <li><a href="/dashboard/blood-report">Blood Report</a></li>
            <li><a href="/dashboard/available-stock">Available Stock</a></li>
            <li><a href="/dashboard/coins-report">Coins Report</a></li>
        
        </ul>
    </aside>
    <!-- Overlay to blur the background -->
    <div class="overlay" id="overlay"></div>
    <main class="content">
        <div class="user-list">
            <h2 style="text-align:center">User Requested List</h2>
            <h3 style="text-align:center; color:red">${errorMsg}</h3>
            <h3 style="color:green; text-align:center"> ${success} </h3>
            <div class="action-buttons">
                
                <form action="/dashboard/blood-request" method="post">
                    <!-- Filtering options -->
                    <select id="filterOption" name="filterOption">
                        <option value="select">Select Options</option>
                        <option value="byAgent">By Agent</option>
                        <option value="byUsername">By Username</option>
                        <option value="createdBetween">Created Between</option>
                        <option value="byStatus">By Status</option> <!-- New option for status -->
                    </select>
                    
                    <!-- Username input -->
                    <input id="usernameInput" name="username" placeholder="Enter Username" style="display: none;"
                           type="text">
                    
                    <!-- Agent input -->
                    <input id="agentInput" name="agent" placeholder="Enter Agent" style="display: none;" type="text">
                    
                    <!-- Start date input -->
                    <input id="startDate" name="startDate" placeholder="Start Date" style="display: none;" type="date">
                    
                    <!-- End date input -->
                    <input id="endDate" name="endDate" placeholder="End Date" style="display: none;" type="date">
                    
                    <!-- Status select -->
                    <select id="statusSelect" name="status" style="display: none;">
                        <option value="select">Select Status</option>
                        <option value="accepted">Accepted</option>
                        <option value="rejected">Rejected</option>
                        <option value="pending">Pending</option>
                    </select>
                    
                    <button type="submit">Filter</button>
                </form>
                <table class="user-list">
                    <thead>
                        <tr>
                            <th>Serial No.</th>
                            <th>Username</th>
                            <th>Agent</th>
                            <th>Blood Group</th>
                            <th>Quantity</th>
                            <th>Coins</th>
                            <th>Type</th>
                            <th>Requested At</th>
                            <th>DOB</th>
                            <th>Age</th>
                            <th>Request ID</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${bloodStocks}" var="bloodStock" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${bloodStock.username}</td>
                                <td>${bloodStock.agent}</td>
                                <td>${bloodStock.bloodGroup}</td>
                                <td>${bloodStock.quantity}</td>
                                <td>${bloodStock.coins}</td>
                                <td>${bloodStock.type.toUpperCase()}</td>
                                <td>${bloodStock.createdAt}</td>
                                <td>${bloodStock.dob}</td>
                                <td>${bloodStock.age}</td>
                                <td>${bloodStock.id}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${bloodStock.status eq 'pending'}">
                                            <div class="button-group">
                                                <form action="/dashboard/blood-request" method="POST">
                                                    <input name="bloodStatus" type="hidden" value="accepted">
                                                    <input name="quantity" type="hidden" value="${bloodStock.quantity}">
                                                    <input name="bloodGroup" type="hidden"
                                                           value="${bloodStock.bloodGroup}">
                                                    <input name="id" type="hidden" value="${bloodStock.id}">
                                                    <input name="type" type="hidden" value="${bloodStock.type}">
                                                    <button class="btn accept-btn" type="submit">Accept</button>
                                                </form>
                                                <button class="btn reject-btn" onclick="togglePopup('${bloodStock.id}')"
                                                        type="button">Reject
                                                </button>
                                            </div>
                                        </c:when>
                                        <c:when test="${bloodStock.status eq 'rejected'}">
                                            <span class="btn rejected-btn">Rejected</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="btn accepted-btn">Accepted</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
                <!-- Popup message form -->
                <div class="popup" id="messagePopup">
                    <form action="/dashboard/blood-request" method="POST">
                        <h2>Remark</h2>
                        <label for="remark">Reason for rejection:</label><br>
                        <textarea id="remark" name="remark" placeholder="Enter your reason here..." required></textarea><br>
                        <h5>
                            <c:out value="${reqId}"/>
                        </h5>
                        <input name="bloodStatus" type="hidden" value="rejected">
                        <input id="reqId" name="id" type="hidden">
                        <div class="button-container">
                            <button class="btn cancel-btn" onclick="togglePopup()" type="button">Cancel</button>
                            <input class="btn submit-btn" type="submit" value="Submit">
                        </div>
                    </form>
                </div>
            </div>
    </main>
</div>

<jsp:include page="footer.jsp"/>
