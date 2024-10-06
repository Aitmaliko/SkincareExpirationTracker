package com.skincare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection conn;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Constructor for ProductDAO
    public ProductDAO(Connection conn) {
        this.conn = conn;
        createTable();
        addDDayColumn();  // Add the d_day column if it doesn't exist
    }

    // Method to create the table if it doesn't exist
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS products ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name TEXT NOT NULL,"
                + " expiration_date TEXT NOT NULL,"
                + " d_day INTEGER NOT NULL"
                + ");";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    // Add the d_day column if it doesn't already exist
    public void addDDayColumn() {
        String checkColumn = "PRAGMA table_info(products)";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(checkColumn)) {

            boolean dDayColumnExists = false;
            while (rs.next()) {
                if ("d_day".equals(rs.getString("name"))) {
                    dDayColumnExists = true;
                    break;
                }
            }

            if (!dDayColumnExists) {
                String sql = "ALTER TABLE products ADD COLUMN d_day INTEGER";
                stmt.execute(sql);
                System.out.println("d_day column added successfully.");
            } else {
                System.out.println("d_day column already exists.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding d_day column: " + e.getMessage());
        }
    }

    // Add a product to the database
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products(name, expiration_date, d_day) VALUES(?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getExpirationDate().format(dateFormatter));  // Store LocalDate as String
            pstmt.setInt(3, product.getDDay());  // Store d_day as an integer
            pstmt.executeUpdate();
        }
    }

    // Get all products from the database, ordered by d_day in ascending order
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, expiration_date, d_day FROM products ORDER BY d_day ASC";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                // Convert from milliseconds to LocalDate
                long millis = rs.getLong("expiration_date");
                LocalDate expirationDate = LocalDate.ofEpochDay(millis / (24 * 60 * 60 * 1000)); // Convert to days
                int dDay = rs.getInt("d_day");
                products.add(new Product(id, name, expirationDate, dDay));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving products: " + e.getMessage());
        }
        return products;
    }


    // Delete a product by ID
    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
