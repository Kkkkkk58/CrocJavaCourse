package ru.croc.task5.exceptions;

import ru.croc.task5.core.Point;

public class RectangleException extends RuntimeException {
	private RectangleException(String message) {
		super(message);
	}

	public static RectangleException InvalidPointCoordinates(Point leftBottom, Point rightTop) {
		return new RectangleException("Invalid rectangle coordinates: leftBottom - " + leftBottom + ", rightTop - " + rightTop);
	}
}
