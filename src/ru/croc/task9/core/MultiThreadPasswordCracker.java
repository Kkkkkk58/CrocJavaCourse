package ru.croc.task9.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.croc.task9.core.abstractions.PasswordCracker;
import ru.croc.task9.core.abstractions.PasswordHasher;

public class MultiThreadPasswordCracker implements PasswordCracker {

	private final PasswordHasher passwordHasher;
	private final int threadNumber;
	private final ExecutorService threadPool;
	private final AtomicBoolean isPasswordFound;

	public MultiThreadPasswordCracker(PasswordHasher passwordHasher, int threadNumber) {
		this.passwordHasher = passwordHasher;
		this.threadNumber = threadNumber;
		threadPool = Executors.newFixedThreadPool(threadNumber);
		isPasswordFound = new AtomicBoolean(false);
	}

	@Override
	public String getPassword(int passwordLength, char[] alphabet, String hash) {
		List<Future<?>> tasks = initializeTasks(passwordLength, alphabet, hash);
		try {
			return getTasksResult(tasks);
		} catch (ExecutionException | InterruptedException e) {
			throw new RuntimeException("An error occurred during the process", e);
		} finally {
			threadPool.shutdown();
		}
	}

	@Nullable
	private static String getTasksResult(List<Future<?>> tasks)
		throws InterruptedException, ExecutionException {
		for (Future<?> task : tasks) {
			String value = (String) task.get();
			if (value != null) {
				return value;
			}
		}

		return null;
	}

	@NotNull
	private List<Future<?>> initializeTasks(int passwordLength, char[] alphabet, String hash) {
		List<Future<?>> tasks = new ArrayList<>();
		var key = new char[passwordLength];
		Arrays.fill(key, alphabet[0]);

		for (char c : alphabet) {
			var newKey = key.clone();
			newKey[0] = c;
			var iterator = new PasswordIterator(newKey, alphabet, 1);
			PasswordCrackingRunner runner =
				new PasswordCrackingRunner(hash, passwordHasher, iterator, isPasswordFound);

			tasks.add(threadPool.submit(runner));
		}
		return tasks;
	}
}
