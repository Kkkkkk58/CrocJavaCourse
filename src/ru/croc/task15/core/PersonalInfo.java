package ru.croc.task15.core;

import java.util.Objects;

public class PersonalInfo {
	private final String name;
	private final int age;

	public PersonalInfo(String name, int age) {
		if (age < 0) {
			throw new IllegalArgumentException("Negative age");
		}

		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return String.format("%s (%d)", name, age);
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof PersonalInfo other
			&& age == other.age
			&& Objects.equals(name, other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, age);
	}
}
