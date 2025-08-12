package com.codecool.dungeoncrawl.dao;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class JdbcDao {
    private static final Dotenv DOT_ENV = Dotenv.load();
    private static final String JDBC_URL = DOT_ENV.get("DB_URL");
    private static final String USERNAME = DOT_ENV.get("DB_USER");
    private static final String PASSWORD = DOT_ENV.get("DB_PASSWORD");

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("PostgreSQL JDBC driver not found", e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public void executeUpdate(String sql, SQLConsumer<PreparedStatement> parameterSetter) {
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            parameterSetter.accept(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Update failed", e);
        }
    }

    public void executeQuery(String sql, SQLConsumer<PreparedStatement> parameterSetter, SQLConsumer<ResultSet> resultHandler) {
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            parameterSetter.accept(statement);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultHandler.accept(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query failed", e);
        }
    }

    @FunctionalInterface
    public interface SQLConsumer<T> {
        void accept(T t) throws SQLException;
    }
}