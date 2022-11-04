package ru.croc.task5;

import ru.croc.task5.figures.abstractions.Figure;

public class Annotation {
	private final Figure _figure;
	private final String _caption;

	public Annotation(Figure figure, String caption) {
		_figure = figure;
		_caption = caption;
	}

	public Figure figure() {
		return _figure;
	}

	public String caption() {
		return _caption;
	}

	@Override
	public String toString() {
		return _figure + ": " + _caption;
	}
}
