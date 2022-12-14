package ru.croc.task2.Core.Models;

import ru.croc.task2.Core.Abstractions.Progression;

public class ArithmeticProgression implements Progression {
	private final int _firstElement, _diff;

	public ArithmeticProgression(int firstElement, int diff) {
		_firstElement = firstElement;
		_diff = diff;
	}

	@Override
	public int firstElement() {
		return _firstElement;
	}

	@Override
	public int diff() {
		return _diff;
	}
}
