package ru.croc.task4.figures;

import ru.croc.task4.core.Point;
import ru.croc.task4.figures.abstractions.Figure;

public class Circle implements Figure {
	private final Point _center;
	private final int _radius;

	public Circle(Point center, int radius) {
		if (radius < 0) {
			throw new IllegalArgumentException("Negative radius");
		}

		_center = center;
		_radius = radius;
	}

	public Point center() {
		return _center;
	}

	public int radius() {
		return _radius;
	}

	@Override
	public String toString() {
		return String.format("Circle %s, %d", _center, _radius);
	}
}
