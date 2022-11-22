package ru.croc.task9.core;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import ru.croc.task9.core.abstractions.PasswordHasher;

public class PasswordCrackingRunner implements Callable<String> {

	private final String hash;
	private final PasswordIterator passwordIterator;
	private final PasswordHasher passwordHasher;
	private final AtomicBoolean isPasswordFound;

	public PasswordCrackingRunner(String hash, PasswordHasher passwordHasher,
		PasswordIterator iterator,
		AtomicBoolean isPasswordFound) {
		this.hash = hash;
		this.passwordHasher = passwordHasher;
		this.passwordIterator = iterator;
		this.isPasswordFound = isPasswordFound;
	}

	@Override
	public String call() {
		while (!isPasswordFound.get() && passwordIterator.hasNext()) {
			String password = passwordIterator.next();
			if (passwordHasher.hashPassword(password).equals(hash)) {
				isPasswordFound.set(true);
				return password;
			}
		}

		return null;
	}
}
