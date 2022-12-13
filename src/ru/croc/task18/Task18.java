package ru.croc.task18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import ru.croc.task17.core.entities.Product;
import ru.croc.task18.data_access.OrderProductsDao;
import ru.croc.task18.data_access.OrderProductsDaoImpl;

public class Task18 {
	private static final String CONNECTION_STRING = "jdbc:h2:tcp://localhost/~/test";
	private static final String USER = "sa";
	private static final String PASSWORD = "";

	public static void main(String[] args) throws Exception {
		try (Connection connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD)) {
			OrderProductsDao orderProductsDao = new OrderProductsDaoImpl(connection);

			var product = orderProductsDao.createProduct(new Product("Т19", "Мотоцикл Урал", 1200000));
			var retrieved = orderProductsDao.findProduct(product.getVendorCode());
			System.out.println("Retrieved product equals original: " + product.equals(retrieved));

			product.setPrice(100);
			product = orderProductsDao.updateProduct(product);

			orderProductsDao.deleteProduct(product.getVendorCode());
			retrieved = orderProductsDao.findProduct(product.getVendorCode());
			System.out.println("Deleted product is not found: " + (retrieved == null));

			// TODO fix autoincrement field and test orders
		}
	}

	private static List<Product> getProducts(OrderProductsDao orderProductsDao) {
		return new ArrayList<>(
			List.of(
				orderProductsDao.findProduct("Т1"),
				orderProductsDao.findProduct("Т3")
			)
		);
	}
}
