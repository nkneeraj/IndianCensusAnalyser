package com.cg.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	public int loadStateCsvData(String csvFilePath) throws CensusAnalyserException {
		if (!csvFilePath.contains(".csv")) {
			throw new CensusAnalyserException(".csv file not found", CensusAnalyserException.ExceptionType.WRONG_TYPE);
		}

		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			
			
			Iterator<CsvStateCensus> csvItr = this.getCSVFileIterator(reader, CsvStateCensus.class);
			Iterable<CsvStateCensus> csvIterable = () -> csvItr;
			int noOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return noOfEntries;

//			List<CsvStateCensus> list = new ArrayList<CsvStateCensus>();
//			while(csvItr.hasNext())
//				list.add(csvItr.next());
//			return list.size();			
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException("Data not according to format",
					CensusAnalyserException.ExceptionType.INTERNAL_ISSUE);
		}
	}

	public int loadStateCode(String csvFilePath) throws CensusAnalyserException {
		if (!csvFilePath.contains(".csv")) {
			throw new CensusAnalyserException(".csv file not found", CensusAnalyserException.ExceptionType.WRONG_TYPE);
		}

		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			Iterator<StateCodeCSV> csvItr = this.getCSVFileIterator(reader, StateCodeCSV.class);
			Iterable<StateCodeCSV> csvIterable = () -> csvItr;
			int noOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return noOfEntries;

		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException("Data not according to format",
					CensusAnalyserException.ExceptionType.INTERNAL_ISSUE);
		}
	}
	
	private <E> Iterator<E> getCSVFileIterator(Reader reader,
												Class<E> csvClass) throws CensusAnalyserException{
		try {
		CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
		CsvToBean<E> csvToBean = csvToBeanBuilder.withType(csvClass)
				.withIgnoreLeadingWhiteSpace(true).build();

		
		return csvToBean.iterator();
	
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.toString(),
					CensusAnalyserException.ExceptionType.INTERNAL_ISSUE);
		}
	}

}
