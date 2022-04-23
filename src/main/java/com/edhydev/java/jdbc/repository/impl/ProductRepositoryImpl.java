package com.edhydev.java.jdbc.repository.impl;

import com.edhydev.java.jdbc.models.Product;
import com.edhydev.java.jdbc.repository.Repository;
import com.edhydev.java.jdbc.utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements Repository<Product> {

    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getInstance();
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String SQL = "SELECT * FROM product";
        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(SQL)) {
            while (resultSet.next()) {
                Product p = new Product();
                p.setId(resultSet.getLong("id"));
                p.setName(resultSet.getString("name"));
                p.setPrice(resultSet.getDouble("price"));
                p.setRegisterDate(resultSet.getDate("register_date"));
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void delete(Long id) {

    }
}
