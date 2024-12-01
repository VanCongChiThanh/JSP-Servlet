<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 11/15/2024
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Screen</title>
</head>
<body>
       <%
           String temp="temp";
           session.setAttribute("temp",temp);
       %>
       <form name="form1" action="checkLogin.jsp" method="post">
           Username: <input type="text" name="username"><br>
           Password: <input type="password" name="password"><br>
           <input type="submit" value="Login">
           <input type="reset" value="Reset">
       </form>
</body>
</html>