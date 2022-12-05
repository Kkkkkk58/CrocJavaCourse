package ru.croc.task15.core;

public class AgeGroup {
	private final Integer ageFrom;
	private final Integer ageTo;

	public AgeGroup(Integer ageFrom, Integer ageTo) {
		validateAges(ageFrom, ageTo);
		this.ageFrom = ageFrom;
		this.ageTo = ageTo;
	}

	public Integer getAgeTo() {
		return ageTo;
	}

	public Integer getAgeFrom() {
		return ageFrom;
	}

	@Override
	public String toString() {
		return String.format("[%d-%d]", ageFrom, ageTo);
	}

	private static void validateAges(Integer ageFrom, Integer ageTo) {
		if (ageTo != null && ageTo < ageFrom) {
			throw new IllegalArgumentException("ageTo can't be less than ageFrom");
		}

		if (ageFrom < 0) {
			throw new IllegalArgumentException("Negative age");
		}
	}
}
