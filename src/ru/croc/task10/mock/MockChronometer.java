package ru.croc.task10.mock;

import java.time.LocalDateTime;
import ru.croc.task10.core.abstractions.Chronometer;

public class MockChronometer implements Chronometer {

	@Override
	public LocalDateTime now() {
		return LocalDateTime.now();
	}
}
