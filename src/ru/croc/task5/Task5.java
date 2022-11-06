package ru.croc.task5;

import ru.croc.task5.core.Point;
import ru.croc.task5.figures.Circle;
import ru.croc.task5.figures.Rectangle;

public class Task5 {
	public static void main(String[] args) {
		var c = new Circle(new Point(0, 0), 5);
		var r = new Rectangle(new Point(5, 7), new Point(10, 15));
		var annotation1 = new Annotation(c, "Tree");
		var annotation2 = new Annotation(r, "Car");
		var image = new AnnotatedImage("1.jpg", annotation1, annotation2);

		for (Annotation annotation : image.getAnnotations()) {
			System.out.println(annotation);
		}
		System.out.println("=== Move rectangle ===");
		r.move(-1, -1);
		for (Annotation annotation : image.getAnnotations()) {
			System.out.println(annotation);
		}

		Annotation a = image.findByLabel("ee");
		assert a.equals(annotation1);
		Annotation b = image.findByPoint(0, 0);
		assert b.equals(annotation1);
		c.move(10, 10);
		Annotation d = image.findByPoint(0, 0);
		assert d == null;
	}
}
