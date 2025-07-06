<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.Utilisateur,itu.spring.bibliotheque.models.Role" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<h2>Create New User</h2>
<form method="post" action="/librarian/users/save">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>
    <label for="birthDate">Birth Date:</label>
    <input type="date" id="birthDate" name="birthDate" required><br>
    <label for="roleId">Role:</label>
    <select id="roleId" name="roleId" required>
        <%
            java.util.List roles = (java.util.List) request.getAttribute("roles");
            if (roles != null) {
                for (Object obj : roles) {
                    itu.spring.bibliotheque.models.Role role = (itu.spring.bibliotheque.models.Role) obj;
        %>
        <option value="<%= role.getId() %>"><%= role.getName() %></option>
        <%
                }
            }
        %>
    </select><br>
    <button type="submit">Save</button>
</form>
<a href="/adherents">Back to adherents</a>
</body>
</html>
