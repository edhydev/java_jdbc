package com.edhydev.java.jdbc;

import com.edhydev.java.jdbc.models.Product;
import com.edhydev.java.jdbc.repository.Repository;
import com.edhydev.java.jdbc.repository.impl.ProductRepositoryImpl;
import com.edhydev.java.jdbc.utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = ConexionBaseDatos.getInstance()) {
            Repository<Product> repository = new ProductRepositoryImpl();

            System.out.println("========== FIND ALL ==========");
            repository.findAll().forEach(System.out::println);
            System.out.println();

            System.out.println("========== FIND BY ID ==========");
            System.out.println(repository.findById(1L));
            System.out.println();

            System.out.println("========== SAVE - CREAR ==========");
            repository.save(new Product("coca cola 3L", 33.5, new Date()));
            System.out.println();

            System.out.println("========== SAVE - ACTUALIZAR ==========");
            Product p = repository.findById(1L);
            p.setName("coca cola 2.5L");
            repository.save(p);
            System.out.println();

            System.out.println("========== FIND ALL ==========");
            repository.findAll().forEach(System.out::println);
            System.out.println();
        }
    }
}
