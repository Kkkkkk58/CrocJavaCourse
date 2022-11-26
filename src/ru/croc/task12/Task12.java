package ru.croc.task12;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import ru.croc.task12.core.AsyncBlackListFilter;
import ru.croc.task12.core.abstractions.BlackListFilter;
import ru.croc.task12.tools.StringWithMistakesComparator;

public class Task12 {

	public static void main(String[] args) {
		List<String> comments = getComments();
		Set<String> blackList = getBlackList();
		BlackListFilter filter = new AsyncBlackListFilter(
			new StringWithMistakesComparator(1));

		filter.filterComments(comments, blackList);

		for (String comment : comments) {
			System.out.println(comment);
		}
	}

	private static List<String> getComments() {
		return new ArrayList<>(
			List.of(
				"Парень с девушкой занимаются любовью у него дома",
				"Заходят родители",
				"(далее мысли)",
				"Парень: \"Ну всё, попал\"",
				"Девушка: \"Ну всё, только жениться\"",
				"Мать: \"Боже, как она лежит! Ему же неудобно!\"",
				"Отец: \"Надо бы фуфайку новую купить... Аахахахахах фуфайка какое же смешное слово",
				"фуфайка фуфайка фуфайка ахахахах\"")
		);
	}

	private static Set<String> getBlackList() {
		return Set.of(
			"девушка",
			"девушкой",
			"фуфайка",
			"все"
		);
	}
}
