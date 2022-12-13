package ru.croc.task17.core.abstractions;

import java.io.BufferedReader;
import java.io.IOException;

public interface CsvReader<T> {

	Iterable<T> getFromCsv(BufferedReader csvStream) throws IOException;
}
