package ru.croc.task13.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import ru.croc.task13.core.entities.Film;

public class RecommendationService {
	private final List<Film> films;
	private final List<Map<Long, Long>> userWatchFrequency;

	public RecommendationService(List<Film> films, List<List<Long>> userWatchHistory) {
		this.films = films;
		this.userWatchFrequency = userWatchHistory.stream().map(this::getFrequencyRate).collect(
			Collectors.toList());
	}

	public Film recommendFilm(List<Long> userWatchList) {
		Map<Long, Long> watchFrequency = getFrequencyRate(userWatchList);
		Map<Long, Double> userWeight = new HashMap<>();
		Set<Film> possibleRecommendations = new HashSet<>();

		long i = 0;
		for (var frequency : userWatchFrequency) {
			Set<Long> intersections = new HashSet<>(watchFrequency.keySet());
			intersections.retainAll(frequency.keySet());
			double weight = (double) intersections.size() / userWatchList.size();
			userWeight.put(i, weight);
			++i;
			if (weight >= 0.5) {
				Set<Long> remain = new HashSet<>(frequency.keySet());
				remain.removeAll(watchFrequency.keySet());
				for (Long filmId : remain) {
					possibleRecommendations.add(films.stream().filter(film -> film.id() == filmId).findFirst().get());
				}
			}
		}
		Map<Film, Double> recommendationAnalysis = new HashMap<>();

		for (Film film : possibleRecommendations) {
			recommendationAnalysis.put(film, (double) 0);
		}

		for (i = 0; i < userWatchFrequency.size(); ++i) {
			Map<Long, Long> map = userWatchFrequency.get((int) i);
			for (int j = 0; j < map.size(); ++j) {
				for (Film recommendation : possibleRecommendations) {
					if (map.containsKey(recommendation.id())) {
						double recommendationLevel = recommendationAnalysis.get(recommendation) + userWeight.get(i) * map.get(recommendation.id());
						recommendationAnalysis.replace(recommendation, recommendationLevel);
					}
				}
			}
		}

		return Collections.max(recommendationAnalysis.entrySet(), Entry.comparingByValue()).getKey();
	}

	private Map<Long, Long> getFrequencyRate(List<Long> watchList) {
		return watchList.stream().collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));
	}
}
