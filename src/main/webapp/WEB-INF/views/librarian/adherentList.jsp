<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.Adherent,itu.spring.bibliotheque.models.AdherentType,itu.spring.bibliotheque.models.Utilisateur" %>
<html>
<head>
    <link rel="stylesheet" href="/style.css" />
    <title>Adherents List</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
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
                itu.spring.bibliotheque.models.Adherent adherent = (itu.spring.bibliotheque.models.Adherent) obj;
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
