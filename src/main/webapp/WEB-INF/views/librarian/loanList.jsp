<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.Loan,itu.spring.bibliotheque.models.Book,itu.spring.bibliotheque.models.Adherent" %>
<html>
<head>
    <title>Loans List</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<%@ include file="navbar.jsp" %>
<h2>Loans List</h2>
<a href="/librarian/loans/create">Add New Loan</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Book</th>
        <th>No Copy</th>
        <th>Adherent</th>
        <th>From Date</th>
        <th>To Date</th>
        <th>State</th>
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
        <td><%= loan.getBookCopy().getBook() != null ? loan.getBookCopy().getBook().getTitle() : "" %></td>
        <td><%= loan.getBookCopy() != null ? loan.getBookCopy().getCopyNumber() : "" %></td>
        <td><%= loan.getAdherent() != null && loan.getAdherent().getUtilisateur() != null ? loan.getAdherent().getUtilisateur().getUsername() : "" %></td>
        <td><%= loan.getFromDate() %></td>
        <td><%= loan.getToDate() %></td>
        <td><%= loan.getState() %></td>
        <td><%= loan.getCreatedBy() != null ? loan.getCreatedBy().getUsername() : "" %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
