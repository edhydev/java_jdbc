package com.edhydev.java.jdbc.repository.impl;

import com.edhydev.java.jdbc.models.Product;
import com.edhydev.java.jdbc.repository.Repository;
import com.edhydev.java.jdbc.utils.ConexionBaseDatos;

import java.sql.*;
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
                Product p = getProduct(resultSet);
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        Product p = new Product();
        p.setId(resultSet.getLong("id"));
        p.setName(resultSet.getString("name"));
        p.setPrice(resultSet.getDouble("price"));
        p.setRegisterDate(resultSet.getDate("register_date"));
        return p;
    }

    @Override
    public Product findById(Long id) {
        Product product = null;
        String SQL = "SELECT * FROM product WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(SQL);) {
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
            SQL = "UPDATE product SET name = ?, price = ? WHERE id = ?";
        } else {
            SQL = "INSERT INTO product SET name = ?, price = ?, register_date = ?";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(SQL)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            if (product.getId() != null && product.getId() > 0) {
                stmt.setLong(3, product.getId());
            } else {
                stmt.setDate(3, new Date(product.getRegisterDate().getTime()));
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String SQL = "DELETE FROM product WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(SQL)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
