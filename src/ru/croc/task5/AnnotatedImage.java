package ru.croc.task5;

import ru.croc.task5.core.Point;
import java.util.function.Predicate;

public class AnnotatedImage {
	private final String imagePath;
	private final Annotation[] annotations;

	public AnnotatedImage(String imagePath, Annotation... annotations) {
		this.imagePath = imagePath;
		this.annotations = annotations;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public Annotation[] getAnnotations() {
		return this.annotations;
	}

	public Annotation findByPoint(int x, int y) {
		Point point = new Point(x, y);

		return findFirstWhere(annotation -> annotation.figure().contains(point));
	}

	public Annotation findByLabel(String label) {
		return findFirstWhere(annotation -> annotation.caption().contains(label));
	}

	private Annotation findFirstWhere(Predicate<Annotation> condition) {
		for (Annotation annotation : annotations) {
			if (condition.test(annotation)) {
				return annotation;
			}
		}

		return null;
	}
}
