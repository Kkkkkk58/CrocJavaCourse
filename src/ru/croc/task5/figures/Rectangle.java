package ru.croc.task5.figures;

import ru.croc.task5.core.Point;
import ru.croc.task5.exceptions.RectangleException;
import ru.croc.task5.figures.abstractions.Figure;

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
	public boolean contains(Point point) {
		return point.x() <= _rightTop.x() && point.x() >= _leftBottom.x()
			&& point.y() <= _rightTop.y() && point.y() >= _leftBottom.y();
	}

	@Override
	public void move(int dx, int dy) {
		_leftBottom.move(dx, dy);
		_rightTop.move(dx, dy);
	}

	@Override
	public String toString() {
		return String.format("Rectangle %s, %s", _leftBottom, _rightTop);
	}

	private static boolean coordinatesAreInvalid(Point leftBottom, Point rightTop) {
		return leftBottom.x() > rightTop.x() || leftBottom.y() > rightTop.y();
	}
}
