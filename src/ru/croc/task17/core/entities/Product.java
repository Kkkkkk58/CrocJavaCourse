package ru.croc.task17.core.entities;

import java.util.Objects;

public class Product {
	private final String vendorCode;
	private String name;
	private double price;

	public Product(String vendorCode, String name, double price) {
		this.vendorCode = vendorCode;
		this.name = name;
		this.price = price;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Product other
			&& vendorCode.equalsIgnoreCase(other.vendorCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vendorCode);
	}
}
