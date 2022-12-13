package ru.croc.task17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import ru.croc.task17.core.ShopCsvReader;
import ru.croc.task17.core.ShopDbSeeder;
import ru.croc.task17.core.ShopSchemaCreator;

public class Task17 {
	private static final String CONNECTION_STRING = "jdbc:h2:tcp://localhost/~/test";
	private static final String USER = "sa";
	private static final String PASSWORD = "";
	private static final String FILEPATH = "src/ru/croc/task17/resources/data.csv";
	public static void main(String[] a)
		throws Exception {
		try (Connection connection = DriverManager.
			getConnection(CONNECTION_STRING, USER, PASSWORD)) {
			new ShopSchemaCreator().createSchema(connection);
			BufferedReader csvStream = new BufferedReader(new FileReader(FILEPATH));
			var orders = new ShopCsvReader().getFromCsv(csvStream);
			new ShopDbSeeder().seedDb(connection, orders);
		}
	}
}
