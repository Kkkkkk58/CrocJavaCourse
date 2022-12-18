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

	/**
	 * An entrypoint for task17 solution example
	 * @param args first argument is the name of .csv file with data
	 * @throws Exception in case of invalid number of parameters or problems with db connection
	 */
	public static void main(String[] args)
		throws Exception {
		if (args.length != 1) {
			throw new IllegalArgumentException("Invalid number of input parameters");
		}

		final String filePath = args[0];
		try (Connection connection = DriverManager.
			getConnection(CONNECTION_STRING, USER, PASSWORD)) {
			new ShopSchemaCreator().createSchema(connection);
			BufferedReader csvStream = new BufferedReader(new FileReader(filePath));
			var orders = new ShopCsvReader().getFromCsv(csvStream);
			new ShopDbSeeder().seedDb(connection, orders);
		}
	}
}
