<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Department</title>
    <style>
        /* Style for the form */
        form {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
        }

        input, textarea {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<h1>Thêm phòng ban</h1>

<form action="departments?action=insert" method="post">
    <div>
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" required>
    </div>
    <div>
        <label for="name">Tên phòng ban:</label>
        <input type="text" id="name" name="name" required>
    </div>

    <div>
        <label for="description">Mô tả:</label>
        <textarea id="description" name="description" rows="4" required></textarea>
    </div>

    <button type="submit">Thêm</button>
</form>

</body>
</html>