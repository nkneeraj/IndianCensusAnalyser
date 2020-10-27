package com.cg.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCodeAnalyser {
	public int loadCSVFile(Path path) throws CensusException {
		try {
			Reader reader = Files.newBufferedReader(path);
			CsvToBean<StateCode> csvToBean = new CsvToBeanBuilder(reader).withType(StateCode.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			Iterator<StateCode> iterator = csvToBean.iterator();

			ArrayList<StateCode> stateCensusList = new ArrayList<StateCode>();
			while (iterator.hasNext()) {

				stateCensusList.add(iterator.next());

			}

			return stateCensusList.size();
		} catch (IOException e) {
			throw new CensusException("File not found", CensusException.ExceptionType.WRONG_CSV);
		} catch (RuntimeException e) {
			throw new CensusException("File internal data not valid", CensusException.ExceptionType.WRONG_HEADER);
		}
	}
}
