package com.edhydev.java.jdbc.repository.impl;

import com.edhydev.java.jdbc.models.Category;
import com.edhydev.java.jdbc.repository.Repository;
import com.edhydev.java.jdbc.utils.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements Repository<Category> {
    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getConnection();
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        String SQL = "SELECT * FROM category;";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {
            while (resultSet.next()) {
                Category c = new Category();
                c.setId(resultSet.getLong("id"));
                c.setName(resultSet.getString("name"));
                categories.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category findById(Long id) {
        Category category = null;
        String SQL = "SELECT * FROM category WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    category.setId(resultSet.getLong("id"));
                    category.setName(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public void save(Category category) {
        String SQL;
        if (category.getId() != null && category.getId() > 0) {
            SQL = "UPDATE category set name = ? WHERE id = ?";
        } else {
            SQL = "INSERT INTO category SET name = ?";
        }
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setString(1, category.getName());
            if (category.getId() != null && category.getId() > 0) {
                stmt.setLong(2, category.getId());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String SQL = "DELETE FROM category WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
