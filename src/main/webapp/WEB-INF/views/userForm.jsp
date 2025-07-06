<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<h2>Create New User</h2>
<form method="post" action="/users/save">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>
    <label for="birthDate">Birth Date:</label>
    <input type="date" id="birthDate" name="birthDate" required><br>
    <label for="roleId">Role:</label>
    <select id="roleId" name="roleId" required>
        <c:forEach var="role" items="${roles}">
            <option value="${role.id}">${role.name}</option>
        </c:forEach>
    </select><br>
    <button type="submit">Save</button>
</form>
<a href="/adherents">Back to adherents</a>
</body>
</html>
