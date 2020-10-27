package com.cg.censusanalyser;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;

import org.junit.Test;

import junit.framework.Assert;

import com.google.gson.Gson;

@SuppressWarnings("deprecation")
public class StateCensusAnalyserTest {
	public static final String STATE_CENSUS_DATA = "IndiaStateCensusData.csv";
	public static final String WRONG_STATE_CENSUS_DATA = "src/main/java/com/StateCensusAnalyser/IndiaStateCensus.txt";
	public static final String WRONG_STATE_CENSUS_DATA_HEADER = "IndiaStateCode.csv";
	public static final String WRONG_STATE_CENSUS_DATA_TYPE = "IndiaStateCensus.txt";

	StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();

	@Test
	public void ensureNoOfRecordMatches() throws CensusException, CSVException {
		int records = stateCensusAnalyser.loadIndiaCensusData(Paths.get(STATE_CENSUS_DATA));
		Assert.assertEquals(29, records);
	}

	@Test
	public void checkWrongPath() throws CensusException, CSVException {
		try {
			stateCensusAnalyser.loadIndiaCensusData(Paths.get(WRONG_STATE_CENSUS_DATA));
		} catch (CensusException e) {
			Assert.assertEquals(CensusException.ExceptionType.WRONG_CSV, e.type);
		}
	}

	@Test
	public void checkWrongHeader() throws CensusException, CSVException {
		try {
			stateCensusAnalyser.loadIndiaCensusData(Paths.get(WRONG_STATE_CENSUS_DATA_HEADER));
		} catch (CensusException e) {
			Assert.assertEquals(CensusException.ExceptionType.WRONG_CSV, e.type);
			;
		}
	}

	@Test
	public void checkWrongType() throws CensusException, CSVException {
		try {
			stateCensusAnalyser.loadIndiaCensusData(Paths.get(WRONG_STATE_CENSUS_DATA_TYPE));
		} catch (CensusException e) {
			Assert.assertEquals(CensusException.ExceptionType.WRONG_CSV, e.type);
			;
		}
	}

	@Test
	public void checkSorted() throws CensusException, CSVException {
		try {
			stateCensusAnalyser.loadIndiaCensusData(Paths.get(STATE_CENSUS_DATA));
			String sortedCensusData = stateCensusAnalyser.getSortedCensuByState();
			CSVStateCensus[] censusCsv = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
			Assert.assertEquals("Andhra Pradesh", censusCsv[0].state);
		} catch (CensusException e) {
		}
	}

	@Test
	public void censusSortedOnState() throws CensusException {
		stateCensusAnalyser.loadIndiaCensusData(Paths.get(STATE_CENSUS_DATA));
		String sortedCensusData = stateCensusAnalyser.getSortedCensuByState();
		CSVStateCensus[] censusCsv = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		Assert.assertEquals("Andhra Pradesh", censusCsv[0].state);
	}
	
	@Test
	public void censusSortedOnStateDensity() throws CensusException {
		stateCensusAnalyser.loadIndiaCensusData(Paths.get(STATE_CENSUS_DATA));
		String sortedCensusData = stateCensusAnalyser.getStatePopulationDensityWiseSortedCensusData();
		CSVStateCensus[] censusCsv = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		Assert.assertEquals("Bihar", censusCsv[28].state);
	}
	
	@Test
	   public void censusSortedOnStateArea() throws CensusException {
		  stateCensusAnalyser.loadIndiaCensusData(Paths.get(STATE_CENSUS_DATA));
		  String sortedCensus = stateCensusAnalyser.getAreaWiseData();
		  CSVStateCensus[] censusSortedCsv = new Gson().fromJson(sortedCensus, CSVStateCensus[].class);
		  Assert.assertEquals("Rajasthan", censusSortedCsv[28].state);
	   }
}