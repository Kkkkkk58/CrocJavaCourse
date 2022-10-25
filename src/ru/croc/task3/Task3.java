package ru.croc.task3;

import org.jetbrains.annotations.NotNull;
import ru.croc.task3.Core.Abstractions.TriangleAreaCounter;
import ru.croc.task3.Core.Entities.Point;
import ru.croc.task3.Core.Entities.Triangle;
import ru.croc.task3.Core.Exceptions.TriangleException;
import ru.croc.task3.Core.Models.HeronTriangleAreaCounter;

public class Task3 {

	private static final int COORDINATES_NUMBER = 6;

	public static void main(String @NotNull [] args) {
		if (args.length != COORDINATES_NUMBER) {
			throw new IllegalArgumentException("Invalid number of arguments");
		}

		double[] coordinates = parseCoordinates(args);
		Point a = new Point(coordinates[0], coordinates[1]);
		Point b = new Point(coordinates[2], coordinates[3]);
		Point c = new Point(coordinates[4], coordinates[5]);

		try {
			Triangle triangle = new Triangle(new Point[]{a, b, c});

			TriangleAreaCounter counter = new HeronTriangleAreaCounter();
			double area = counter.getArea(triangle);

			System.out.println("Area: " + area);
		}
		catch (TriangleException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static double @NotNull [] parseCoordinates(String[] args) {
		double[] coordinates = new double[COORDINATES_NUMBER];
		for (int i = 0; i < COORDINATES_NUMBER; ++i) {
			coordinates[i] = Double.parseDouble(args[i]);
		}

		return coordinates;
	}
}
