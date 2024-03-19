 <jsp:include page="header.jsp" />
 <div class="main">
            <aside class="sidebar">
                <ul>
                    <li><a href="/dashboard">Dashboard</a></li>
                    <li><a href="/dashboard/donate-blood">Donate Blood</a></li>
                    <li><a href="/dashboard/receive-blood">Receive Blood</a></li>
                    <li><a href="/dashboard/status" class="active">Status</a></li>
                </ul>
            </aside>
            <div class="main-content">
                <div class="blood-group-info">
                    <p style="margin: 0;">Blood Group: A+</p>
                </div>

                <table>
                  <thead class="table-header">
                    <tr>
                        <th>Serial No.</th>
                        <th>Request Date</th>
                        <th>Quantity</th>
                        <th>Donor/Receiver</th>
                        <th>Status</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>1</td>
                      <td>2024-03-18</td>
                      <td>20ml</td>
                      <td>Donor</td>
                      <td class="accepted">Accepted</td>
                    </tr>
                    <tr>
                      <td>2</td>
                      <td>2024-03-17</td>
                      <td>20ml</td>
                      <td>Receiver</td>
                      <td class="rejected">Rejected</td>
                    </tr>
                    <tr>
                      <td>3</td>
                      <td>2024-03-16</td>
                      <td>500ml</td>
                      <td>Donor</td>
                      <td class="pending">Pending</td>
                    </tr>
                    <!-- Add more rows as needed -->
                  </tbody>
                </table>
                </div>
            </div>
 </div>
<jsp:include page="footer.jsp" />