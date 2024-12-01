package com.qlpb.model.dao;

import java.sql.*;

public class AuthDAO {
    private static final String DATABASE_HOST = "jdbc:mysql://localhost";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "12345Thanh";

    private Connection databaseConnection;

    public AuthDAO() {
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
                "SELECT * FROM btad.admin WHERE username = ? AND password = ?")) {

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
}