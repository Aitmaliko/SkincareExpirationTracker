# Skincare Expiration Tracker

![banner](assets/Banner.png)

![Java version](https://img.shields.io/badge/Python%20version-3.10%2B-lightgrey)
![GitHub last commit](https://img.shields.io/github/last-commit/Aitmaliko/SkincareExpirationTracker)
![GitHub repo size](https://img.shields.io/github/repo-size/Aitmaliko/SkincareExpirationTracker)
![License](https://img.shields.io/badge/License-MIT-green)

## Overview

Skincare Expiration Tracker is a JavaFX application that helps users manage their skincare products by tracking their expiration dates and categories. Users can add new products, view existing products, and delete products from their inventory. The application uses a SQLite database to store product information.

## Table of Contents
- [Talk to your PDF](#talk-to-your-pdf)
  - [Overview](#overview)
  - [Authors](#authors)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
  - [Tech Stack](#tech-stack)
  - [How RAG Works](#how-rag-works)
  - [How the app Works](#how-the-app-works)
      - [Step 1: File Upload](#step-1-file-upload)
      - [Step 2: Pre-run Service](#step-2-pre-run-service)
      - [Step 3: Intent Service](#step-3-intent-service)
      - [Step 4: Information Retrieval Service](#step-4-information-retrieval-service)
      - [Step 5: Response Service](#step-5-response-service)
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
