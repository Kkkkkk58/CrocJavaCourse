package ru.croc.task16.core.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import ru.croc.task16.core.models.Coordinates;
import ru.croc.task16.core.models.OrderInformation;
import ru.croc.task16.core.models.SpecialService;
import ru.croc.task16.core.models.TaxiClass;

public class TaxiCab {

	private final UUID id;
	private final String name;
	private final String code;
	private final TaxiClass taxiClass;
	private final Collection<SpecialService> specialServices;
	private Coordinates location;
	private TaxiDriver driver;

	public TaxiCab(String name, String code, TaxiClass taxiClass,
		Collection<SpecialService> specialServices,
		Coordinates location, TaxiDriver driver) {
		id = UUID.randomUUID();
		this.name = name;
		this.code = code;
		this.taxiClass = taxiClass;
		this.specialServices = specialServices;
		this.driver = driver;
		this.location = location;
	}

	public boolean fitsOrder(OrderInformation orderInformation) {
		return taxiClass.equals(orderInformation.taxiClass())
			&& specialServices.containsAll(orderInformation.specialServices());
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public TaxiClass getTaxiClass() {
		return taxiClass;
	}

	public Collection<SpecialService> getSpecialServices() {
		return Collections.unmodifiableCollection(specialServices);
	}

	public Coordinates getLocation() {
		return location;
	}

	public void setLocation(Coordinates location) {
		this.location = location;
	}

	public TaxiDriver getDriver() {
		return driver;
	}

	public void setDriver(TaxiDriver driver) {
		this.driver = driver;
	}

	public void addSpecialService(SpecialService specialService) {
		if (!specialServices.add(specialService)) {
			throw new IllegalArgumentException("Can't add special service" + specialService);
		}
	}

	public void removeSpecialService(SpecialService specialService) {
		if (!specialServices.remove(specialService)) {
			throw new IllegalArgumentException("Can't remove special service" + specialService);
		}
	}
}
