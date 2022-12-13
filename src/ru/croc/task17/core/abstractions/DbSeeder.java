package ru.croc.task17.core.abstractions;

import java.sql.Connection;

public interface DbSeeder<T> {

	void seedDb(Connection connection, Iterable<T> seeding);
}
