package ru.croc.task16.core.builders.abstractions;

import java.util.Collection;
import ru.croc.task16.core.models.SpecialService;

public interface TaxiCabSpecialServicesBuilder {

	TaxiCabLocationBuilder WithSpecialServices(Collection<SpecialService> specialServices);
}
