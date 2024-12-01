<%@ page import="com.student_management.model.bean.Student" %>
<%@ page import="com.student_management.model.dao.DAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Sinh Vien</title>
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

        .error {
            color: red;
            font-size: 12px;
            margin-top: -10px;
        }
    </style>
</head>
<body>
<%
    Student sv = null;
    try {
        String mssv = (request.getParameter("id"));
        DAO dao = new DAO();
        sv = dao.getStudentById(mssv);
    } catch (Exception e) {}
%>
<div class="container">
    <h1>Sửa sinh viên <%= sv != null ? sv.getName() : "" %></h1>
    <form id="editForm" action="EditServlet" method="POST" onsubmit="return validateForm()">
        <label for="id">Mã SV:</label>
        <input type="text" id="id" value="<%=sv != null ? sv.getId() : "" %>" name="id" readonly>

        <label for="name">Họ Tên:</label>
        <input type="text" id="name" value="<%=sv != null ? sv.getName() : "" %>" name="name">
        <div id="nameError" class="error"></div>

        <label for="age">Tuổi:</label>
        <input type="text" id="age" value="<%=sv != null ? sv.getAge() : "" %>" name="age">
        <div id="ageError" class="error"></div>

        <label for="faculty">Khoa:</label>
        <input type="text" id="faculty" value="<%=sv != null ? sv.getFaculty() : "" %>" name="faculty">
        <div id="facultyError" class="error"></div>

        <input type="submit" value="Lưu lại">
        <input type="button" value="Quay lại" onClick="history.back()">
    </form>
</div>

<script>
    function validateForm() {
        let isValid = true;

        document.getElementById("nameError").textContent = "";
        document.getElementById("ageError").textContent = "";
        document.getElementById("facultyError").textContent = "";

        const name = document.getElementById("name").value.trim();
        const age = document.getElementById("age").value.trim();
        const faculty = document.getElementById("faculty").value.trim();

        // Validate name
        if (name === "") {
            document.getElementById("nameError").textContent = "Tên không được để trống.";
            isValid = false;
        }

        if (age === "") {
            document.getElementById("ageError").textContent = "Tuổi không được để trống.";
            isValid = false;
        } else if (isNaN(age) || age <= 0) {
            document.getElementById("ageError").textContent = "Tuổi phải là số dương.";
            isValid = false;
        }

        if (faculty === "") {
            document.getElementById("facultyError").textContent = "Khoa không được để trống.";
            isValid = false;
        }
        return isValid;
    }
</script>
</body>
</html>