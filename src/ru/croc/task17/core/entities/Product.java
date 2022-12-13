package ru.croc.task17.core.entities;

import java.util.Objects;

public record Product(String vendorCode, String name, double price) {

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
