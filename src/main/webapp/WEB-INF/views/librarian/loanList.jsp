<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.Loan,itu.spring.bibliotheque.models.Book,itu.spring.bibliotheque.models.Adherent" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Loans List</title>
</head>
<body>
<h2>Loans List</h2>
<a href="/librarian/loans/create">Add New Loan</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Book</th>
        <th>Adherent</th>
        <th>From Date</th>
        <th>To Date</th>
        <th>Created By</th>
    </tr>
    <%
        java.util.List loans = (java.util.List) request.getAttribute("loans");
        if (loans != null) {
            for (Object obj : loans) {
                Loan loan = (Loan) obj;
    %>
    <tr>
        <td><%= loan.getId() %></td>
        <td><%= loan.getBook() != null ? loan.getBook().getTitle() : "" %></td>
        <td><%= loan.getAdherent() != null && loan.getAdherent().getUtilisateur() != null ? loan.getAdherent().getUtilisateur().getUsername() : "" %></td>
        <td><%= loan.getFromDate() %></td>
        <td><%= loan.getToDate() %></td>
        <td><%= loan.getCreatedBy() != null ? loan.getCreatedBy().getUsername() : "" %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
