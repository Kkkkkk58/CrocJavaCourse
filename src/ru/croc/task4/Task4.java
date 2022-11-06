package ru.croc.task4;

import ru.croc.task4.core.Point;
import ru.croc.task4.figures.Circle;
import ru.croc.task4.figures.Rectangle;

public class Task4 {
	public static void main(String[] args) {
		var c = new Circle(new Point(0, 0), 5);
		var r = new Rectangle(new Point(5, 7), new Point(10, 15));
		var annotation1 = new Annotation(c, "Tree");
		var annotation2 = new Annotation(r, "Car");
		var image = new AnnotatedImage("1.jpg", annotation1, annotation2);

		for (Annotation annotation : image.getAnnotations()) {
			System.out.println(annotation);
		}
	}
}
