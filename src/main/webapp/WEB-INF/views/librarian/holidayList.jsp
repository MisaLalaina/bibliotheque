<%@ page contentType="text/html;charset=UTF-8" language="java" import="itu.spring.bibliotheque.models.HolidayList" %>
<html>
</head>
    <link rel="stylesheet" href="/style.css" />
    <title>Holiday List</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
<h2>Holiday List</h2>
<a href="/librarian/holidays/new">Add New Holiday</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Date</th>
        <th>Description</th>
    </tr>
    <%
        java.util.List holidays = (java.util.List) request.getAttribute("holidays");
        if (holidays != null) {
            for (Object obj : holidays) {
                HolidayList holiday = (HolidayList) obj;
    %>
    <tr>
        <td><%= holiday.getId() %></td>
        <td><%= holiday.getHolidayDate() %></td>
        <td><%= holiday.getDescription() %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
