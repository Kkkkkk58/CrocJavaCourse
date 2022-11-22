package ru.croc.task10;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Currency;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ru.croc.task10.core.MoneyAmount;
import ru.croc.task10.core.ThreadSafeLot;
import ru.croc.task10.core.abstractions.Lot;
import ru.croc.task10.exceptions.LotException;

public class Task10 {

	public static void main(String[] args) {
		var lot = new ThreadSafeLot("lot", LocalDateTime.now().plus(1, ChronoUnit.MINUTES));
		try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
			executorService.execute(new Tester("KSlacker", lot, 1));
			executorService.execute(new Tester("User2", lot, 2));
			executorService.execute(new Tester("User3", lot, 3));
		}

	}

	private static class Tester implements Runnable {

		private final String bidderName;
		private final Lot lot;
		private final int sleep;

		private Tester(String bidderName, Lot lot, int sleep) {
			this.bidderName = bidderName;
			this.lot = lot;
			this.sleep = sleep;
		}

		@Override
		public void run() {
			try {
				while (true) {
					var moneyAmount = BigDecimal.valueOf(Math.random());
					lot.acceptBet(new MoneyAmount(moneyAmount, Currency.getInstance("USD")),
						bidderName,
						LocalDateTime.now());
					String result = String.format("Current winner is %s with %s",
						lot.getWinnerName(), lot.getCost());
					System.out.println(result);
					Thread.sleep(1000L * sleep);
				}
			} catch (InterruptedException | LotException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
