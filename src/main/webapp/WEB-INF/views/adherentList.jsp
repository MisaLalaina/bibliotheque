<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.model.Adherent,itu.spring.bibliotheque.model.AdherentType,itu.spring.bibliotheque.model.Utilisateur" %>
<html>
<head>
    <title>Adherents List</title>
</head>
<body>
<h2>Adherents List</h2>
<a href="/adherents/new">Add New Adherent</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Adherent Type</th>
    </tr>
    <% if (request.getAttribute("adherents") != null) {
        java.util.List adherents = (java.util.List) request.getAttribute("adherents");
        for (Object obj : adherents) {
            Adherent adherent = (Adherent) obj; // Change package/class as needed
    %>
        <tr>
            <td><%= adherent.getId() %></td>
            <td><%= adherent.getUtilisateur().getUsername() %></td>
            <td><%= adherent.getAdherentType().getName() %></td>
        </tr>
    <%   }
       }
    %>
</table>
</body>
</html>
