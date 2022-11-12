package ru.croc.task7;

import org.jetbrains.annotations.NotNull;
import ru.croc.task7.core.ChessPosition;
import ru.croc.task7.core.KnightMoveChecker;
import ru.croc.task7.exceptions.IllegalMoveException;

public class Task7 {
	public static void main(String @NotNull [] args) {
		if (args.length == 0) {
			System.out.println("OK");
			return;
		}

		boolean isCorrect = true;
		try {
			CheckAllMoves(args);
		}
		catch (IllegalMoveException e) {
			isCorrect = false;
			System.out.println(e.getMessage());
		}

		if (isCorrect) {
			System.out.println("OK");
		}
	}

	private static void CheckAllMoves(String @NotNull [] args)
		throws IllegalMoveException {
		var knightMoveChecker = new KnightMoveChecker();

		ChessPosition curPosition = ChessPosition.parse(args[0]);
		for (int i = 1; i < args.length; ++i) {
			ChessPosition prevPosition = curPosition;
			curPosition = ChessPosition.parse(args[i]);

			knightMoveChecker.check(prevPosition, curPosition);
		}
	}
}
