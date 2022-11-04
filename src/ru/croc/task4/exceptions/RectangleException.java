package ru.croc.task4.exceptions;

import ru.croc.task4.core.Point;

public class RectangleException extends RuntimeException {
	private RectangleException(String message) {
		super(message);
	}

	public static RectangleException InvalidPointCoordinates(Point leftBottom, Point rightTop) {
		return new RectangleException("Invalid rectangle coordinates: leftBottom - " + leftBottom + ", rightTop - " + rightTop);
	}
}
