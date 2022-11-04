package ru.croc.task5.core;

import ru.croc.task5.core.abstractions.Movable;

public class Point implements Movable {
	private int _x;
	private int _y;

	public Point(int x, int y) {
		_x = x;
		_y = y;
	}

	public int x() {
		return _x;
	}

	public int y() {
		return _y;
	}

	public double getDistance(Point other) {
		return (Math.sqrt(Math.pow(other.x() - x(), 2) + Math.pow(other.y() - y(), 2)));
	}

	@Override
	public String toString() {
		return String.format("(%d, %d)", _x, _y);
	}

	@Override
	public void move(int dx, int dy) {
		_x += dx;
		_y += dy;
	}
}
