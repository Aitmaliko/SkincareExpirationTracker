# Skincare Expiration Tracker

## Overview

Skincare Expiration Tracker is a JavaFX application that helps users manage their skincare products by tracking their expiration dates and categories. Users can add new products, view existing products, and delete products from their inventory. The application uses a SQLite database to store product information.

## Features

- **Add Product**: Easily add new skincare products with their name, expiration date, and category.
- **View Products**: View a list of all skincare products stored in the database.
- **Delete Product**: Remove products from your inventory.
- **Database Connection**: Utilizes SQLite for storing product data, ensuring persistence across sessions.

## Technologies Used

- Java
- JavaFX
- SQLite

## Prerequisites

To run this application, ensure you have the following installed:

- Java Development Kit (JDK) 11 or later
- SQLite JDBC Driver (download from [here](https://bitbucket.org/xerial/sqlite-jdbc/downloads/))
- JavaFX SDK (download from [here](https://gluonhq.com/products/javafx/))

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/yourusername/SkincareExpirationTracker.git
cd SkincareExpirationTracker
```
## Setup the Database
Before running the application, ensure that the SQLite JDBC driver is correctly referenced in your project.

Create a SQLite database file named skincare.db in the root directory of the project.
The application will automatically create a products table if it doesn't exist.

## Compile and Run
```bash
javac -d out --module-path path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml src/main/java/com/skincare/*.java
java --module-path path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp "out:path/to/sqlite-jdbc-<version>.jar" com.skincare.SkincareApplication
```
## Usage
Upon launching the application, you will see the main interface with options to add, view, or delete skincare products.

- Add Product: Enter the product name, expiration date (YYYY-MM-DD), and category, then click "Add Product."
- View Products: Click "View Products" to display all stored products.
- Delete Product: Enter the ID of the product you want to delete and confirm the deletion.
## Acknowledgements
Thanks to the JavaFX community for their support.
Special thanks to SQLite for providing a reliable database solution.
