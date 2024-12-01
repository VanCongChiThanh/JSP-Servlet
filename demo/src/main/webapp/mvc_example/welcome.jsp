<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 11/22/2024
  Time: 12:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.ArrayList,com.mvc_example.model.bean.Wife" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Address</th>
        <th>Is Alive</th>
    </tr>
    <%
        ArrayList<Wife> wifeArray = (ArrayList<Wife>) request.getAttribute("wifeList");
        for (Wife wife : wifeArray) {
    %>
    <tr>
        <td><%= wife.getName() %></td>
        <td><%= wife.getAddress() %></td>
        <td><%= wife.isAlive() %></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>