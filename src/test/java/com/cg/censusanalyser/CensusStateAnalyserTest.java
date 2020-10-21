package com.cg.censusanalyser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusStateAnalyserTest {
	
	private StateCensusAnalyser censusAnalyser;
	
	   public static final String STATE_CENSUS_FILE_PATH = "D:\\neeraj_workspace\\IndianCensusAnalyser1\\IndiaStateCensusData.csv";
	   public static final String STATE_EMPTY_FILE = "D:\\neeraj_workspace\\IndianCensusAnalyser1\\StateCensusData.csv";
	   
	   @Before
	   public void initialize() {
		    censusAnalyser = new StateCensusAnalyser(); 
	   }
	   
	   @Test
		public void givenStateCensusCSVFile_ShouldReturnNumberOfRecords() throws CensusAnalyserException {
			int noOfEntries = censusAnalyser.loadStateCsvData(STATE_CENSUS_FILE_PATH);
			Assert.assertEquals(29, noOfEntries);
		}	  
}
