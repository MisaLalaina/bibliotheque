<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.Book" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Create Reservation</title>
</head>
<body>
<h2>Create Reservation</h2>
<form method="post" action="/adherent/reservations/save">
    <%
        if (request.getAttribute("error") != null) {
            String error = (String) request.getAttribute("error");
            %>
            <div style="color: red;">
                <%= error %>
            </div>
            <%
        }
    %>
    <div>
        
    </div>
    <div>
        <label for="reservationDate">Reservation Date:</label>
        <input type="date" id="reservationDate" name="reservationDate" required>
    </div>
    <label for="bookId">Book:</label>
    <select id="bookId" name="bookId" required>
        <%
            java.util.List books = (java.util.List) request.getAttribute("books");
            if (books != null) {
                for (Object obj : books) {
                    itu.spring.bibliotheque.models.Book book = (itu.spring.bibliotheque.models.Book) obj;
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
