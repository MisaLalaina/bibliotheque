<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.HolidayList" %>
<html>
<head>
    <link rel="stylesheet" href="/style.css" />
    <title>Add Holiday</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
<h2>Add Holiday</h2>
<% String error = (String) request.getAttribute("error"); if (error != null) { %>
    <div style="color:red"><%= error %></div>
<% } %>
<form action="/librarian/holidays/save" method="post">
    <label>Date:</label>
    <input type="date" name="holidayDate" value="<%= request.getAttribute("holiday") != null ? ((HolidayList)request.getAttribute("holiday")).getHolidayDate() : "" %>" required />
    <br/>
    <label>Description:</label>
    <input type="text" name="description" value="<%= request.getAttribute("holiday") != null ? ((HolidayList)request.getAttribute("holiday")).getDescription() : "" %>" />
    <br/>
    <input type="submit" value="Save" />
</form>
</body>
</html>
