<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        hr {
            margin: 20px 0;
            border: none;
            border-top: 2px solid #ddd;
        }

        .container {
            max-width: 900px;
            margin: 20px auto;
            padding: 20px;
            background: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        .actions {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .actions a,
        .actions form button {
            background-color: #007bff;
            color: white;
            text-decoration: none;
            padding: 8px 12px;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .actions a:hover,
        .actions form button:hover {
            background-color: #0056b3;
        }

        form {
            display: inline-flex;
            align-items: center;
        }

        form input[type="text"] {
            padding: 5px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 12px;
            text-align: center;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        td a {
            color: #007bff;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        td a:hover {
            color: #0056b3;
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
<div class="container">
    <h1>Session expired! Please login again!</h1>
    <hr>
    <a href="index.jsp">Back</a>
</div>
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
<div class="container">
    <h1>Welcome, <%= u.getUsername()%>!</h1>
    <hr>
    <div class="actions">
        <a href="addStudent.jsp">Add New Student</a>
        <form action="SearchStudentServlet" method="GET">
            <label for="search-name">Search:</label>
            <input id="search-name" type="text" name="name" placeholder="Enter name">
            <button type="submit">Search</button>
        </form>
    </div>
    <table>
        <thead>
        <tr>
            <th>MSSV</th>
            <th>Full Name</th>
            <th>Age</th>
            <th>Faculty</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
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
</div>
<%
    }
%>
</body>
</html>