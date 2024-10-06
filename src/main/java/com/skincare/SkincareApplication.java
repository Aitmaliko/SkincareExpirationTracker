package com.skincare;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SkincareApplication extends Application {

    private ProductDAO productDAO;
    private ListView<Product> productListView;

    @Override
    public void start(Stage primaryStage) {
        // Initialize database connection and ProductDAO
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.out.println("Failed to establish connection to the database.");
            return;
        }
        productDAO = new ProductDAO(connection);

        // Create the form for adding products
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 400, 300);

        Label nameLabel = new Label("Product Name:");
        TextField nameField = new TextField();
        Label dateLabel = new Label("Expiration Date (YYYY-MM-DD):");
        TextField dateField = new TextField();
        Button addButton = new Button("Add Product");
        Button viewButton = new Button("View Products");
        Button deleteButton = new Button("Delete Product");

        productListView = new ListView<>();
        productListView.setPrefHeight(150);

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(dateLabel, 0, 1);
        grid.add(dateField, 1, 1);
        grid.add(addButton, 0, 2);
        grid.add(viewButton, 1, 2);
        grid.add(deleteButton, 0, 3);
        grid.add(productListView, 0, 4, 2, 1);

        addButton.setOnAction(e -> {
            String name = nameField.getText();
            LocalDate expirationDate = LocalDate.parse(dateField.getText());

            try {
                productDAO.addProduct(new Product(name, expirationDate));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Product added successfully!");
                alert.showAndWait();
                nameField.clear();
                dateField.clear();
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error adding product: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        viewButton.setOnAction(e -> {
            List<Product> products = productDAO.getAllProducts();
            productListView.getItems().clear(); // Clear the existing items in the list view
            productListView.getItems().addAll(products); // Add products to the list view
        });

        deleteButton.setOnAction(e -> {
            Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                try {
                    productDAO.deleteProduct(selectedProduct.getId());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Product deleted successfully!");
                    alert.showAndWait();
                    viewButton.fire(); // Refresh the product list
                } catch (SQLException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error deleting product: " + ex.getMessage());
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a product to delete.");
                alert.showAndWait();
            }
        });

        primaryStage.setTitle("Skincare Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
