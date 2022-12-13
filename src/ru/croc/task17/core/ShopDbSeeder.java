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
		var products = StreamSupport.stream(seeding.spliterator(), false)
			.map(Order::getProducts)
			.flatMap(Collection::stream)
			.distinct()
			.toList();

		String sql = """
					INSERT INTO Products(vendor_code, name, price)
					VALUES( ? , ? , ? )
					""";
		for (Product product: products) {
			try (PreparedStatement statement = connection.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, product.vendorCode());
				statement.setString(2, product.name());
				statement.setDouble(3, product.price());
				statement.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		for (Order order : seeding) {
			sql = """
							MERGE INTO Orders(id, buyer_name)
							KEY(id)
							VALUES(?, ?)
							""";
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setInt(1, order.getId());
				stmt.setString(2, order.getBuyerName());
				stmt.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}


			Iterable<Product> orderProducts = order.getProducts();
			for (Product product: orderProducts) {
				sql = """
				SELECT * FROM Products WHERE vendor_code = ?
				""";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, product.vendorCode());
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.first()) {
						int productId = resultSet.getInt("id");
						sql = """
							INSERT INTO OrderProducts(order_id, product_id)
							VALUES( ? , ? )
							""";
						try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
							stmt.setInt(1, order.getId());
							stmt.setInt(2, productId);
							stmt.executeUpdate();
						} catch (SQLException e) {
							throw new RuntimeException(e);
						}
					}
				}
			}
			catch (SQLException e) {
				throw new RuntimeException(e);
			}
			}
		}
	}
}
