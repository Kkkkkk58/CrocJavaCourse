package ru.croc.task10.core;

import org.jetbrains.annotations.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

public class MoneyAmount implements Comparable<MoneyAmount> {
	private final BigDecimal amount;
	private final Currency currency;

	public MoneyAmount(BigDecimal amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}

	@Override
	public int compareTo(@NotNull MoneyAmount o) {
		if (!currency.equals(o.currency)) {
			throw new IllegalArgumentException();
		}

		return amount.compareTo(o.amount);
	}

	@Override
	public String toString() {
		return currency + " " + amount;
	}
}
