<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.Subscription,itu.spring.bibliotheque.models.Adherent" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Subscriptions List</title>
</head>
<body>
<h2>Subscriptions List</h2>
<a href="/librarian/subscriptions/new">Add New Subscription</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Adherent</th>
        <th>From Date</th>
        <th>To Date</th>
    </tr>
    <%
        java.util.List subscriptions = (java.util.List) request.getAttribute("subscriptions");
        if (subscriptions != null) {
            for (Object obj : subscriptions) {
                itu.spring.bibliotheque.models.Subscription sub = (itu.spring.bibliotheque.models.Subscription) obj;
    %>
    <tr>
        <td><%= sub.getId() %></td>
        <td><%= sub.getAdherent() != null && sub.getAdherent().getUtilisateur() != null ? sub.getAdherent().getUtilisateur().getUsername() : "" %></td>
        <td><%= sub.getFromDate() %></td>
        <td><%= sub.getToDate() %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
