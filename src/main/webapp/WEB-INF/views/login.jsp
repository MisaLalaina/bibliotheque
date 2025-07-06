<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/style.css" />
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form method="post" action="/login">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>
    <button type="submit">Login</button>
</form>
<c:if test="${not empty error}">
    <div style="color:red;">${error}</div>
</c:if>
</body>
</html>
