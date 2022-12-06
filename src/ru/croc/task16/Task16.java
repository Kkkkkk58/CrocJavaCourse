package ru.croc.task16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import ru.croc.task16.core.builders.TaxiCabBuilderImpl;
import ru.croc.task16.core.entities.TaxiCab;
import ru.croc.task16.core.entities.TaxiDriver;
import ru.croc.task16.core.models.Coordinates;
import ru.croc.task16.core.models.OrderInformation;
import ru.croc.task16.core.models.SpecialService;
import ru.croc.task16.core.models.TaxiClass;
import ru.croc.task16.core.services.TaxiHub;

public class Task16 {

	public static void main(String[] args) {
		Collection<TaxiDriver> drivers = seedDrivers();
		Collection<TaxiCab> cabs = seedCabs(drivers);
		var hub = new TaxiHub(drivers, cabs);
		OrderInformation orderInformation = getOrderInformation();
		TaxiCab suitableCab = hub.getTheMostSuitableCab(orderInformation);

		System.out.println(suitableCab.getDriver());
	}

	private static Collection<TaxiDriver> seedDrivers() {
		var driver1 = new TaxiDriver("Kracker Slacker");
		var driver2 = new TaxiDriver("Shmaker Pracker");
		var driver3 = new TaxiDriver("Clacker Dracker");

		return new ArrayList<>(
			List.of(driver1, driver2, driver3)
		);
	}

	private static Collection<TaxiCab> seedCabs(Collection<TaxiDriver> drivers) {
		final List<TaxiClass> classes = seedClasses();
		final List<Collection<SpecialService>> specialServices = seedSpecialServices();
		final List<Coordinates> coordinates = seedCoordinates();
		int n = 1;

		List<TaxiCab> cabs = new ArrayList<>();
		for (TaxiDriver driver : drivers) {
			var cab = new TaxiCabBuilderImpl()
				.WithName("Lada Kalina")
				.WithCode("A" + n + n + n + "AA")
				.WithTaxiClass(getElement(classes, n))
				.WithSpecialServices(getElement(specialServices, n))
				.LocatedAt(getElement(coordinates, n))
				.DrivenBy(driver)
				.Build();
			cabs.add(cab);
			++n;
		}
		return cabs;
	}

	private static <T> T getElement(List<T> collection, int n) {
		return collection.get((n - 1) % collection.size());
	}

	private static List<Coordinates> seedCoordinates() {
		return new ArrayList<>(
			List.of(
				new Coordinates(60.0112, 30.3212),
				new Coordinates(156.1101, 12.1512),
				new Coordinates(11.1111, 12.1111)
			)
		);
	}

	private static List<Collection<SpecialService>> seedSpecialServices() {
		return new ArrayList<>(List.of(
			new ArrayList<>(List.of(
				new SpecialService("Детское кресло"),
				new SpecialService("Багажник"),
				new SpecialService("Музыка")
			)),
			new ArrayList<>(List.of(
				new SpecialService("Молчу")
			)),
			new ArrayList<>(List.of(
				new SpecialService("Детское кресло")
			))
		));
	}

	@NotNull
	private static ArrayList<TaxiClass> seedClasses() {
		return new ArrayList<>(
			List.of(
				new TaxiClass("Бизнес"),
				new TaxiClass("Эконом"),
				new TaxiClass("Комфорт")
			)
		);
	}

	private static OrderInformation getOrderInformation() {
		Scanner input = new Scanner(System.in);
		Coordinates place = getCoordinates(input);
		TaxiClass taxiClass = getTaxiClass(input);
		Collection<SpecialService> specialServices = getSpecialServices(input);

		return new OrderInformation(place, taxiClass, specialServices);
	}

	private static Coordinates getCoordinates(Scanner input) {
		List<Double> values = Arrays
			.stream(input.nextLine().split(", "))
			.map(Double::parseDouble)
			.toList();
		double latitude = values.get(0);
		double longitude = values.get(1);
		return new Coordinates(latitude, longitude);
	}

	private static TaxiClass getTaxiClass(Scanner input) {
		String taxiClassName = input.nextLine();
		return new TaxiClass(taxiClassName);
	}

	private static Collection<SpecialService> getSpecialServices(Scanner input) {
		String[] specialServicesNames = input.nextLine().split(", ");

		return Arrays
			.stream(specialServicesNames)
			.map(SpecialService::new)
			.collect(Collectors.toList());
	}
}
