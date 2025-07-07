<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="itu.spring.bibliotheque.models.BookCopy" %>
<%@ page import="itu.spring.bibliotheque.models.Book" %>
<html>
<head>
    <title>Book Copies</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<%@ include file="navbar.jsp" %>

<h2>Book Copies</h2>
<a href="/librarian/book-copies/new">Add Book Copy</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Book</th>
        <th>Copy Number</th>
        <th>Acquisition Date</th>
        <th>Condition</th>
        <th>State</th>
        <th>Actions</th>
    </tr>

    <%
        List<BookCopy> bookCopies = (List<BookCopy>) request.getAttribute("bookCopies");
        if (bookCopies != null) {
            for (BookCopy copy : bookCopies) {
                Book book = copy.getBook();
    %>
    <tr>
        <td><%= copy.getId() %></td>
        <td><%= book != null ? book.getTitle() : "Unknown" %></td>
        <td><%= copy.getCopyNumber() %></td>
        <td><%= copy.getAcquisitionDate() %></td>
        <td><%= copy.getCopyCondition() %></td>
        <td><%= copy.getState() %></td>
        <td>
            <a href="/librarian/book-copies/edit?id=<%= copy.getId() %>">Edit</a>
            <form action="/librarian/book-copies/delete" method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= copy.getId() %>" />
                <button type="submit" onclick="return confirm('Delete this copy?');">Delete</button>
            </form>
        </td>
    </tr>
    <%
            }
        } else {
    %>
    <tr>
        <td colspan="7">No book copies found.</td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
