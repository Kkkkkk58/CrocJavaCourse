package ru.croc.task3.Core.Exceptions;

public class TriangleException extends Exception {
	private TriangleException(String message) {
		super(message);
	}

	public static TriangleException InvalidTriangleSides() {
		return new TriangleException("Passed an invalid triangle configuration");
	}
}
