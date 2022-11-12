package ru.croc.task8;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Task8 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Locale locale = EnterLocale(scanner);
		double value = EnterValue(scanner);

		NumberFormat nf = DecimalFormat.getCurrencyInstance(locale);
		System.out.println(nf.format(value));
	}

	private static Locale EnterLocale(Scanner input) {
		System.out.print("Enter a locale language tag: ");
		String languageTag = input.nextLine();
		return getChosenLocale(languageTag);
	}

	private static Locale getChosenLocale(String languageTag) {
		if (languageTag.isEmpty()) {
			return Locale.getDefault();
		}

		Locale locale = Locale.forLanguageTag(languageTag);
		if (!Arrays.asList(Locale.getAvailableLocales()).contains(locale)) {
			throw new IllegalArgumentException("Given language tag is invalid");
		}
		return locale;
	}

	private static double EnterValue(Scanner scanner) {
		System.out.print("Enter a number: ");
		return scanner.nextDouble();
	}
}
