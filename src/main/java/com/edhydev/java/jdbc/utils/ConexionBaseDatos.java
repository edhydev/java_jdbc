package com.edhydev.java.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306?curso_java&serverTimezone=UTC";
    private static final String USER = "edgar";
    private static final String PWD = "1234";
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PWD);
        }
        return connection;
    }
}
