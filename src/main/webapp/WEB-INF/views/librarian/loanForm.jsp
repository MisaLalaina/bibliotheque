<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.Loan,itu.spring.bibliotheque.models.Book,itu.spring.bibliotheque.models.Adherent" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Loan Form</title>
</head>
<body>
<h2>Create Loan</h2>
<% String error = (String) request.getAttribute("error"); if (error != null) { %>
    <div style="color:red"><%= error %></div>
<% } %>
<form action="/librarian/loans/save" method="post">
    <%
        Loan loan = (Loan) request.getAttribute("loan");
        Book book = (Book) request.getAttribute("book");
        Adherent adherent = (Adherent) request.getAttribute("adherent");
        java.util.List books = (java.util.List) request.getAttribute("books");
        java.util.List adherents = (java.util.List) request.getAttribute("adherents");
    %>
    <label>Book:</label>
    <% if (book != null) { %>
        <input type="text" name="bookTitle" value="<%= book.getTitle() %>" readonly />
        <input type="hidden" name="book.id" value="<%= book.getId() %>" />
    <% } else { %>
        <select name="book.id">
            <option value="">-- Select Book --</option>
            <% for (Object obj : books) {
                Book b = (Book) obj; %>
                <option value="<%= b.getId() %>"><%= b.getTitle() %></option>
            <% } %>
        </select>
    <% } %>
    <br/>
    <label>Adherent:</label>
    <% if (adherent != null) { %>
        <input type="text" name="adherentName" value="<%= adherent.getUtilisateur().getUsername() %>" readonly />
        <input type="hidden" name="adherent.id" value="<%= adherent.getId() %>" />
    <% } else { %>
        <select name="adherent.id">
            <option value="">-- Select Adherent --</option>
            <% for (Object obj : adherents) {
                Adherent a = (Adherent) obj; %>
                <option value="<%= a.getId() %>"><%= a.getUtilisateur().getUsername() %></option>
            <% } %>
        </select>
    <% } %>
    <br/>
    <label>From Date:</label>
    <input type="date" name="fromDate" value="<%= loan != null && loan.getFromDate() != null ? loan.getFromDate().toString() : "" %>" required />
    <br/>
    <input type="submit" value="Save Loan" />
</form>
</body>
</html>
