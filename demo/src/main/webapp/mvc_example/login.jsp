<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 11/22/2024
  Time: 12:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login MVC</title>
</head>
<body>
<form action="CheckLoginServlet" method="post">
    Username: <input type="text" name="username" >
    Password: <input type="password" name="password" >
    <br>
    <br>
    <input type="submit" value="Login">
    <input type="reset"  value="Reset">
    <h3 name="error"></h3>
</form>
</body>
</html>