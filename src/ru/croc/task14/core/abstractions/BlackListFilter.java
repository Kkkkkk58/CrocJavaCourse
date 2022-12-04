package ru.croc.task14.core.abstractions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public interface BlackListFilter<T> {

	/**
	 * From the given collection of comments removes ones that matches predicate.
	 *
	 * @param comments     collection of comments; every comment is a sequence of words, separated
	 *                     by spaces, punctuation or line breaks
	 * @param isBadComment predicate determining whether the comment should be purged
	 */
	default Iterable<T> filterComments(Iterable<T> comments, Predicate<T> isBadComment) {
		List<T> filtered = new ArrayList<>();
		for (T comment : comments) {
			if (!isBadComment.test(comment)) {
				filtered.add(comment);
			}
		}

		return filtered;
	}
}
