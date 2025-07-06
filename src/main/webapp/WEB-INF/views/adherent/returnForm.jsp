<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/style.css" />
    <title>Return Book</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
<h2>Return Book</h2>
<form action="/adherent/returns/create" method="post">
    <input type="hidden" name="loanId" value="<%= request.getAttribute("loanId") %>" />
    <label>Return Date:</label>
    <input type="date" name="returnDate" value="<%= request.getAttribute("today") %>" required />
    <br/>
    <input type="submit" value="Submit Return Request" />
</form>
</body>
</html>
