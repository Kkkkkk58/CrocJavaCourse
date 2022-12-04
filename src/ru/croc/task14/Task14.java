package ru.croc.task14;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import ru.croc.task14.core.SimpleBlackListFilter;
import ru.croc.task14.core.abstractions.BlackListFilter;

public class Task14 {

	public static void main(String[] args) {
		BlackListFilter<String> filter = new SimpleBlackListFilter<>();
		Iterable<String> comments = getComments();

		Predicate<String> isBadWord = comment -> comment.contains("фуфайка");
		Iterable<String> result = filter.filterComments(comments, isBadWord);

		for (String comment : result) {
			System.out.println(comment);
		}
	}

	private static Iterable<String> getComments() {
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
}
