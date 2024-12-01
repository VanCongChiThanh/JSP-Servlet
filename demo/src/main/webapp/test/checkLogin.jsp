<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 11/15/2024
  Time: 2:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username=request.getParameter("username");
    String password=request.getParameter("password");

    String address="192 Nguyen Luong Bang";
    if("Chi pheo".equals(username) && (!"Thi No".equals(password))){
        request.setAttribute("address",address);
        RequestDispatcher rd= request.getRequestDispatcher("welcome.jsp");
        rd.forward(request,response);
    }else{
        response.sendRedirect("login.jsp");
    }
%>