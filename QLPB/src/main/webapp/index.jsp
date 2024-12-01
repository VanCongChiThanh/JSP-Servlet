<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Nhân Sự</title>
    <style>
        /* Tổng thể */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            flex-direction: column;
            background-color: #f4f4f9;
        }

        /* Header */
        header {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        header h1 {
            margin: 0;
        }

        header .auth {
            display: flex;
            align-items: center;
        }

        header .auth a {
            color: white;
            text-decoration: none;
            margin-left: 10px;
            padding: 5px 10px;
            background-color: #333;
            border-radius: 5px;
        }

        header .auth a:hover {
            background-color: #555;
        }

        /* Main container */
        .container {
            flex-grow: 1;
            display: flex;
            overflow: hidden;
        }

        /* Sidebar */
        .sidebar {
            width: 200px;
            background-color: #333;
            color: white;
            display: flex;
            flex-direction: column;
        }

        .sidebar a {
            text-decoration: none;
            color: white;
            padding: 15px;
            text-align: center;
            border-bottom: 1px solid #444;
        }

        .sidebar a:hover {
            background-color: #575757;
        }

        /* Main content */
        .main-content {
            flex-grow: 1;
            padding: 0;
        }

        iframe {
            width: 100%;
            height: 100%;
            border: none;
        }
    </style>
</head>
<body>
<!-- Header -->
<header>
    <h1>Quản Lý Nhân Sự</h1>
    <div class="auth">
        <%
            String username = (session != null) ? (String) session.getAttribute("username") : null;

            if (username != null) {
        %>
        <span>Xin chào, <%= username %></span>
        <a href="/logout">Đăng Xuất</a>
        <% } else { %>
        <a href="/login">Đăng Nhập</a>
        <% } %>
    </div>
</header>

<!-- Content -->
<div class="container">
    <!-- Sidebar -->
    <div class="sidebar">
        <a href="departments" target="content-frame">Quản lý Phòng Ban</a>
        <a href="employees" target="content-frame">Quản lý Nhân Viên</a>
    </div>
    <div class="main-content">
        <iframe name="content-frame" src="<%= request.getContextPath() %>/departments"></iframe>
    </div>
</div>
</body>
</html>