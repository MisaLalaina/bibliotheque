<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/style.css" />
    <title>Request Extension</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
<h2>Request Extension</h2>
<% String error = (String) request.getAttribute("error"); if (error != null) { %>
    <div style="color:red"><%= error %></div>
<% } %>
<form action="/adherent/extensions/request" method="post">
    <input type="hidden" name="loanId" value="<%= request.getAttribute("loanId") %>" />
    <label>Extension Amount (days):</label>
    <input type="number" name="amount" min="1" value="<%= request.getAttribute("maxExtension") %>" disabled  max="<%= request.getAttribute("maxExtension") %>" required />
    <br/>
    <label>Holiday Direction:</label>
    <% Object[] directions = (Object[]) request.getAttribute("holidayDirections");
       for (Object dir : directions) { %>
        <input type="radio" name="holidayDirection" value="<%= dir.toString() %>" required /> <%= dir.toString() %>
    <% } %>
    <br/>
    <input type="submit" value="Request Extension" />
</form>
</body>
</html>
