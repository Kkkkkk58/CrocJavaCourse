package ru.croc.task17.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ru.croc.task17.core.abstractions.CsvReader;
import ru.croc.task17.core.entities.Order;
import ru.croc.task17.core.entities.Product;

public class ShopCsvReader implements CsvReader<Order> {

	@Override
	public Iterable<Order> getFromCsv(BufferedReader csvStream) throws IOException {
		Set<Product> productRegistry = new HashSet<>();
		Set<Order> orderRegistry = new HashSet<>();

		String line = csvStream.readLine();
		while (line != null) {
			String[] input = line.split(",");
			getEntities(productRegistry, orderRegistry, input);
			line = csvStream.readLine();
		}

		return orderRegistry;
	}

	private static void getEntities(
		Set<Product> productRegistry,
		Set<Order> orderRegistry,
		String[] input) {
		int orderId = Integer.parseInt(input[0]);
		String buyerName = input[1];
		String vendorCode = input[2];
		String productName = input[3];
		double price = Double.parseDouble(input[4]);
		var product = new Product(vendorCode, productName, price);
		productRegistry.add(product);
		var order = orderRegistry.stream()
			.filter(o -> o.getId() == orderId)
			.findFirst();
		if (order.isEmpty()) {
			orderRegistry.add(new Order(orderId, buyerName, new ArrayList<>(
				List.of(product)
			)));
		} else {
			order.get().addProduct(product);
		}
	}
}
