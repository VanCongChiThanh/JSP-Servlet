<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chỉnh Sửa Nhân Viên</title>
    <style>
        .container {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"], select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            display: block;
            text-align: center;
            padding: 10px 15px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .back-link {
            display: block;
            margin-top: 15px;
            text-align: center;
            text-decoration: none;
            color: #007bff;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h1>Chỉnh Sửa Nhân Viên</h1>
    <form action="employees" method="post">
        <input type="hidden" name="action" value="update">
        <div class="form-group">
            <label for="id">ID Nhân Viên:</label>
            <input type="text" id="id" name="id" value="${employee.id}" readonly class="form-control" required>
        </div>
        <div class="form-group">
            <label for="name">Tên Nhân Viên:</label>
            <input type="text" id="name" name="name" value="${employee.name}" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="departmentID">Phòng Ban:</label>
            <select id="departmentID" name="departmentID" class="form-control" required>
                <option value="PB01" ${employee.departmentID == 'PB01' ? 'selected' : ''}>Phòng Ban 1</option>
                <option value="PB02" ${employee.departmentID == 'PB02' ? 'selected' : ''}>Phòng Ban 2</option>
                <option value="PB03" ${employee.departmentID == 'PB03' ? 'selected' : ''}>Phòng Ban 3</option>
            </select>
        </div>
        <div class="form-group">
            <label for="address">Địa Chỉ:</label>
            <input type="text" id="address" name="address" value="${employee.address}" class="form-control" required>
        </div>
        <button type="submit">Lưu</button>
        <a href="employees" class="back-link">Quay lại</a>
    </form>
</div>
</body>
</html>