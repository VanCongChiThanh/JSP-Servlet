<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Sinh Vien</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f9fc;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 400px;
        }

        h1 {
            text-align: center;
            color: #333333;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            font-weight: bold;
            color: #555555;
        }

        input[type="text"] {
            padding: 10px;
            border: 1px solid #cccccc;
            border-radius: 4px;
            width: 100%;
            box-sizing: border-box;
        }

        input[type="submit"], input[type="button"] {
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #0056b3;
        }

        input[type="button"] {
            background-color: #6c757d;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Thêm mới sinh viên</h1>
    <form action="AddServlet" method="POST">
        <label for="id">Mã SV:</label>
        <input type="text" id="id" name="id" placeholder="Nhập mã sinh viên">

        <label for="name">Họ Tên:</label>
        <input type="text" id="name" name="name" placeholder="Nhập họ tên sinh viên">

        <label for="age">Tuổi:</label>
        <input type="text" id="age" name="age" placeholder="Nhập tuổi sinh viên">

        <label for="faculty">Khoa:</label>
        <input type="text" id="faculty" name="faculty" placeholder="Nhập khoa">

        <input type="submit" value="Lưu lại">
        <input type="button" value="Quay lại" onClick="history.back()">
    </form>
</div>
</body>
</html>