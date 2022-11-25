package ru.croc.task10.core.abstractions;

import java.time.LocalDateTime;
import java.util.UUID;
import ru.croc.task10.core.MoneyAmount;
import ru.croc.task10.exceptions.LotException;

public interface Lot {

	void acceptBet(MoneyAmount newCost, String buyer)
		throws LotException;

	String getWinnerName();

	UUID getId();

	MoneyAmount getCost();

	LocalDateTime getEndTime();
}
