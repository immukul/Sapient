package com.sopoteelo.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.sopoteelo.ReadWriteCsvFile;

public class TestReadWriteCsvFile {

	@Test
	public void TestReadLine(){
		assertNotNull(ReadWriteCsvFile.readfile("D:/Code/VNFManager8/Sopoteelo/sampleInput.csv"));
	}
	
}
