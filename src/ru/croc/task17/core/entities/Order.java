package ru.croc.task17.core.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Order {

	private final int id;
	private final String buyerLogin;
	private final List<Product> products;

	public Order(int id, String buyerLogin, Collection<Product> products) {
		if (id < 0) {
			throw new IllegalArgumentException("Negative id");
		}
		this.id = id;
		this.buyerLogin = buyerLogin;
		this.products = new ArrayList<>(products);
	}

	public int getId() {
		return id;
	}

	public String getBuyerLogin() {
		return buyerLogin;
	}

	public Collection<Product> getProducts() {
		return Collections.unmodifiableCollection(products);
	}

	public Product addProduct(Product product) {
		products.add(product);
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
