package com.edhydev.java.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/curso_java?serverTimezone=America/Mexico_City";
    private static final String USER = "edgar";
    private static final String PWD = "1234";

    public static Connection getInstance() throws SQLException {
        return DriverManager.getConnection(URL, USER, PWD);
    }
}
