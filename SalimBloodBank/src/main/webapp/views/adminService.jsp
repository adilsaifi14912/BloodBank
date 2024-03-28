<%@ page import="java.util.List" %>
<%@ page import="in.sp.main.dto.RegisterDTO" %>

<% List<RegisterDTO> allUsers = (List<RegisterDTO>) request.getAttribute("signedupUsers");
    for (RegisterDTO endUser : allUsers) {
%>
    <tr>
        <td><%= endUser.getUsername() %></td>
        <td><%= endUser.getDateOfBirth() %></td>
        <td><%= endUser.getPhoneNumber() %></td>
        <td><%= endUser.getCity() %></td>
        <td><%= endUser.getBloodGroup() %></td>
    </tr>
<% } %>
