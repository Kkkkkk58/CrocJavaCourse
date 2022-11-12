package ru.croc.task7.exceptions;

import ru.croc.task7.core.ChessPosition;

public class IllegalMoveException extends Exception {
	public IllegalMoveException(ChessPosition from, ChessPosition to) {
		super(String.format("Move %s -> %s is invalid", from, to));
	}
}
