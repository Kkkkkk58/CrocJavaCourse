package ru.croc.task16.core.builders.abstractions;

import ru.croc.task16.core.models.Coordinates;

public interface TaxiCabLocationBuilder {

	TaxiCabDriverBuilder LocatedAt(Coordinates location);
}
