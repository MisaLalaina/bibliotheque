<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.model.Adherent,itu.spring.bibliotheque.model.AdherentType,itu.spring.bibliotheque.model.Utilisateur" %>
<html>
<head>
    <title>Add New Adherent</title>
</head>
<body>
<h2>Add New Adherent</h2>
<form method="post" action="/adherents/save">
    <label for="userId">User:</label>
    <select id="userId" name="userId" required>
        <% if (request.getAttribute("users") != null) {
            java.util.List users = (java.util.List) request.getAttribute("users");
            for (Object obj : users) {
                User user = (User) obj; // Change package/class as needed
        %>
            <option value="<%= user.getId() %>"><%= user.getUsername() %></option>
        <%     }
           }
        %>
    </select><br>
    <label for="adherentTypeId">Adherent Type:</label>
    <select id="adherentTypeId" name="adherentTypeId" required>
        <% if (request.getAttribute("adherentTypes") != null) {
            java.util.List types = (java.util.List) request.getAttribute("adherentTypes");
            for (Object obj : types) {
                AdherentType type = (AdherentType) obj; // Change package/class as needed
        %>
            <option value="<%= type.getId() %>"><%= type.getName() %></option>
        <%     }
           }
        %>
    </select><br>
    <button type="submit">Save</button>
</form>
<a href="/adherents">Back to list</a>
</body>
</html>
