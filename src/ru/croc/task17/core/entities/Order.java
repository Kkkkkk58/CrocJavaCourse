package ru.croc.task17.core.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Order {
	private final int id;
	private final String buyerName;
	private final List<Product> products;

	public Order(int id, String buyerName, Collection<Product> products) {
		if (id < 0) {
			throw new IllegalArgumentException("Negative id");
		}
		this.id = id;
		this.buyerName = buyerName;
		this.products = new ArrayList<>(products);
	}

	public int getId() {
		return id;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public Collection<Product> getProducts() {
		return Collections.unmodifiableCollection(products);
	}

	public Product addProduct(Product product) {
		if (!products.add(product)) {
			throw new IllegalArgumentException("KLklkklsdskldskdsklfdskldskl");
		}
		return product;
	}
	@Override
	public boolean equals(Object o) {
		return o instanceof Order other
			&& id == other.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
