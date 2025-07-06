<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Extension Requests</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp" />
<h2>My Extension Requests</h2>
<c:if test="${not empty error}">
    <div style="color:red;">${error}</div>
</c:if>
<table border="1">
    <thead>
    <tr>
        <th>Loan ID</th>
        <th>Book Title</th>
        <th>Request Date</th>
        <th>State</th>
        <th>Reason</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="req" items="${extensionRequests}">
        <tr>
            <td>${req.loan.id}</td>
            <td>${req.loan.book.title}</td>
            <td><fmt:formatDate value="${req.requestDate}" pattern="yyyy-MM-dd"/></td>
            <td>${req.state}</td>
            <td>${req.reason}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/adherent/books">Back to Book List</a>
</body>
</html>
