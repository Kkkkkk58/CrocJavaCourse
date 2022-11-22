package ru.croc.task10.exceptions;

import java.util.Currency;

public class MoneyAmountException extends RuntimeException {

	private MoneyAmountException(String message) {
		super(message);
	}

	public static MoneyAmountException MismatchingCurrencies(Currency actual, Currency expected) {
		return new MoneyAmountException(
			String.format("Can't perform operations with different currencies - %s and %s", actual,
				expected));
	}
}
