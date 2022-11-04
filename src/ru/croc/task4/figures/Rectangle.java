package ru.croc.task4.figures;

import ru.croc.task4.core.Point;
import ru.croc.task4.exceptions.RectangleException;
import ru.croc.task4.figures.abstractions.Figure;

public class Rectangle implements Figure {
	private final Point _leftBottom;
	private final Point _rightTop;

	public Rectangle(Point leftBottom, Point rightTop) {
		if (coordinatesAreInvalid(leftBottom, rightTop)) {
			throw RectangleException.InvalidPointCoordinates(leftBottom, rightTop);
		}

		_leftBottom = leftBottom;
		_rightTop = rightTop;
	}

	public Point leftBottom() {
		return _leftBottom;
	}

	public Point rightTop() {
		return _rightTop;
	}

	@Override
	public String toString() {
		return String.format("Rectangle %s, %s", _leftBottom, _rightTop);
	}

	private static boolean coordinatesAreInvalid(Point leftBottom, Point rightTop) {
		return leftBottom.x() > rightTop.x() || leftBottom.y() > rightTop.y();
	}
}
