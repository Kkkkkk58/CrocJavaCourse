package ru.croc.task12.core;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.Callable;

public class CommentFilter implements Callable<String> {

	private static final char replacementChar = '*';
	private final Set<String> blackList;
	private final Comparator<String> stringComparator;
	private final String comment;

	public CommentFilter(String comment, Set<String> blackList,
		Comparator<String> stringComparator) {
		this.comment = comment;
		this.blackList = blackList;
		this.stringComparator = stringComparator;
	}

	@Override
	public String call() {
		String edit = comment;
		for (String word : comment.split("[^\\p{IsAlphabetic}]")) {
			edit = replaceIfProhibited(word, edit);
		}

		return edit;
	}

	private String replaceIfProhibited(String word, String edit) {
		for (String prohibitedWord : blackList) {
			if (stringComparator.compare(word, prohibitedWord) == 0) {
				var replacementChars = new char[prohibitedWord.length()];
				Arrays.fill(replacementChars, replacementChar);
				String replacement = new String(replacementChars);
				return edit.replaceAll(word, replacement);
			}
		}

		return edit;
	}
}
