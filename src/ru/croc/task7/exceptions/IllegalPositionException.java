package ru.croc.task7.exceptions;

public class IllegalPositionException extends RuntimeException {
	public IllegalPositionException(int x, int y) {
		super(String.format("Position (%d, %d) is invalid", x, y));
	}
}
