package com.qlpb.model.dao;

import com.qlpb.model.bean.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private static final String DATABASE_HOST = "jdbc:mysql://localhost:3306/btad";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "12345Thanh";

    private Connection databaseConnection;

    // Constructor: Kết nối cơ sở dữ liệu
    public DepartmentDAO() {
        try {
            // Load JDBC driver (for newer MySQL versions)
            Class.forName("com.mysql.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(DATABASE_HOST, DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch (Exception e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    // Fetch all departments from the database
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM phongban"; // Thay đổi bảng name cho phù hợp với cơ sở dữ liệu

        try (Statement statement = databaseConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String id = resultSet.getString("idpb"); // Thay đổi cột theo cơ sở dữ liệu
                String name = resultSet.getString("tenpb"); // Thay đổi cột theo cơ sở dữ liệu
                String description = resultSet.getString("mota"); // Thay đổi cột theo cơ sở dữ liệu
                departments.add(new Department(id, name, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    // Fetch a department by its ID
    public Department getDepartmentById(String id) {
        Department department = null;
        String query = "SELECT * FROM phongban WHERE idpb = ?"; // Sử dụng cột idpb cho phù hợp

        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("tenpb"); // Sử dụng cột tenpb
                    String description = resultSet.getString("mota"); // Sử dụng cột mota
                    department = new Department(id, name, description);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    // Insert a new department into the database
    public void addDepartment(Department department) {
        String query = "INSERT INTO phongban (idpb, tenpb, mota) VALUES (?, ?, ?)"; // Sử dụng cột đúng trong câu lệnh SQL

        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(query)) {
            preparedStatement.setString(1, department.getId());
            preparedStatement.setString(2, department.getName());
            preparedStatement.setString(3, department.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing department
    public void updateDepartment(Department department) {
        String query = "UPDATE phongban SET tenpb = ?, mota = ? WHERE idpb = ?"; // Sử dụng đúng cột trong câu lệnh SQL

        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(query)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setString(2, department.getDescription());
            preparedStatement.setString(3, department.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a department by its ID
    public void deleteDepartment(String id) {
        String query = "DELETE FROM phongban WHERE idpb = ?"; // Sử dụng đúng cột trong câu lệnh SQL

        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}