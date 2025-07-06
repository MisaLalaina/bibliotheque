<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.model.Book" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Books List</title>
</head>
<body>
<h2>Books List</h2>
<a href="/librarian/books/new">Add New Book</a>
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
                itu.spring.bibliotheque.model.Book book = (itu.spring.bibliotheque.model.Book) obj;
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
