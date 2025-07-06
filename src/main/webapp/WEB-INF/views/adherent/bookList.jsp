<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.Book" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>My Books</title>
</head>
<body>
<h2>My Books</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Author</th>
        <th>Age Min</th>
        <th>State</th>
    </tr>
    <%
        java.util.List books = (java.util.List) request.getAttribute("books");
        if (books != null) {
            for (Object obj : books) {
                itu.spring.bibliotheque.models.Book book = (itu.spring.bibliotheque.models.Book) obj;
    %>
    <tr>
        <td><%= book.getId() %></td>
        <td><%= book.getTitle() %></td>
        <td><%= book.getAuthor() %></td>
        <td><%= book.getAgeMin() %></td>
        <td><%= book.getState() %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
