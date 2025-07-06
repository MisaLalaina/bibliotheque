<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.Adherent,itu.spring.bibliotheque.models.AdherentType,itu.spring.bibliotheque.models.Utilisateur" %>
<html>
<head>
    <link rel="stylesheet" href="/style.css" />
    <title>Add New Adherent</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
<h2>Add New Adherent</h2>
<form method="post" action="/librarian/adherents/save">
    <label for="userId">User:</label>
    <select id="userId" name="userId" required>
        <%
            java.util.List users = (java.util.List) request.getAttribute("users");
            if (users != null) {
                for (Object obj : users) {
                    itu.spring.bibliotheque.models.Utilisateur user = (itu.spring.bibliotheque.models.Utilisateur) obj;
        %>
        <option value="<%= user.getId() %>"><%= user.getUsername() %></option>
        <%
                }
            }
        %>
    </select><br>
    <label for="adherentTypeId">Adherent Type:</label>
    <select id="adherentTypeId" name="adherentTypeId" required>
        <%
            java.util.List types = (java.util.List) request.getAttribute("adherentTypes");
            if (types != null) {
                for (Object obj : types) {
                    itu.spring.bibliotheque.models.AdherentType type = (itu.spring.bibliotheque.models.AdherentType) obj;
        %>
        <option value="<%= type.getId() %>"><%= type.getName() %></option>
        <%
                }
            }
        %>
    </select><br>
    <button type="submit">Save</button>
</form>
<a href="/adherents">Back to list</a>
</body>
</html>
