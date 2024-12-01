<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 11/15/2024
  Time: 1:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
       <%
           String username=request.getParameter("username");
           String address=request.getParameter("address");
           String tmp=(String)session.getAttribute("address");
       %>
       Welcome=<%=username%> <br/>
       You are living at= <%=address%> <br>
</body>
</html>