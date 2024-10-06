package com.skincare;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SkincareApplication extends Application {

    private ProductDAO productDAO;

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
        Label categoryLabel = new Label("Product Category:");
        TextField categoryField = new TextField();
        Button addButton = new Button("Add Product");
        Button viewButton = new Button("View Products");
        Button deleteButton = new Button("Delete Product");

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(dateLabel, 0, 1);
        grid.add(dateField, 1, 1);
        grid.add(categoryLabel, 0, 2);
        grid.add(categoryField, 1, 2);
        grid.add(addButton, 0, 3);
        grid.add(viewButton, 1, 3);
        grid.add(deleteButton, 0, 4);

        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String expirationDate = dateField.getText();
            String category = categoryField.getText();

            try {
                productDAO.addProduct(new Product(name, expirationDate, category));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Product added successfully!");
                alert.showAndWait();
                nameField.clear();
                dateField.clear();
                categoryField.clear();
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error adding product: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        viewButton.setOnAction(e -> {
            List<Product> products = productDAO.getAllProducts();
            StringBuilder productList = new StringBuilder("Products in the database:\n");

            for (Product product : products) {
                productList.append("ID: ").append(product.getId())
                        .append(", Name: ").append(product.getName())
                        .append(", Expiration Date: ").append(product.getExpirationDate())
                        .append(", Category: ").append(product.getCategory()).append("\n");
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION, productList.toString());
            alert.showAndWait();
        });

        deleteButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Delete Product");
            dialog.setHeaderText("Enter the ID of the product to delete:");

            dialog.showAndWait().ifPresent(id -> {
                try {
                    productDAO.deleteProduct(Integer.parseInt(id));
                } catch (SQLException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error deleting product: " + ex.getMessage());
                    alert.showAndWait();
                } catch (NumberFormatException nfe) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid ID format.");
                    alert.showAndWait();
                }
            });
        });

        primaryStage.setTitle("Skincare Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}