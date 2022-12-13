package ru.croc.task18.data_access;

import ru.croc.task17.core.entities.Order;
import ru.croc.task17.core.entities.Product;
import ru.croc.task18.exceptions.ShopException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OrderProductsDaoImpl implements OrderProductsDao {

	private final Connection connection;
	public OrderProductsDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Product findProduct(String productCode) {
		String sql = """
			SELECT * FROM Products
			WHERE vendor_code = ?
			""";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, productCode);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.first()) {
					String productName = resultSet.getString("name");
					double price = resultSet.getDouble("price");
					return new Product(productCode, productName, price);
				}
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Product createProduct(Product product) throws ShopException {
		throwIfProductExists(product.getVendorCode());
		String sql = """
			INSERT INTO Products(vendor_code, name, price)
			VALUES (?, ?, ?)
			""";
		try (PreparedStatement statement = connection.prepareStatement(sql,
			Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, product.getVendorCode());
			statement.setString(2, product.getName());
			statement.setDouble(3, product.getPrice());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return product;
	}

	@Override
	public Product updateProduct(Product product) throws ShopException {
		throwIfProductNotFound(product.getVendorCode());
		String sql = """
			UPDATE Products
			SET name = ?, price = ?
			WHERE vendor_code = ?
			""";
		try (PreparedStatement statement = connection.prepareStatement(sql,
			Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, product.getName());
			statement.setDouble(2, product.getPrice());
			statement.setString(3, product.getVendorCode());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return product;
	}

	@Override
	public void deleteProduct(String productCode) throws ShopException {
		throwIfProductNotFound(productCode);
		int productId = getProductId(productCode);
		String sql = """
			DELETE FROM OrderProducts WHERE product_id = ?;
			DELETE FROM Products WHERE id = ?;
			""";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, productId);
			statement.setInt(2, productId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Order createOrder(String userLogin, List<Product> products) throws ShopException {
		for (Product product : products) {
			throwIfProductNotFound(product.getVendorCode());
		}

		int orderId = addOrderToTable(userLogin);
		String sql = """
			INSERT INTO OrderProducts(order_id, product_id)
			VALUES (?, ?)
			""";
		for (Product product : products) {
			int productId = getProductId(product.getVendorCode());
			try (PreparedStatement statement = connection.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {
				statement.setInt(1, orderId);
				statement.setInt(2, productId);
				statement.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return new Order(orderId, userLogin, products);
	}

	private int addOrderToTable(String userLogin) {
		String sql = """
			INSERT INTO Orders(buyer_login)
			VALUES (?)
			""";
		try (PreparedStatement statement = connection.prepareStatement(sql,
			Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, userLogin);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return getLastOrderByUser(userLogin);
	}

	private int getLastOrderByUser(String userLogin) {
		String sql;
		sql = """
			SELECT id FROM Orders
			WHERE buyer_login = ?
			""";
		try (PreparedStatement statement = connection.prepareStatement(sql,
			Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, userLogin);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.last()) {
					return resultSet.getInt(1);
				}
				throw new SQLException("Can't get id of user");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private int getProductId(String productCode) {
		String sql = """
			SELECT id FROM Products
			WHERE vendor_code = ?
			""";
		try (PreparedStatement statement = connection.prepareStatement(sql,
			Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, productCode);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.first()) {
					return resultSet.getInt(1);
				}
				throw new SQLException("Can't get id of product");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void throwIfProductExists(String vendorCode) throws ShopException {
		if (getProductCount(vendorCode) != 0) {
			throw ShopException.ProductAlreadyExists(vendorCode);
		}
	}

	private void throwIfProductNotFound(String vendorCode) throws ShopException {
		if (getProductCount(vendorCode) == 0) {
			throw ShopException.ProductNotFound(vendorCode);
		}
	}

	private int getProductCount(String vendorCode) {
		String sql = """
			SELECT COUNT(*) FROM Products
			WHERE vendor_code = ?
			""";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, vendorCode);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.first()) {
					return resultSet.getInt(1);
				}
				throw new SQLException("Can't get count of products");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
