<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.model.Adherent,itu.spring.bibliotheque.model.AdherentType,itu.spring.bibliotheque.model.Utilisateur" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Adherents List</title>
</head>
<body>
<h2>Adherents List</h2>
<a href="/librarian/adherents/new">Add New Adherent</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Adherent Type</th>
    </tr>
    <%
        java.util.List adherents = (java.util.List) request.getAttribute("adherents");
        if (adherents != null) {
            for (Object obj : adherents) {
                itu.spring.bibliotheque.model.Adherent adherent = (itu.spring.bibliotheque.model.Adherent) obj;
    %>
    <tr>
        <td><%= adherent.getId() %></td>
        <td><%= adherent.getUtilisateur() != null ? adherent.getUtilisateur().getUsername() : "" %></td>
        <td><%= adherent.getAdherentType() != null ? adherent.getAdherentType().getName() : "" %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
