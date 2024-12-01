<%@ page import="com.qlpb.model.bean.Department" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Departments</title>
    <style>
        .container{
            width: 90%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        td {
            background-color: #fff;
        }

        td a {
            color: #007BFF;
            text-decoration: none;
        }

        td a:hover {
            text-decoration: underline;
        }

        .no-departments {
            text-align: center;
            font-style: italic;
            color: #888;
            background-color: #f9f9f9;
        }

        button {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
        }

        button:hover {
            background-color: #c82333;
        }

        .action-links {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .action-links a {
            margin-right: 10px;
        }

        .action-links button {
            margin-left: 10px;
        }

        /* Định dạng cho liên kết "Thêm phòng ban" */
        a {
            color: #007BFF;
            text-decoration: none;
            margin: 10px;
            display: inline-block;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">

    <h1>Quản lý phòng ban</h1>
    <%
        boolean isLoggedIn = session.getAttribute("username") != null;
    %>

    <%if (isLoggedIn) { %>
    <a href="departments?action=addForm">Thêm phòng ban</a>
    <% } %>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Mô tả</th>
            <th>Hành động</th>
            <% if (isLoggedIn) { %>
            <th>Xem nhân viên</th>
            <% } %>
        </tr>
        </thead>
        <tbody>
        <%
            List<Department> departments = (List<Department>) request.getAttribute("departments");

            if (departments != null && !departments.isEmpty()) {
                for (Department department : departments) {
        %>
        <tr>
            <td><%= department.getId() %></td>
            <td><%= department.getName() %></td>
            <td><%= department.getDescription() %></td>
            <% if (isLoggedIn) { %>
            <td>
                <a href="departments?action=editForm&id=<%= department.getId() %>">Sửa</a> |
                <form action="departments?action=delete&id=<%= department.getId() %>" method="POST" style="display:inline;">
                    <button type="submit" >Xóa</button>
                </form>
            </td>
            <% } %>
            <td>
                <a href="employees?action=viewByDepartment&id=<%= department.getId() %>">Xem nhân viên</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="5" class="no-departments">Không có phòng ban nào</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

</div>>
</body>
</html>