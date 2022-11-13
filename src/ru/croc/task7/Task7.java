package ru.croc.task7;

import org.jetbrains.annotations.NotNull;
import ru.croc.task7.core.ChessPosition;
import ru.croc.task7.core.KnightMoveChecker;
import ru.croc.task7.exceptions.IllegalMoveException;

import java.util.Arrays;

public class Task7 {

	/***
	 * Program validates moves of knight passed as command line arguments
	 * @param args completed moves recorded in standard notation
	 */
	public static void main(String @NotNull [] args) {
		ChessPosition[] moves = getMoves(args);
		try {
			KnightMoveChecker.checkAllMoves(moves);
			System.out.println("OK");
		} catch (IllegalMoveException e) {
			System.out.println(e.getMessage());
		}
	}

	private static ChessPosition @NotNull [] getMoves(String[] moveNotations) {
		return Arrays
			.stream(moveNotations)
			.map(ChessPosition::parse)
			.toArray(ChessPosition[]::new);
	}
}
