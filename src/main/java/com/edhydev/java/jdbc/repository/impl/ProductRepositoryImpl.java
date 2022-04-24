package com.edhydev.java.jdbc.repository.impl;

import com.edhydev.java.jdbc.models.Category;
import com.edhydev.java.jdbc.models.Product;
import com.edhydev.java.jdbc.repository.Repository;
import com.edhydev.java.jdbc.utils.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements Repository<Product> {

    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getConnection();
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String SQL = "SELECT p.id, p.name, p.price, p.register_date, p.category_id, c.name as category " +
                "FROM product p " +
                "INNER JOIN category c ON p.category_id = c.id";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {
            while (resultSet.next()) {
                Product p = getProduct(resultSet);
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findById(Long id) {
        Product product = null;
        String SQL = "SELECT p.id, p.name, p.price, p.register_date, p.category_id, c.name as category " +
                "FROM product p " +
                "INNER JOIN category c ON p.category_id = c.id " +
                "WHERE p.id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    product = getProduct(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void save(Product product) {
        String SQL;
        if (product.getId() != null && product.getId() > 0) {
            SQL = "UPDATE product SET name = ?, price = ?, category_id = ? WHERE id = ?";
        } else {
            SQL = "INSERT INTO product SET name = ?, price = ?, category_id = ?, register_date = ?";
        }
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setLong(3, product.getCategory().getId());

            if (product.getId() != null && product.getId() > 0) {
                stmt.setLong(4, product.getId());
            } else {
                stmt.setDate(4, new Date(product.getRegisterDate().getTime()));
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String SQL = "DELETE FROM product WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        Product p = new Product();
        p.setId(resultSet.getLong("id"));
        p.setName(resultSet.getString("name"));
        p.setPrice(resultSet.getDouble("price"));
        p.setRegisterDate(resultSet.getDate("register_date"));
        p.setCategory(new Category());
        p.getCategory().setId(resultSet.getLong("category_id"));
        p.getCategory().setName(resultSet.getString("category"));
        return p;
    }
}
