package ru.croc.task17.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.stream.StreamSupport;
import ru.croc.task17.core.abstractions.DbSeeder;
import ru.croc.task17.core.entities.Order;
import ru.croc.task17.core.entities.Product;

public class ShopDbSeeder implements DbSeeder<Order> {


	@Override
	public void seedDb(Connection connection, Iterable<Order> seeding) {
		seedProducts(connection, seeding);
		for (Order order : seeding) {
			seedOrderData(connection, order);

			Iterable<Product> orderProducts = order.getProducts();
			for (Product product : orderProducts) {
				seedOrderProducts(connection, order, product);
			}
		}
	}

	private static void seedProducts(Connection connection, Iterable<Order> seeding) {
		var products = StreamSupport.stream(seeding.spliterator(), false)
			.map(Order::getProducts)
			.flatMap(Collection::stream)
			.distinct()
			.toList();

		String sql = """
			INSERT INTO Products(vendor_code, name, price)
			VALUES( ? , ? , ? )
			""";
		for (Product product : products) {
			try (PreparedStatement statement = connection.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, product.getVendorCode());
				statement.setString(2, product.getName());
				statement.setDouble(3, product.getPrice());
				statement.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static void seedOrderData(Connection connection, Order order) {
		String sql = """
			MERGE INTO Orders(id, buyer_login)
			KEY(id)
			VALUES(?, ?)
			""";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, order.getId());
			stmt.setString(2, order.getBuyerLogin());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private static void seedOrderProducts(Connection connection, Order order, Product product) {
		String sql = """
			SELECT * FROM Products WHERE vendor_code = ?
			""";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, product.getVendorCode());
			fillOrderProductsData(connection, order, statement);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private static void fillOrderProductsData(Connection connection, Order order,
		PreparedStatement statement)
		throws SQLException {
		try (ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.first()) {
				int productId = resultSet.getInt("id");
				String sql = """
					INSERT INTO OrderProducts(order_id, product_id)
					VALUES( ? , ? )
					""";
				try (PreparedStatement stmt = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS)) {
					stmt.setInt(1, order.getId());
					stmt.setInt(2, productId);
					stmt.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
