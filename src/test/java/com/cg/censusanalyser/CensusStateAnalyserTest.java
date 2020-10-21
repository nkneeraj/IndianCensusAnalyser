package com.cg.censusanalyser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusStateAnalyserTest {
	
	private StateCensusAnalyser censusAnalyser;
	
	   public static final String STATE_CENSUS_FILE_PATH = "D:\\neeraj_workspace\\IndianCensusAnalyser1\\IndiaStateCensusData.csv";
	   public static final String STATE_EMPTY_FILE = "D:\\neeraj_workspace\\IndianCensusAnalyser1\\StateCensusData.csv";
	   public static final String NOT_CSV_FILE = "D:\\neeraj_workspace\\IndianCensusAnalyser1\\Random.txt";
	   public static final String WRONG_HEADER = "D:\\neeraj_workspace\\IndianCensusAnalyser1\\WrongHeader.csv";
	   @Before
	   public void initialize() {
		    censusAnalyser = new StateCensusAnalyser(); 
	   }
	   
	   @Test
		public void givenStateCensusCSVFile_ShouldReturnNumberOfRecords() throws CensusAnalyserException {
			int noOfEntries = censusAnalyser.loadStateCsvData(STATE_CENSUS_FILE_PATH);
			Assert.assertEquals(29, noOfEntries);
		}	
	   
	   @Test
		public void givenStateCensus_WrongCSVFile_ShouldThrowException()  {
		   try {
			censusAnalyser.loadStateCsvData(NOT_CSV_FILE);
		   }catch(CensusAnalyserException e) {
			   Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_TYPE, e.type);
		   }
		}
	   
	   @Test
		public void givenStateCensus_WrongHeader_ShouldThrowException()  {
		   try {
			censusAnalyser.loadStateCsvData(WRONG_HEADER);
		   }catch(CensusAnalyserException e) {
			   Assert.assertEquals(CensusAnalyserException.ExceptionType.INTERNAL_ISSUE, e.type);
		   }
		}
}
