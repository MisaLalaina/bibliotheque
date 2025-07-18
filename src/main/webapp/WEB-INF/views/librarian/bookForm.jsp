<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.Book" %>
<html>
<head>
    <title>Add New Book</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<%@ include file="navbar.jsp" %>
<h2>Add New Book</h2>
<form method="post" action="/librarian/books/save">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required><br>
    <label for="author">Author:</label>
    <input type="text" id="author" name="author"><br>
    <label for="ageMin">Age Min:</label>
    <input type="number" id="ageMin" name="ageMin" min="0"><br>
    <label for="state">State:</label>
    <input type="text" id="state" name="state"><br>
    <button type="submit">Save</button>
</form>
<a href="/books">Back to list</a>
</body>
</html>
