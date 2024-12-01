<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qlpb.model.bean.Employee" %>
<html>
<head>
    <title>Danh Sách Nhân Viên</title>
    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 90%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
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

        .actions {
            text-align: center;
        }

        .actions .action-btn {
            margin: 0 5px;
            padding: 5px 10px;
            text-decoration: none;
            color: white;
            border-radius: 5px;
        }

        .actions .edit {
            background-color: #4CAF50;
        }

        .actions .delete {
            background-color: #f44336;
        }

        .actions form {
            display: inline;
        }

        a[href*="addForm"] {
            display: inline-block;
            margin: 10px 0;
            padding: 10px 15px;
            background-color: #2196F3;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        a[href*="addForm"]:hover {
            background-color: #0b7dda;
        }

        .no-employees {
            text-align: center;
            color: #ff0000;
            font-style: italic;
        }

        /* Nút quay lại */
        .back-link {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background-color: #f1f1f1;
            color: #333;
            text-decoration: none;
            border-radius: 5px;
        }

        .back-link:hover {
            background-color: #ddd;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>Danh Sách Nhân Viên</h1>
    <%
        boolean isLoggedIn = session.getAttribute("username") != null;
    %>

    <%
        if (isLoggedIn) { %>
    <a href="employees?action=addForm">Add New Employee</a>
    <% } %>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Tên Nhân Viên</th>
            <th>Địa Chỉ</th>
            <th>ID Phòng Ban</th>
            <% if (isLoggedIn) { %>
            <th>Hành Động</th>
            <% } %>

        </tr>
        </thead>
        <tbody>
        <%
            List<Employee> employees = (List<Employee>) request.getAttribute("employees");
            if (employees != null && !employees.isEmpty()) {
                for (Employee employee : employees) {
        %>
        <tr>
            <td><%= employee.getId() %></td>
            <td><%= employee.getName() %></td>
            <td><%= employee.getAddress() %></td>
            <td><%= employee.getDepartmentID() %></td>
            <% if (isLoggedIn) { %>
            <td class="actions">
                <a href="employees?action=editForm&id=<%= employee.getId() %>" class="action-btn edit">Sửa</a>
                <form action="employees?action=delete&id=<%= employee.getId() %>" method="POST" style="display:inline;">
                    <button type="submit" class="action-btn delete">Xóa</button>
                </form>
            </td>
            <% } %>

        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="5" class="no-employees">Không có nhân viên nào</td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>

</html>