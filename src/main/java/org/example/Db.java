package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Db {

    private Db() {}

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/foodfast";
        String user = "postgres";
        String password = "postgres";

        return DriverManager.getConnection(url, user, password);
    }
}
