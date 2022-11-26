package ru.croc.task12.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.jetbrains.annotations.NotNull;
import ru.croc.task12.core.abstractions.BlackListFilter;

public class AsyncBlackListFilter implements BlackListFilter {

	private final Comparator<String> stringComparator;
	private final Integer numberOfThreads;


	public AsyncBlackListFilter(Comparator<String> stringComparator) {
		this(null, stringComparator);
	}

	public AsyncBlackListFilter(Integer numberOfThreads, Comparator<String> stringComparator) {
		this.numberOfThreads = numberOfThreads;
		this.stringComparator = stringComparator;
	}

	@Override
	public void filterComments(List<String> comments, Set<String> blackList) {
		ExecutorService threadPool = getThreadPool(comments);
		List<Future<String>> tasks = getTasks(comments, blackList, threadPool);

		try {
			for (int i = 0; i < tasks.size(); ++i) {
				comments.set(i, tasks.get(i).get());
			}
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException("An error occurred during the process", e);
		} finally {
			threadPool.shutdown();
		}
	}

	@NotNull
	private List<Future<String>> getTasks(List<String> comments, Set<String> blackList,
		ExecutorService threadPool) {
		List<Future<String>> tasks = new ArrayList<>();
		for (String comment : comments) {
			tasks.add(threadPool.submit(new CommentFilter(comment, blackList, stringComparator)));
		}
		return tasks;
	}

	@NotNull
	private ExecutorService getThreadPool(List<String> comments) {
		return Executors.newFixedThreadPool(
			Objects.requireNonNullElseGet(numberOfThreads, comments::size));
	}
}
