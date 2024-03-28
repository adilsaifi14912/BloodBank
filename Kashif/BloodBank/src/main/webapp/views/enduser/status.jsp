<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main">
    <aside class="sidebar">
        <ul>
            <li><a href="/dashboard">Dashboard</a></li>
            <li><a href="/dashboard/donate-blood">Donate Blood</a></li>
            <li><a href="/dashboard/receive-blood">Receive Blood</a></li>
            <li><a class="active" href="/dashboard/status">Status</a></li>
        </ul>
    </aside>
    <div class="main-content">
        <div class="blood-group-info">
            <p style="margin: 0;">Blood Group:<strong> ${data.bloodGroup} </strong></p>
        </div>
        <br>
        <form action="/dashboard/status" method="post" style="display: inline-block; margin-right: 20px;">
            
            <!-- Filtering options -->
            <select id="filterOption" name="filterOption">
                <option value="select">Select Options</option>
                <option value="createdBetween">Created Between</option>
                <option value="byStatus">By Status</option> <!-- New option for status -->
            </select>
            
         
            
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
        
        <br>
        <table>
            <thead class="table-header">
                <tr>
                    <th>Serial No.</th>
                    <th>Donor/Receiver</th>
                    <th>Unit</th>
                    <th>Coin</th>
                    <th>Request Date</th>
                    <th>Request Id</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${bloodRequests}" var="request" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${request.type.toUpperCase()}</td>
                        <td>${request.quantity}</td>
                        <td>${request.coins}</td>
                        <td>${request.createdAt}</td>
                        <td>${request.id}</td>
                        <c:if test="${request.status eq 'rejected'}">
                            <td class=${request.status}>${request.status.toUpperCase()}
                                <span class="info-icon enduser-status" data-remark="${request.remark}">&#8505;</span>
                            </td>
                        
                        </c:if>
                        <c:if test="${request.status ne 'rejected'}">
                            <td class=${request.status}>${request.status.toUpperCase()}</td>
                        </c:if>
                    
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- Popup -->
    <div class="enduser-status-popup enduser-status" id="enduser-status-popup">
        <div class="popup-heading">Rejection Details</div>
        <div class="popup-text" id="popup-reason">
        </div>
        <button class="popup-close-button" onclick="closePopup()">Close</button>
    </div>
</div>

<jsp:include page="footer.jsp"/>