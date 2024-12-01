package com.qlpb.model.dao;

import com.qlpb.model.bean.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private static final String DATABASE_HOST = "jdbc:mysql://localhost:3306/btad";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "12345Thanh";

    private Connection databaseConnection;

    // Constructor: Kết nối cơ sở dữ liệu
    public EmployeeDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(DATABASE_HOST, DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch (Exception e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

     public Employee getEmployeeById(String id){
        String sql = "SELECT * FROM nhanvien WHERE idnv =?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Employee(resultSet.getString("idnv"), resultSet.getString("hoten"),
                        resultSet.getString("idpb"), resultSet.getString("diachi"));
            }
        } catch (SQLException e) {
            System.err.println("Failed to get employee by ID.");
            e.printStackTrace();
        }
        return null;
     }
    // Thêm nhân viên
    public boolean addEmployee(Employee employee) {
        String sql = "INSERT INTO nhanvien (idnv, hoten, idpb, diachi) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getDepartmentID());
            preparedStatement.setString(4, employee.getAddress());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Failed to add employee.");
            e.printStackTrace();
            return false;
        }
    }

    // Sửa nhân viên
    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE nhanvien SET hoten = ?, idpb = ?, diachi = ? WHERE idnv = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getDepartmentID());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Failed to update employee.");
            e.printStackTrace();
            return false;
        }
    }

    // Xóa nhân viên
    public boolean deleteEmployee(String id) {
        String sql = "DELETE FROM nhanvien WHERE idnv = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Failed to delete employee.");
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách nhân viên theo phòng ban
    public List<Employee> getEmployeesByDepartment(String departmentID) {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien WHERE idpb = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, departmentID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getString("idnv"),
                        resultSet.getString("hoten"),
                        resultSet.getString("idpb"),
                        resultSet.getString("diachi")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Failed to get employees by department.");
            e.printStackTrace();
        }
        return employees;
    }

    // Tìm kiếm nhân viên theo tên
    public List<Employee> searchEmployeesByName(String name) {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien WHERE hoten LIKE ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getString("idnv"),
                        resultSet.getString("hoten"),
                        resultSet.getString("idpb"),
                        resultSet.getString("diachi")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Failed to search employees by name.");
            e.printStackTrace();
        }
        return employees;
    }

}