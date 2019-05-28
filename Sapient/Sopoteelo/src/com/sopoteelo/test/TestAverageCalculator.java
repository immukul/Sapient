package com.sopoteelo.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.sopoteelo.Average;
import com.sopoteelo.AverageCalculator;
import com.sopoteelo.CSVInputData;

public class TestAverageCalculator {

	@Test
	public void testConvertCurrency() {
		List<CSVInputData> list = getDummyList();
		AverageCalculator.convertCurrency(list);
		Assert.assertEquals(1.0f, list.get(0).getAverageIncome(),1.0);
	}

	@Test
	public void testAverageIncome() {
		List<CSVInputData> list = getDummyList();
		Map<String, Average> map = AverageCalculator.averageIncome(list);
		assertNotNull(map);
		assertNotNull(map.get("IND"));
		
	}

	@Test
	public void testOutputFile() {
		
		AverageCalculator.outputFile("D:/Code/VNFManager8/Sopoteelo/");
		File file = new File("D:/Code/VNFManager8/Sopoteelo/sampleOutput.csv");
		assertTrue(file.exists());
		
	}

	private List<CSVInputData> getDummyList(){
		List<CSVInputData> list = new ArrayList<>();
		CSVInputData csvInputData = new CSVInputData();
		csvInputData.setCity("NEW DELHI");
		csvInputData.setCountry("IND");
		csvInputData.setCurrency("INR");
		csvInputData.setGender("F");
		csvInputData.setAverageIncome(66.0f);
		list.add(csvInputData);
		return list;
	}
	
	
	
}
