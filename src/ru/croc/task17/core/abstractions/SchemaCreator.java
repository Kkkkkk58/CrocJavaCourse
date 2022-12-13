package ru.croc.task17.core.abstractions;

import java.sql.Connection;

public interface SchemaCreator {
	void createSchema(Connection connection);
}
