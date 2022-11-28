package ru.croc.task13.core;

import org.jetbrains.annotations.NotNull;
import ru.croc.task13.core.entities.Film;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RecommendationService {

	private static final double MIN_SIMILARITY_RATE = 0.5;
	private final List<Film> films;
	private final List<Map<Long, Long>> overallWatchFrequency;

	/**
	 * .ctor of recommendation service
	 *
	 * @param films                  Collection of films
	 * @param overallWatchStatistics Statistics by users with list of watched films' ids
	 */
	public RecommendationService(List<Film> films, List<List<Long>> overallWatchStatistics) {
		this.films = films;
		this.overallWatchFrequency = overallWatchStatistics.stream().map(this::getFrequencyRate)
			.collect(Collectors.toList());
	}

	/**
	 * Finds the recommendation based upon user's watch history
	 *
	 * @param userWatchList List of watched films' ids
	 * @return next recommended film
	 */
	public Film recommendFilm(List<Long> userWatchList) {
		Map<Long, Long> userWatchFrequency = getFrequencyRate(userWatchList);
		Map<Integer, Double> userRecommendationWeight = new HashMap<>();
		Set<Film> possibleRecommendations = new HashSet<>();

		getPossibleRecommendationsAndWeights(userWatchFrequency, userRecommendationWeight,
			possibleRecommendations);

		Map<Film, Double> recommendationAnalysis = performRecommendationAnalysis(
			userRecommendationWeight, possibleRecommendations);

		return getMostRecommendedFilm(recommendationAnalysis);
	}

	/**
	 * Fills possible recommendations and their weights
	 *
	 * @param userWatchFrequency       statistics about user's watch frequencies
	 * @param userRecommendationWeight map with user id and weight of one's recommendations
	 * @param possibleRecommendations  list of possible recommendations
	 */
	private void getPossibleRecommendationsAndWeights(
		Map<Long, Long> userWatchFrequency,
		Map<Integer, Double> userRecommendationWeight,
		Set<Film> possibleRecommendations) {

		for (int i = 0; i < overallWatchFrequency.size(); ++i) {
			Map<Long, Long> userFromBaseWatchFrequency = overallWatchFrequency.get(i);
			double weight = getRecommendationWeight(userWatchFrequency, userFromBaseWatchFrequency);
			userRecommendationWeight.put(i, weight);

			if (weight >= MIN_SIMILARITY_RATE) {
				getNewRecommendations(userWatchFrequency, possibleRecommendations,
					userFromBaseWatchFrequency);
			}
		}
	}

	/**
	 * Measures recommendation weight based on the number of intersections of watch histories
	 *
	 * @param userWatchFrequency         statistics about new user's watch history
	 * @param userFromBaseWatchFrequency statistics about another user from base
	 * @return weight of recommendation
	 */
	private static double getRecommendationWeight(
		Map<Long, Long> userWatchFrequency,
		Map<Long, Long> userFromBaseWatchFrequency) {

		Set<Long> intersections = new HashSet<>(userWatchFrequency.keySet());
		intersections.retainAll(userFromBaseWatchFrequency.keySet());

		return (double) intersections.size() / userWatchFrequency.size();
	}

	/**
	 * Fills possible recommendations with yet uncovered film
	 *
	 * @param userWatchFrequency         statistics about new user's watch history
	 * @param possibleRecommendations    list of possible recommendations
	 * @param userFromBaseWatchFrequency statistics about another user from base
	 */
	private void getNewRecommendations(
		Map<Long, Long> userWatchFrequency,
		Set<Film> possibleRecommendations,
		Map<Long, Long> userFromBaseWatchFrequency) {

		Set<Long> unwatchedFilms = new HashSet<>(userFromBaseWatchFrequency.keySet());
		unwatchedFilms.removeAll(userWatchFrequency.keySet());

		for (Long filmId : unwatchedFilms) {
			Optional<Film> recommendation = films.stream().filter(film -> film.id() == filmId)
				.findFirst();
			recommendation.ifPresent(possibleRecommendations::add);
		}
	}

	/**
	 * Performs analysis based on users' recommendation weights and watch frequencies
	 *
	 * @param userRecommendationWeight map of users' recommendation weights
	 * @param possibleRecommendations  list of possible recommendations
	 * @return map where key is a film and value is its recommendation score
	 */
	@NotNull
	private Map<Film, Double> performRecommendationAnalysis(
		Map<Integer, Double> userRecommendationWeight,
		Set<Film> possibleRecommendations) {

		Map<Film, Double> recommendationAnalysis = new HashMap<>();
		for (Film film : possibleRecommendations) {
			recommendationAnalysis.put(film, (double) 0);
		}

		for (int i = 0; i < overallWatchFrequency.size(); ++i) {
			Map<Long, Long> userFromBaseWatchFrequency = overallWatchFrequency.get(i);
			double recommendationWeight = userRecommendationWeight.get(i);
			analyzeUserFromBaseWeights(recommendationAnalysis, possibleRecommendations,
				userFromBaseWatchFrequency, recommendationWeight);
		}

		return recommendationAnalysis;
	}

	/**
	 * Sets the result of new analysis based on user from base recommendation weight and watch
	 * history
	 *
	 * @param recommendationAnalysis     map of films and their recommendation scores
	 * @param possibleRecommendations    list of possible recommendations
	 * @param userFromBaseWatchFrequency statistics about user from base
	 * @param recommendationWeight       user from base recommendation weight
	 */
	private static void analyzeUserFromBaseWeights(
		Map<Film, Double> recommendationAnalysis,
		Set<Film> possibleRecommendations,
		Map<Long, Long> userFromBaseWatchFrequency,
		double recommendationWeight) {

		for (int j = 0; j < userFromBaseWatchFrequency.size(); ++j) {
			for (Film recommendation : possibleRecommendations) {
				if (userFromBaseWatchFrequency.containsKey(recommendation.id())) {
					double recommendationLevel = getRecommendationLevel(recommendationAnalysis,
						userFromBaseWatchFrequency, recommendationWeight, recommendation);
					recommendationAnalysis.replace(recommendation, recommendationLevel);
				}
			}
		}
	}

	/**
	 * Evaluates recommendation total weight
	 *
	 * @param recommendationAnalysis     map of films and their recommendation scores
	 * @param userFromBaseWatchFrequency statistics about user from base
	 * @param recommendationWeight       weight of user from base recommendation
	 * @param recommendation             recommended film
	 * @return recommendation weight
	 */
	private static double getRecommendationLevel(
		Map<Film, Double> recommendationAnalysis,
		Map<Long, Long> userFromBaseWatchFrequency,
		double recommendationWeight,
		Film recommendation) {

		return recommendationAnalysis.get(recommendation)
			+ recommendationWeight * userFromBaseWatchFrequency.get(recommendation.id());
	}

	/**
	 * Determines which recommendation fits the most based on its total weight
	 *
	 * @param recommendationAnalysis map of weights
	 * @return recommended film
	 */
	private static Film getMostRecommendedFilm(Map<Film, Double> recommendationAnalysis) {
		return Collections.max(recommendationAnalysis.entrySet(), Entry.comparingByValue())
			.getKey();
	}

	/**
	 * Creates map of frequencies where key is film id and value is the number of times the film was
	 * watched by user
	 *
	 * @param watchList List of film identifiers
	 * @return map of frequency rates
	 */
	private Map<Long, Long> getFrequencyRate(List<Long> watchList) {
		return watchList.stream().collect(
			Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));
	}
}
