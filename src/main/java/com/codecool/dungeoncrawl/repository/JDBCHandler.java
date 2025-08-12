package com.codecool.dungeoncrawl.repository;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;

public class JDBCHandler {

    private static final Dotenv DOT_ENV = Dotenv.load();
    private static final String JDBC_URL = DOT_ENV.get("DB_URL");
    private static final String USERNAME = DOT_ENV.get("DB_USER");
    private static final String PASSWORD = DOT_ENV.get("DB_PASSWORD");

    public static ResultSet runSql(String sqlCommand) {
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            loadDriver();
            connection = createConnection();
            Statement statement = createStatement(connection);
            resultSet = statement.executeQuery(sqlCommand);

        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Connection to database failed: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return resultSet;
    }

    private static void loadDriver() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    private static Statement createStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}