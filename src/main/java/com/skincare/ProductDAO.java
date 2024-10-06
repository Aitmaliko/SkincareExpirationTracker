package com.skincare;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection conn;

    public ProductDAO(Connection conn) {
        this.conn = conn;
        createTable();
    }

    // Create the products table if it doesn't exist
    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS products (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "expiration_date TEXT NOT NULL," +
                "category TEXT NOT NULL," +
                "date_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    // Add a product to the database
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, expiration_date, category) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getExpirationDate());
            pstmt.setString(3, product.getCategory());
            pstmt.executeUpdate();
        }
    }

    // Retrieve all products from the database
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("expiration_date"),
                        rs.getString("category")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving products: " + e.getMessage());
        }
        return products;
    }

    // Delete a product from the database by ID
    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
