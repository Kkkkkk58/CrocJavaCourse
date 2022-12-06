package ru.croc.task15.core;

public class AgeGroup {
	private static final int MAX_AGE = 123;
	private final int ageFrom;
	private final int ageTo;

	public AgeGroup(int ageFrom, Integer ageTo) {
		if (ageTo == null) {
			ageTo = MAX_AGE;
		}

		validateAges(ageFrom, ageTo);
		this.ageFrom = ageFrom;
		this.ageTo = ageTo;
	}

	public int getAgeTo() {
		return ageTo;
	}

	public int getAgeFrom() {
		return ageFrom;
	}

	@Override
	public String toString() {
		return String.format("[%d-%d]", ageFrom, ageTo);
	}

	private static void validateAges(int ageFrom, int ageTo) {
		if (ageTo < ageFrom) {
			throw new IllegalArgumentException("ageTo can't be less than ageFrom");
		}

		if (ageFrom < 0) {
			throw new IllegalArgumentException("Negative age");
		}
	}
}
