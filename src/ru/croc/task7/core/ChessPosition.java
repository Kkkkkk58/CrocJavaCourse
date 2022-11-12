package ru.croc.task7.core;

import org.jetbrains.annotations.NotNull;
import ru.croc.task7.exceptions.IllegalPositionException;

public class ChessPosition {
	private static final int MIN_COORDINATE = 0, MAX_COORDINATE = 7;
	private final int x, y;

	public ChessPosition(int x, int y) {
		if (isInvalidCoordinate(x) || isInvalidCoordinate(y)) {
			throw new IllegalPositionException(x, y);
		}

		this.x = x;
		this.y = y;
	}

	public static ChessPosition parse(@NotNull String position) {
		if (position.length() != 2) {
			throw new IllegalArgumentException("Invalid position string");
		}

		int x = position.charAt(0) - 'a';
		int y = position.charAt(1) - '1';
		return new ChessPosition(x, y);
	}

	public int rank() {
		return y + 1;
	}
	public char file() {
		return (char) ('a' + x);
	}

	@Override
	public String toString() {
		return String.format("%s%d", file(), rank());
	}

	private boolean isInvalidCoordinate(int coordinate) {
		return coordinate < MIN_COORDINATE || coordinate > MAX_COORDINATE;
	}
}
