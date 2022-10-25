package ru.croc.task2;

import org.jetbrains.annotations.NotNull;
import ru.croc.task2.Core.Models.ArithmeticProgression;
import ru.croc.task2.Core.Models.ArithmeticProgressionSumCounter;

public class Task2 {

	public static void main(String @NotNull [] args) {
		if (args.length != 3) {
			throw new IllegalArgumentException("Not enough arguments");
		}

		int firstElement = Integer.parseInt(args[0]);
		int diff = Integer.parseInt(args[1]);
		int numberOfElements = Integer.parseInt(args[2]);

		var progression = new ArithmeticProgression(firstElement, diff);
		var counter = new ArithmeticProgressionSumCounter();
		int sum = counter.getProgressionSum(progression, numberOfElements);

		System.out.println("Sum: " + sum);
	}
}
