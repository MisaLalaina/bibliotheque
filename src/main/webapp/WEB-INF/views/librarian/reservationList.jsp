<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.Reservation,itu.spring.bibliotheque.models.Book,itu.spring.bibliotheque.models.Adherent,itu.spring.bibliotheque.models.Utilisateur" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Reservations List</title>
</head>
<body>
<h2>Reservations List</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Adherent</th>
        <th>Book</th>
        <th>Date</th>
        <th>State</th>
        <th>Validated By</th>
        <th>Action</th>
    </tr>
    <%
        java.util.List reservations = (java.util.List) request.getAttribute("reservations");
        if (reservations != null) {
            for (Object obj : reservations) {
                itu.spring.bibliotheque.models.Reservation reservation = (itu.spring.bibliotheque.models.Reservation) obj;
    %>
    <tr>
        <td><%= reservation.getId() %></td>
        <td><%= reservation.getAdherent() != null && reservation.getAdherent().getUtilisateur() != null ? reservation.getAdherent().getUtilisateur().getUsername() : "" %></td>
        <td><%= reservation.getBook() != null ? reservation.getBook().getTitle() : "" %></td>
        <td><%= reservation.getReservationDate() %></td>
        <td><%= reservation.getState() %></td>
        <td><%= reservation.getValidatedBy() != null ? reservation.getValidatedBy().getUsername() : "" %></td>
        <td>
            <form method="post" action="/librarian/reservations/validate">
                <input type="hidden" name="reservationId" value="<%= reservation.getId() %>" />
                <button type="submit">Validate</button>
            </form>
            <form method="post" action="/librarian/loans/reservation">
                <input type="hidden" name="reservationId" value="<%= reservation.getId() %>" />
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
