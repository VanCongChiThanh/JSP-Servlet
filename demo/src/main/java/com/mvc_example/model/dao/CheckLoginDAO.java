
package com.mvc_example.model.dao;

import com.mvc_example.model.bean.Wife;

import java.sql.*;
import java.util.ArrayList;

    public class CheckLoginDAO {
        private static final String DB_URL = "jdbc:mysql://localhost:3306/login";
        private static final String DB_USER = "root";
        private static final String DB_PASSWORD = "12345Thanh";

        public boolean isExistingUser(String username, String password) {
            boolean isUserExist = false;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                Statement statement = connection.createStatement();

                String sqlQuery = "SELECT * FROM account WHERE username='" + username + "' AND password='" + password + "'";
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                if (resultSet.next()) {
                    isUserExist = true;
                }

                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return isUserExist;
        }

        public ArrayList<Wife> getWifeList(String usernameHusband) {
            ArrayList<Wife> wifeList = new ArrayList<>();

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                Statement statement = connection.createStatement();

                String sqlQuery = "SELECT * FROM wife WHERE username_husband='" + usernameHusband + "'";
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {
                    Wife wife = new Wife();
                    wife.setName(resultSet.getString("name"));
                    wife.setAddress(resultSet.getString("address"));
                    wife.setAlive(resultSet.getBoolean("alive"));

                    wifeList.add(wife);
                }
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return wifeList;
        }
    }