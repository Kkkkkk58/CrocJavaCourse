package ru.croc.task13;

import java.util.ArrayList;
import java.util.List;
import ru.croc.task13.core.RecommendationService;
import ru.croc.task13.core.entities.Film;

public class Task13 {
	public static void main(String[] args) {
		List<Film> films = new ArrayList<>(
			List.of(
				new Film(1, "Мстители: Финал"),
				new Film(2, "Хатико"),
				new Film(3, "Дюна"),
				new Film(4, "Унесенные призраками")
			)
		);
		List<List<Long>> wh = new ArrayList<>();
		wh.add(new ArrayList<>(List.of((long)1, (long)2, (long)3)));
		wh.add(new ArrayList<>(List.of((long)1, (long)4, (long)3)));
		wh.add(new ArrayList<>(List.of((long)2, (long)2, (long)3, (long)2, (long)2, (long)2)));

		var service = new RecommendationService(films, wh);
		List<Long> ww = new ArrayList<>(List.of((long)2, (long)4));

		System.out.println(service.recommendFilm(ww).name());
	}
}
