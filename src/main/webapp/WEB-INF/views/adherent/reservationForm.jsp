<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.model.Book" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Create Reservation</title>
</head>
<body>
<h2>Create Reservation</h2>
<form method="post" action="/adherent/reservations/save">
    <label for="bookId">Book:</label>
    <select id="bookId" name="bookId" required>
        <%
            java.util.List books = (java.util.List) request.getAttribute("books");
            if (books != null) {
                for (Object obj : books) {
                    itu.spring.bibliotheque.model.Book book = (itu.spring.bibliotheque.model.Book) obj;
        %>
        <option value="<%= book.getId() %>"><%= book.getTitle() %></option>
        <%
                }
            }
        %>
    </select><br>
    <button type="submit">Reserve</button>
</form>
<a href="/adherent/reservations">Back to list</a>
</body>
</html>
