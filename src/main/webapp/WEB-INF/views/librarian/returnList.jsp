<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.ReturnBook" %>
<html>
<head>
    <title>Return Requests</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<h2>Return Requests</h2>
<%@ include file="navbar.jsp" %>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Book</th>
        <th>Adherent</th>
        <th>Return Date</th>
        <th>State</th>
        <th>Action</th>
    </tr>
    <%
        java.util.List returns = (java.util.List) request.getAttribute("returns");
        if (returns != null) {
            for (Object obj : returns) {
                ReturnBook rb = (ReturnBook) obj;
    %>
    <tr>
        <td><%= rb.getId() %></td>
        <td><%= rb.getBook() != null ? rb.getBook().getTitle() : "" %></td>
        <td><%= rb.getAdherent() != null && rb.getAdherent().getUtilisateur() != null ? rb.getAdherent().getUtilisateur().getUsername() : "" %></td>
        <td><%= rb.getReturnDate() %></td>
        <td><%= rb.getState() %></td>
        <td>
            <form method="post" action="/librarian/returns/validate">
                <input type="hidden" name="returnId" value="<%= rb.getId() %>" />
                <button type="submit">Validate</button>
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
