package ru.croc.task2.Core.Models;

import ru.croc.task2.Core.Abstractions.Progression;
import ru.croc.task2.Core.Abstractions.ProgressionSumCounter;

public class ArithmeticProgressionSumCounter implements ProgressionSumCounter {
	@Override
	public int getProgressionSum(Progression progression, int numberOfElements) {
		if (!(progression instanceof ArithmeticProgression)) {
			throw new IllegalArgumentException(
				"Counter must be provided with ArithmeticProgression");
		}

		if (numberOfElements < 0) {
			throw new IllegalArgumentException("Negative number of elements");
		}

		int sum = 0;
		for (int i = 0; i < numberOfElements; ++i) {
			sum += progression.firstElement() + (i * progression.diff());
		}

		return sum;
	}
}
