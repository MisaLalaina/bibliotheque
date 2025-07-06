<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.Adherent" %>
<html>
<head>
    <title>Add Subscription</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<%@ include file="navbar.jsp" %>
<h2>Add Subscription</h2>
<form method="post" action="/librarian/subscriptions/save">
    <label for="adherentId">Adherent:</label>
    <select id="adherentId" name="adherentId" required>
        <%
            java.util.List adherents = (java.util.List) request.getAttribute("adherents");
            if (adherents != null) {
                for (Object obj : adherents) {
                    itu.spring.bibliotheque.models.Adherent adherent = (itu.spring.bibliotheque.models.Adherent) obj;
        %>
        <option value="<%= adherent.getId() %>"><%= adherent.getUtilisateur() != null ? adherent.getUtilisateur().getUsername() : "" %></option>
        <%
                }
            }
        %>
    </select><br>
    <label for="fromDate">From Date:</label>
    <input type="date" id="fromDate" name="fromDate" required><br>
    <label for="toDate">To Date:</label>
    <input type="date" id="toDate" name="toDate" required><br>
    <button type="submit">Save</button>
</form>
<a href="/librarian/subscriptions">Back to list</a>
</body>
</html>
