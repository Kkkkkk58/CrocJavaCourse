package ru.croc.task16.core.builders.abstractions;

import ru.croc.task16.core.models.TaxiClass;

public interface TaxiCabTaxiClassBuilder {

	TaxiCabSpecialServicesBuilder WithTaxiClass(TaxiClass taxiClass);
}
