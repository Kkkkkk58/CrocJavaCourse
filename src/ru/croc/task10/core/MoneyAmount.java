package ru.croc.task10.core;

import java.math.BigDecimal;
import java.util.Currency;
import org.jetbrains.annotations.NotNull;
import ru.croc.task10.exceptions.MoneyAmountException;

public class MoneyAmount implements Comparable<MoneyAmount> {

	private final BigDecimal amount;
	private final Currency currency;

	public MoneyAmount(BigDecimal amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}

	@Override
	public int compareTo(@NotNull MoneyAmount other) {
		if (!currency.equals(other.currency)) {
			throw MoneyAmountException.MismatchingCurrencies(other.currency, currency);
		}

		return amount.compareTo(other.amount);
	}

	@Override
	public String toString() {
		return currency + " " + amount;
	}
}
