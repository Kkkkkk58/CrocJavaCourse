package ru.croc.task16.core.models;

import java.util.Collection;

public record OrderInformation(Coordinates place,
							   TaxiClass taxiClass,
							   Collection<SpecialService> specialServices) {

}
