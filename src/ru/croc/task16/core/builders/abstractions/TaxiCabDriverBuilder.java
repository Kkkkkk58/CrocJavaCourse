package ru.croc.task16.core.builders.abstractions;

import ru.croc.task16.core.entities.TaxiDriver;

public interface TaxiCabDriverBuilder {

	TaxiCabFinalBuilder DrivenBy(TaxiDriver driver);
}
