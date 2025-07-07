<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.Book" %>
<html>
<head><link rel="stylesheet" href="/style.css" />
    <title>Books List</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
<h2>Books List</h2>
<a href="/librarian/books/new">Add New Book</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Author</th>
        <th>Age Min</th>
        <th>Action</th>
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
        <td>
            <form method="get" action="/librarian/loans/create">
                <input type="hidden" name="bookId" value="<%= book.getId() %>" />
                <button type="submit">Louer</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
