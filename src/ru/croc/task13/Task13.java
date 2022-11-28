package ru.croc.task13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import ru.croc.task13.core.RecommendationService;
import ru.croc.task13.core.entities.Film;

public class Task13 {
	private static final String filmsPath = "src/ru/croc/task13/resources/films_train.csv";
	private static final String statisticsPath = "src/ru/croc/task13/resources/ratings_train.csv";

	public static void main(String[] args) throws IOException {
		List<Film> films = getFilms();
		List<List<Long>> overallWatchStatistics = getOverallWatchStatistics();

		var service = new RecommendationService(films, overallWatchStatistics);
		List<Long> userWatchHistory = getUserWatchHistory();
		Film recommendation = service.recommendFilm(userWatchHistory);

		System.out.println("The recommended film is " + recommendation.name());
	}

	private static List<Long> getUserWatchHistory() throws IOException {
		System.out.print("Enter user history: ");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String[] input = br.readLine().split(",");
			return Arrays.stream(input).map(Long::parseLong).toList();
		}
	}

	private static List<List<Long>> getOverallWatchStatistics() throws IOException {
		List<List<Long>> statistics = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(statisticsPath))) {
			String line = br.readLine();
			while (line != null) {
				String[] input = line.split(",");
				List<Long> watchedFilmsIds = Arrays.stream(input).map(Long::parseLong).toList();
				statistics.add(watchedFilmsIds);
				line = br.readLine();
			}
		}

		return statistics;
	}

	@NotNull
	private static List<Film> getFilms() throws IOException {
		List<Film> films = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filmsPath))) {
			String line = br.readLine();
			while (line != null) {
				String[] input = line.split(",");
				if (input.length != 2) {
					throw new RuntimeException("Input file format is invalid");
				}

				long id = Long.parseLong(input[0]);
				String name = input[1];
				films.add(new Film(id, name));
				line = br.readLine();
			}
		}

		return films;
	}
}
