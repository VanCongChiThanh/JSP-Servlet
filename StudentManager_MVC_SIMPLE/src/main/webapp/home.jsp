<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login successful</title>
    <style>
        table, td, th {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<%
    Admin u=(Admin) request.getAttribute("user");
    if (u != null) {
        session.setAttribute("user", u);
    }
    u = (Admin) session.getAttribute("user");

    if (u == null) {
%>
<h1>Session expired! Please login again!</h1>
<hr>
<a href="index.jsp">Back</a>
<%
} else {
%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.student_management.model.dao.DAO" %>
<%@ page import="com.student_management.model.bean.Student" %>
<%@ page import="com.student_management.model.bean.Admin" %>
<%
    DAO dao = new DAO();
    ArrayList<Student> arr = dao.getAllStudents();
%>
<h1>Hello my friend :  <%= u.getUsername()%>!</h1>
<hr>
<a href="addStudent.jsp">Thêm mới</a>
<!-- Thêm form tìm kiếm -->
<form action="SearchStudentServlet" method="GET">
    <label for="search-name">Tên cần tìm:</label>
    <input id="search-name" type="text" name="name">
    <button type="submit">Search</button>
</form>
<table>
    <thead>	<tr>
        <th>MSSV</th>
        <th>Họ Tên</th>
        <th>Tuổi </th>
        <th>Khoa</th>
        <th></th>
        <th></th>
    </tr> </thead>
    <tbody>
    <%
        for (Student sv : arr) {
    %>
    <tr>
        <td><%=sv.getId()%></td>
        <td><%=sv.getName()%></td>
        <td><%=sv.getAge()%></td>
        <td><%=sv.getFaculty()%></td>
        <td><a href="editStudent.jsp?mssv=<%=sv.getId()%>">Edit</a></td>
        <td><a href="deleteStudentServlet?mssv=<%=sv.getId()%>">Delete</a></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<%
    }
%>
</body>
</html>