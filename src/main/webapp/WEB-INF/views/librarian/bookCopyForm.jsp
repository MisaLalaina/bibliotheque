<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="itu.spring.bibliotheque.models.Book" %>
<html>
<head>
    <title>Book Copy Form</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<%@ include file="navbar.jsp" %>
<h2>Book Copy Form</h2>
<form action="/librarian/book-copies/save" method="post">
    <input type="hidden" name="id" value="<%= request.getAttribute("bookCopy") != null ? ((itu.spring.bibliotheque.models.BookCopy)request.getAttribute("bookCopy")).getId() : "" %>" />
    <label>Book:</label>
    <select name="book.id" required>
        <%
            List<Book> books = (List<Book>) request.getAttribute("books");
            itu.spring.bibliotheque.models.BookCopy bookCopy = (itu.spring.bibliotheque.models.BookCopy) request.getAttribute("bookCopy");
            Integer selectedBookId = (bookCopy != null && bookCopy.getBook() != null) ? bookCopy.getBook().getId() : null;
            if (books != null) {
                for (Book book : books) {
        %>
            <option value="<%= book.getId() %>" <%= (selectedBookId != null && book.getId().equals(selectedBookId)) ? "selected" : "" %>><%= book.getTitle() %></option>
        <%
                }
            }
        %>
    </select><br/>
    <label>Copy Number:</label>
    <input type="number" name="copyNumber" value="<%= bookCopy != null ? bookCopy.getCopyNumber() : "" %>" required /><br/>
    <label>Acquisition Date:</label>
    <input type="date" name="acquisitionDate" value="<%= bookCopy != null && bookCopy.getAcquisitionDate() != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(bookCopy.getAcquisitionDate()) : "" %>" /><br/>
    <button type="submit">Save</button>
    <a href="/librarian/book-copies">Cancel</a>
</form>
</body>
</html>
