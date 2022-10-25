package ru.croc.task3.Core.Models;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;
import ru.croc.task3.Core.Abstractions.ITriangleAreaCounter;
import ru.croc.task3.Core.Entities.Triangle;

public class HeronTriangleAreaCounter implements ITriangleAreaCounter {

	@Override
	public double getArea(@NotNull Triangle triangle) {
		double[] lengths = triangle.lengths();
		double perimeterHalf = Arrays.stream(lengths).sum() / 2.0;

		return calculateArea(lengths, perimeterHalf);
	}

	private static double calculateArea(double @NotNull [] lengths, double perimeterHalf) {
		double value = Math.sqrt(
			perimeterHalf * (perimeterHalf - lengths[0]) * (perimeterHalf - lengths[1]) *
				(perimeterHalf - lengths[2]));
		double scale = Math.pow(10, 3);
		return Math.ceil(value * scale) / scale;
	}
}
