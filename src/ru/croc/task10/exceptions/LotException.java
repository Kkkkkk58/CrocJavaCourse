package ru.croc.task10.exceptions;

import ru.croc.task10.core.abstractions.Lot;

public class LotException extends RuntimeException {

	private LotException(String message) {
		super(message);
	}

	public static LotException BettingTimeIsOver(Lot lot) {
		return new LotException(String.format("Time to bet for %s is over!", lot));
	}
}
