package ru.croc.task2.Core.Models;

import ru.croc.task2.Core.Abstractions.IProgression;
import ru.croc.task2.Core.Abstractions.IProgressionSumCounter;

public class ArithmeticProgressionSumCounter implements IProgressionSumCounter {

	@Override
	public int getProgressionSum(IProgression progression, int numberOfElements) {
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
