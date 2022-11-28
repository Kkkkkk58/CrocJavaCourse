package ru.croc.task13.core.entities;

import java.util.Objects;

public record Film(long id, String name) {
	@Override
	public boolean equals(Object o) {
		return o instanceof Film film
			&& id == film.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
