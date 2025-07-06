<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.Reservation,itu.spring.bibliotheque.models.Book,itu.spring.bibliotheque.models.Adherent" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>My Reservations</title>
</head>
<body>
<h2>My Reservations</h2>
<a href="/adherent/reservations/new">Create Reservation</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Book</th>
        <th>Date</th>
        <th>State</th>
    </tr>
    <%
        java.util.List reservations = (java.util.List) request.getAttribute("reservations");
        if (reservations != null) {
            for (Object obj : reservations) {
                itu.spring.bibliotheque.models.Reservation reservation = (itu.spring.bibliotheque.models.Reservation) obj;
    %>
    <tr>
        <td><%= reservation.getId() %></td>
        <td><%= reservation.getBook() != null ? reservation.getBook().getTitle() : "" %></td>
        <td><%= reservation.getReservationDate() %></td>
        <td><%= reservation.getState() %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
