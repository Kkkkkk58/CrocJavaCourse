package ru.croc.task10.core.abstractions;

import ru.croc.task10.core.MoneyAmount;
import java.time.LocalDateTime;
import java.util.UUID;

public interface Lot {
	void acceptBet(MoneyAmount newCost, String buyer, LocalDateTime betTime)
		throws InterruptedException;

	String getWinnerName();
	UUID getId();
	MoneyAmount getCost();
	LocalDateTime getEndTime();
}
