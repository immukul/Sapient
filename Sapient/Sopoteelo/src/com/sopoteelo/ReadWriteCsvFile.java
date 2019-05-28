package com.sopoteelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ReadWriteCsvFile {
	
	public static BufferedReader readfile(String path) {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(path));
			bufferedReader.readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedReader;
	}

	public static boolean writeCSV(String path, Map<String, Average> countryMap) {
	        File fileout = new File(path);
	        try {
	            FileOutputStream outfile = new FileOutputStream(fileout);
	            String msg = "City/Country Gender Average Income(in USD)";
	            FileWriter fw = new FileWriter(path);
	            fw.append(msg);
	            fw.append("\n");
	            for (Map.Entry<String, Average> ms : countryMap.entrySet()) {

	                String key = ms.getKey();
	                Average value = ms.getValue();
	                String countryGen[] = key.split(",");
	                String recard = countryGen[0] + " " + countryGen[1] + " " + value.getAvg();
	                fw.append(recard);
	                fw.append("\n");
	            }
	            fw.flush();
	            fw.close();
	            return true;

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            return false;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return true;
	    }
}
