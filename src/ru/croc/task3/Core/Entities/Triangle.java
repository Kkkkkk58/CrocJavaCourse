package ru.croc.task3.Core.Entities;

import org.jetbrains.annotations.NotNull;
import ru.croc.task3.Core.Exceptions.TriangleException;

public class Triangle {

	final static private int TRIANGLE_SIDES = 3;
	private final Point[] _points;
	private final double[] _lengths;

	public Triangle(Point @NotNull [] points) throws TriangleException {
		if (points.length != TRIANGLE_SIDES) {
			throw new IllegalArgumentException();
		}

		double[] lengths = calculateLengthsOfSides(points);
		validateTriangle(lengths);

		_points = points;
		_lengths = lengths;
	}

	public double[] lengths() {
		return _lengths;
	}

	private double @NotNull [] calculateLengthsOfSides(Point[] points) {
		double[] lengths = new double[TRIANGLE_SIDES];
		for (int i = 0; i < TRIANGLE_SIDES; ++i) {
			int nextPointIndex = (i + 1) % TRIANGLE_SIDES;
			double curLength = getDistance(points[i], points[nextPointIndex]);
			lengths[i] = curLength;
		}

		return lengths;
	}

	private static double getDistance(@NotNull Point a, @NotNull Point b) {
		return Math.sqrt(Math.pow(a.x() - b.x(), 2) + Math.pow(a.y() - b.y(), 2));
	}

	private void validateTriangle(double @NotNull [] lengths) throws TriangleException {
		if (lengths[0] > lengths[1] + lengths[2]) {
			throw TriangleException.InvalidTriangleSides();
		}
	}
}
