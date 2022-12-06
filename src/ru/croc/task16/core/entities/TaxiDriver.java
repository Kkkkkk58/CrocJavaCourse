package ru.croc.task16.core.entities;

import java.util.UUID;

public class TaxiDriver {

	private final UUID id;
	private final String name;

	public TaxiDriver(String name) {
		id = UUID.randomUUID();
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("[%s]-%s", id, name);
	}
}
