package ru.croc.task7.core;

import org.jetbrains.annotations.NotNull;
import ru.croc.task7.exceptions.IllegalMoveException;

public class KnightMoveChecker {
	private static final int MAX_SINGLE_DELTA = 2, SUM_DELTA = 3;

	public static void checkAllMoves(ChessPosition @NotNull [] moves) throws IllegalMoveException {
		for (int i = 1; i < moves.length; ++i) {
			checkMove(moves[i - 1], moves[i]);
		}
	}

	public static void checkMove(ChessPosition from, ChessPosition to) throws IllegalMoveException {
		int deltaX = getDeltaX(from, to);
		int deltaY = getDeltaY(from, to);

		if (hasInvalidDeltas(deltaX, deltaY)) {
			throw new IllegalMoveException(from, to);
		}
	}

	private static int getDeltaX(ChessPosition from, ChessPosition to) {
		return Math.abs(to.rank() - from.rank());
	}

	private static int getDeltaY(ChessPosition from, ChessPosition to) {
		return Math.abs(to.file() - from.file());
	}

	private static boolean hasInvalidDeltas(int deltaX, int deltaY) {
		return deltaX > MAX_SINGLE_DELTA || deltaY > MAX_SINGLE_DELTA
			|| deltaX + deltaY != SUM_DELTA;
	}
}
