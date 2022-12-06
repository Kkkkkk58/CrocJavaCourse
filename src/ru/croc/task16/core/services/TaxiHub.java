package ru.croc.task16.core.services;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import ru.croc.task16.core.entities.TaxiCab;
import ru.croc.task16.core.entities.TaxiDriver;
import ru.croc.task16.core.models.Coordinates;
import ru.croc.task16.core.models.OrderInformation;

public class TaxiHub {

	private final Set<TaxiDriver> drivers;
	private final Set<TaxiCab> cabs;

	public TaxiHub(Collection<TaxiDriver> drivers, Collection<TaxiCab> cabs) {
		this.drivers = new HashSet<>(drivers);
		this.cabs = new HashSet<>(cabs);
	}

	public TaxiCab getTheMostSuitableCab(OrderInformation orderInformation) {
		Optional<TaxiCab> suitableCab = cabs
			.stream()
			.filter(cab -> cab.fitsOrder(orderInformation))
			.min(Comparator.comparingDouble(
				cab -> Coordinates.getDistance(cab.getLocation(), orderInformation.place())));

		if (suitableCab.isEmpty()) {
			throw new RuntimeException("Suitable cab not found");
		}
		return suitableCab.get();
	}

	public TaxiDriver addDriver(TaxiDriver driver) {
		if (!drivers.add(driver)) {
			throw new IllegalArgumentException("Cannot add driver " + driver.getId());
		}
		return driver;
	}

	public void removeDriver(UUID driverId) {
		if (!drivers.removeIf(driver -> driver.getId().equals(driverId))) {
			throw new IllegalArgumentException("Driver " + driverId + " not found");
		}
	}

	public TaxiCab addCab(TaxiCab cab) {
		if (!cabs.add(cab)) {
			throw new IllegalArgumentException("Cannot add cab " + cab.getId());
		}
		return cab;
	}

	public void removeCab(UUID cabId) {
		if (!cabs.removeIf(cab -> cab.getId().equals(cabId))) {
			throw new IllegalArgumentException("Cab " + cabId + " not found");
		}
	}
}
