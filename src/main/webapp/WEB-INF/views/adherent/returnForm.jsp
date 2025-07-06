<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Return Book</title>
</head>
<body>
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
