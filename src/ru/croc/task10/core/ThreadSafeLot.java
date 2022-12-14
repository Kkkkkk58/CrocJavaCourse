package ru.croc.task10.core;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Currency;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import ru.croc.task10.core.abstractions.Chronometer;
import ru.croc.task10.core.abstractions.Lot;
import ru.croc.task10.exceptions.LotException;

public class ThreadSafeLot implements Lot {

	private final UUID id;
	private final String name;
	private final LocalDateTime endTime;
	private final ReentrantLock lock = new ReentrantLock();
	private final Chronometer chronometer;
	private MoneyAmount cost;
	private String buyerName;

	public ThreadSafeLot(String name, TemporalAmount duration, Chronometer chronometer) {
		this(name, new MoneyAmount(new BigDecimal(0), Currency.getInstance("USD")), duration,
			chronometer);
	}

	public ThreadSafeLot(String name, MoneyAmount initialCost, TemporalAmount duration,
		Chronometer chronometer) {
		id = UUID.randomUUID();
		this.name = name;
		cost = initialCost;
		this.endTime = chronometer.now().plus(duration);
		this.chronometer = chronometer;
		buyerName = null;
	}

	public void acceptBet(MoneyAmount newCost, String buyer)
		throws LotException {
		lock.lock();
		try {
			LocalDateTime betTime = chronometer.now();
			if (betTime.isAfter(endTime)) {
				throw LotException.BettingTimeIsOver(this);
			}
			if (newCost.compareTo(cost) <= 0) {
				System.out.println("Current bet is lower than the highest");
				return;
			}

			cost = newCost;
			buyerName = buyer;
		} finally {
			lock.unlock();
		}

	}

	public String getWinnerName() {
		while (lock.isLocked())
			;
		return buyerName;
	}

	public UUID getId() {
		return id;
	}

	public MoneyAmount getCost() {
		while (lock.isLocked())
			;
		return cost;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	@Override
	public String toString() {
		return String.format("Lot %s [%s]", name, id);
	}
}
