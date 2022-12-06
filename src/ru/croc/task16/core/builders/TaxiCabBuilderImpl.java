package ru.croc.task16.core.builders;

import java.util.Collection;
import ru.croc.task16.core.builders.abstractions.TaxiCabBuilder;
import ru.croc.task16.core.builders.abstractions.TaxiCabCodeBuilder;
import ru.croc.task16.core.builders.abstractions.TaxiCabDriverBuilder;
import ru.croc.task16.core.builders.abstractions.TaxiCabFinalBuilder;
import ru.croc.task16.core.builders.abstractions.TaxiCabLocationBuilder;
import ru.croc.task16.core.builders.abstractions.TaxiCabSpecialServicesBuilder;
import ru.croc.task16.core.builders.abstractions.TaxiCabTaxiClassBuilder;
import ru.croc.task16.core.entities.TaxiCab;
import ru.croc.task16.core.entities.TaxiDriver;
import ru.croc.task16.core.models.Coordinates;
import ru.croc.task16.core.models.SpecialService;
import ru.croc.task16.core.models.TaxiClass;

public class TaxiCabBuilderImpl implements TaxiCabBuilder, TaxiCabCodeBuilder,
	TaxiCabTaxiClassBuilder,
	TaxiCabSpecialServicesBuilder, TaxiCabLocationBuilder, TaxiCabDriverBuilder,
	TaxiCabFinalBuilder {

	private String name;
	private String code;
	private TaxiClass taxiClass;
	private Collection<SpecialService> specialServices;
	private Coordinates location;
	private TaxiDriver driver;

	@Override
	public TaxiCabCodeBuilder WithName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public TaxiCabTaxiClassBuilder WithCode(String code) {
		this.code = code;
		return this;
	}

	@Override
	public TaxiCabSpecialServicesBuilder WithTaxiClass(TaxiClass taxiClass) {
		this.taxiClass = taxiClass;
		return this;
	}

	@Override
	public TaxiCabLocationBuilder WithSpecialServices(Collection<SpecialService> specialServices) {
		this.specialServices = specialServices;
		return this;
	}


	@Override
	public TaxiCabDriverBuilder LocatedAt(Coordinates location) {
		this.location = location;
		return this;
	}

	@Override
	public TaxiCabFinalBuilder DrivenBy(TaxiDriver driver) {
		this.driver = driver;
		return this;
	}

	@Override
	public TaxiCab Build() {
		return new TaxiCab(name, code, taxiClass, specialServices, location, driver);
	}
}
