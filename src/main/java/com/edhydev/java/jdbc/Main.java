package com.edhydev.java.jdbc;

import com.edhydev.java.jdbc.models.Product;
import com.edhydev.java.jdbc.repository.Repository;
import com.edhydev.java.jdbc.repository.impl.ProductRepositoryImpl;
import com.edhydev.java.jdbc.utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = ConexionBaseDatos.getInstance()) {
            Repository<Product> repository = new ProductRepositoryImpl();
            repository.findAll().forEach(System.out::println);
        }
    }
}
