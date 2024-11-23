package com.student_management.model.dao;

import com.student_management.model.bean.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {
    private static final String DATABASE_HOST = "jdbc:mysql://localhost";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "12345Thanh";

    private Connection databaseConnection;

    public DAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(
                    DATABASE_HOST + "?user=" + DATABASE_USERNAME + "&password=" + DATABASE_PASSWORD);
        } catch (Exception e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    public boolean authenticateUser(String username, String password) {
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(
                "SELECT * FROM studentmanagement.account WHERE username = ? AND password = ?")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            System.err.println("Failed to authenticate user.");
            e.printStackTrace();
        }

        return false;
    }

    public boolean addStudent(String id, String name, int age, String faculty) {
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(
                "INSERT INTO studentmanagement.student VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, faculty);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Failed to add student.");
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();

        try (Statement statement = databaseConnection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM studentmanagement.student")) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String faculty = resultSet.getString("faculty");

                students.add(new Student(id, name, age, faculty));
            }

        } catch (SQLException e) {
            System.err.println("Failed to retrieve students.");
            e.printStackTrace();
        }

        return students;
    }

    public Student getStudentById(String id) {
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(
                "SELECT * FROM studentmanagement.student WHERE id = ?")) {

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String faculty = resultSet.getString("faculty");

                return new Student(id, name, age, faculty);
            }

        } catch (SQLException e) {
            System.err.println("Failed to retrieve student by ID.");
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateStudent(String id, String name, int age, String faculty) {
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(
                "UPDATE studentmanagement.student SET name = ?, age = ?, faculty = ? WHERE id = ?")) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, faculty);
            preparedStatement.setString(4, id);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Failed to update student.");
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteStudent(String id) {
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(
                "DELETE FROM studentmanagement.student WHERE id = ?")) {

            preparedStatement.setString(1, id);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Failed to delete student.");
            e.printStackTrace();
        }
        return false;
    }

}