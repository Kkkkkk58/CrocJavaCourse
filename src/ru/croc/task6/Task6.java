package ru.croc.task6;

import org.jetbrains.annotations.NotNull;
import ru.croc.task6.core.JavaCommentRemover;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task6 {

	/***
	 * An entrypoint for interaction with JavaCommentRemover
	 * @param args The name of the file to remove comments from
	 * @throws IOException In case I/O operations fail
	 */
	public static void main(String @NotNull [] args) throws IOException {
		if (args.length != 1) {
			throw new IllegalArgumentException("Invalid count of input parameters");
		}

		String source = getSource(args[0]);
		String noComments = new JavaCommentRemover().removeComments(source);
		System.out.println(noComments);
	}

	private static @NotNull String getSource(String path) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			return sb.toString();
		}
	}

}
