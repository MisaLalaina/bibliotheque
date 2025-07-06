<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.dto.BookLoan" %>
<html>
<head>
    <title>My Books</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<%@ include file="navbar.jsp" %>
<h2>My Books</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Author</th>
        <th>Age Min</th>
        <th>State</th>
        <th>From Date</th>
        <th>To Date</th>
        <th>Action</th>
    </tr>
    <%
        java.util.List books = (java.util.List) request.getAttribute("books");
        if (books != null) {
            for (Object obj : books) {
                BookLoan bookLoan = (BookLoan) obj;
    %>
    <tr>
        <td><%= bookLoan.getBookId() %></td>
        <td><%= bookLoan.getBookTitle() %></td>
        <td><%= bookLoan.getBookAuthor() %></td>
        <td><%= bookLoan.getBookAgeMin() %></td>
        <td><%= bookLoan.getBookState() %></td>
        <td><%= bookLoan.getFromDate() != null ? bookLoan.getFromDate() : "" %></td>
        <td><%= bookLoan.getToDate() != null ? bookLoan.getToDate() : "" %></td>
        <td>
            <form method="get" action="/adherent/returns/form">
                <input type="hidden" name="loanId" value="<%= bookLoan.getLoanId() %>" />
                <button type="submit">Return</button>
            </form>

            <form method="get" action="/adherent/extensions/form">
                <input type="hidden" name="loanId" value="<%= bookLoan.getLoanId() %>" />
                <button type="submit">Extension</button>
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
