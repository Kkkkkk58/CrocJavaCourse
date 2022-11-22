package ru.croc.task9;

import org.jetbrains.annotations.NotNull;
import ru.croc.task9.core.MD5PasswordHasher;
import ru.croc.task9.core.MultiThreadPasswordCracker;
import ru.croc.task9.core.abstractions.PasswordCracker;

public class Task9 {

	private static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	/**
	 * Program for getting a string password by its MD5 hash
	 *
	 * @param args The first argument is a number of threads to use, the second one is MD5 hash of
	 *             the password
	 */

	// Test input: expected password - mkx, hash - eae732cc846988cdfd4df477834f6728
	public static void main(String @NotNull [] args) {
		if (args.length != 2) {
			throw new IllegalArgumentException("Invalid number of arguments");
		}

		int threadNumber = Integer.parseInt(args[0]);
		String hash = args[1].toUpperCase();

		PasswordCracker passwordCracker = new MultiThreadPasswordCracker(
			new MD5PasswordHasher(), threadNumber);

		String password = passwordCracker
			.getPassword(3, alphabet, hash);

		System.out.println("Cracked password is " + password);
	}
}
