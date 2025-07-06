<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add New Adherent</title>
</head>
<body>
<h2>Add New Adherent</h2>
<form method="post" action="/adherents/save">
    <label for="userId">User:</label>
    <select id="userId" name="userId" required>
        <c:forEach var="user" items="${users}">
            <option value="${user.id}">${user.username}</option>
        </c:forEach>
    </select><br>
    <label for="adherentTypeId">Adherent Type:</label>
    <select id="adherentTypeId" name="adherentTypeId" required>
        <c:forEach var="type" items="${adherentTypes}">
            <option value="${type.id}">${type.name}</option>
        </c:forEach>
    </select><br>
    <button type="submit">Save</button>
</form>
<a href="/adherents">Back to list</a>
</body>
</html>
