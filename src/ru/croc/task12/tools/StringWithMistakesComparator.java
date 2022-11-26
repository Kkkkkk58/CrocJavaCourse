package ru.croc.task12.tools;

import java.util.Comparator;

public class StringWithMistakesComparator implements Comparator<String> {

	private final int mistakesAllowed;

	public StringWithMistakesComparator(int mistakesAllowed) {
		if (mistakesAllowed < 0) {
			throw new IllegalArgumentException(
				"The number of allowed mistakes must be non-negative");
		}

		this.mistakesAllowed = mistakesAllowed;
	}

	@Override
	public int compare(String o1, String o2) {
		if (o1.equalsIgnoreCase(o2)) {
			return 0;
		}
		int curMistakes = 0;
		if (o1.length() == o2.length()) {
			for (int i = 0; i < o1.length(); i++) {
				if (o1.charAt(i) != o2.charAt(i)) {
					++curMistakes;
					if (curMistakes > mistakesAllowed) {
						return o1.compareTo(o2);
					}
				}
			}
			return 0;
		}

		return o1.compareTo(o2);
	}
}
