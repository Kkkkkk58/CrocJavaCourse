package ru.croc.task17;

import ru.croc.task17.core.ShopCsvReader;
import ru.croc.task17.core.ShopDbSeeder;
import ru.croc.task17.core.ShopSchemaCreator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class Task17 {

	public static void main(String[] a)
		throws Exception {
		Connection conn = DriverManager.
			getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
		new ShopSchemaCreator().createSchema(conn);
		var orders = new ShopCsvReader().getFromCsv(new BufferedReader(new FileReader("src/ru/croc/task17/resources/data.csv")));
		new ShopDbSeeder().seedDb(conn, orders);
		conn.close();
	}
}
