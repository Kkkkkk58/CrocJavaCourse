package ru.croc.task17.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import ru.croc.task17.core.abstractions.SchemaCreator;

public class ShopSchemaCreator implements SchemaCreator {

	@Override
	public void createSchema(Connection connection) {
		try (Statement statement = connection.createStatement()){
			String sql = """
				CREATE TABLE IF NOT EXISTS Products
				(id IDENTITY NOT NULL PRIMARY KEY,
				vendor_code VARCHAR(255) UNIQUE NOT NULL,
				name VARCHAR(255) NOT NULL,
				price DECIMAL NOT NULL)""";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (Statement statement = connection.createStatement()){
			String sql = """
				CREATE TABLE IF NOT EXISTS Orders
				(id IDENTITY NOT NULL PRIMARY KEY,
				buyer_name VARCHAR(255) NOT NULL)
				""";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (Statement statement = connection.createStatement()){
			String sql = """
				CREATE TABLE IF NOT EXISTS OrderProducts
				(id IDENTITY NOT NULL PRIMARY KEY,
				order_id INTEGER NOT NULL,
				product_id INTEGER NOT NULL)
				""";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
