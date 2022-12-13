package ru.croc.task17.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import ru.croc.task17.core.abstractions.SchemaCreator;

public class ShopSchemaCreator implements SchemaCreator {


	@Override
	public void createSchema(Connection connection) {
		createProductsTable(connection);
		createOrdersTable(connection);
		createOrderProductsTable(connection);
	}

	private static void createProductsTable(Connection connection) {
		executeQuery(connection, """
			DROP TABLE IF EXISTS OrderProducts;
			DROP TABLE IF EXISTS Products;
			CREATE TABLE Products
			(id IDENTITY NOT NULL PRIMARY KEY,
			vendor_code VARCHAR(255) UNIQUE NOT NULL,
			name VARCHAR(255) NOT NULL,
			price DECIMAL NOT NULL)""");
	}

	private static void createOrdersTable(Connection connection) {
		executeQuery(connection, """
			DROP TABLE IF EXISTS Orders;
			CREATE TABLE Orders
			(id IDENTITY NOT NULL PRIMARY KEY,
			buyer_name VARCHAR(255) NOT NULL)
			""");
	}

	private static void createOrderProductsTable(Connection connection) {
		executeQuery(connection, """
			CREATE TABLE OrderProducts
			(id IDENTITY NOT NULL PRIMARY KEY,
			order_id INTEGER NOT NULL,
			product_id INTEGER NOT NULL,
			FOREIGN KEY (order_id) REFERENCES Orders(id),
			FOREIGN KEY (product_id) REFERENCES Products(id))
			""");
	}

	private static void executeQuery(Connection connection, String sql) {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
