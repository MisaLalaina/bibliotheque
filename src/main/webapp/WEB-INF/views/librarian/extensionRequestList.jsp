<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.ExtensionRequest" %>
<html>
<head>
    <link rel="stylesheet" href="/style.css" />
    <title>Extension Requests</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
<h2>Extension Requests</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Loan ID</th>
        <th>Request Date</th>
        <th>State</th>
        <th>Reason</th>
        <th>Action</th>
    </tr>
    <%
        java.util.List requests = (java.util.List) request.getAttribute("requests");
        if (requests != null) {
            for (Object obj : requests) {
                ExtensionRequest req = (ExtensionRequest) obj;
    %>
    <tr>
        <td><%= req.getId() %></td>
        <td><%= req.getLoan() != null ? req.getLoan().getId() : "" %></td>
        <td><%= req.getRequestDate() %></td>
        <td><%= req.getState() %></td>
        <td><%= req.getReason() %></td>
        <td>
            <form method="post" action="/librarian/extension-requests/validate">
                <input type="hidden" name="requestId" value="<%= req.getId() %>" />
                <button type="submit">Validate</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
