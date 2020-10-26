package com.cg.censusanalyser;

public class CsvBuilderFactory {
public static ICsvBuilder createCsvBuilder() {
	return new CommonCSVBuilder<>();
}
}
