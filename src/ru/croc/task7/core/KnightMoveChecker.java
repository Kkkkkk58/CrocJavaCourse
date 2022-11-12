package ru.croc.task7.core;

import ru.croc.task7.exceptions.IllegalMoveException;

public class KnightMoveChecker {
	private static final int MAX_SINGLE_DELTA = 2, SUM_DELTA = 3;
	public void check(ChessPosition from, ChessPosition to) throws IllegalMoveException {
		int deltaX = getDeltaX(from, to);
		int deltaY = getDeltaY(from, to);

		if (hasInvalidDeltas(deltaX, deltaY)) {
			throw new IllegalMoveException(from, to);
		}
	}

	private int getDeltaX(ChessPosition from, ChessPosition to) {
		return Math.abs(to.rank() - from.rank());
	}

	private int getDeltaY(ChessPosition from, ChessPosition to) {
		return Math.abs(to.file() - from.file());
	}

	private boolean hasInvalidDeltas(int deltaX, int deltaY) {
		return deltaX > MAX_SINGLE_DELTA || deltaY > MAX_SINGLE_DELTA
			|| deltaX + deltaY != SUM_DELTA;
	}
}
