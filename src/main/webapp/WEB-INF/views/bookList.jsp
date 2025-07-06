<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books List</title>
</head>
<body>
<!-- Navigation bar -->
<div style="background:#eee;padding:10px;">
    <a href="/home">Home</a> |
    <a href="/adherents">Adherents</a> |
    <a href="/users/new">Add User</a> |
    <a href="/adherents/new">Add Adherent</a> |
    <a href="/books">Books</a> |
    <a href="/logout">Logout</a>
</div>

<h2>Books List</h2>
<a href="/books/new">Add New Book</a>
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
