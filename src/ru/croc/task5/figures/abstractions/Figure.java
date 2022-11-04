package ru.croc.task5.figures.abstractions;

import ru.croc.task5.core.Point;
import ru.croc.task5.core.abstractions.Movable;

public interface Figure extends Movable {
	boolean contains(Point point);
}
