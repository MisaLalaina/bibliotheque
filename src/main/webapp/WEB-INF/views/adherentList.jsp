<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <c:forEach var="adherent" items="${adherents}">
        <tr>
            <td>${adherent.id}</td>
            <td>${adherent.utilisateur.username}</td>
            <td>${adherent.adherentType.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
