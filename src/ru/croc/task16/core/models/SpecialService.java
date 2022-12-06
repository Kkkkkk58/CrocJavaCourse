package ru.croc.task16.core.models;

import java.util.Objects;

public record SpecialService(String name) {

	@Override
	public boolean equals(Object o) {
		return o instanceof SpecialService other
			&& name.equalsIgnoreCase(other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
