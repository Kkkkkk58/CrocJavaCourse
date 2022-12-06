package ru.croc.task16.core.models;

import java.util.Objects;

public record TaxiClass(String name) {

	@Override
	public boolean equals(Object o) {
		return o instanceof TaxiClass other
			&& name.equalsIgnoreCase(other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
