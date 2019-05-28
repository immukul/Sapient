package com.sopoteelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RowsFromInputFile {

	private static List<CSVInputData> data = new ArrayList<>();

	static  List<CSVInputData> csvRows(String file) throws IOException {
        BufferedReader reader = ReadWriteCsvFile.readfile(file);
        String rows = "";
        while ((rows = reader.readLine()) != null) {
            String[] columnArray = rows.split(",");
            CSVInputData dataObject = new CSVInputData();
            if (columnArray.length != 0) {
                dataObject.setCity(columnArray[0]);
                dataObject.setCountry(columnArray[1]==null?columnArray[0]:columnArray[1]);
                dataObject.setGender(columnArray[2]);
                dataObject.setCurrency(columnArray[3]);
                dataObject.setAverageIncome(columnArray[4].equals("") ? 0.0f : Float.parseFloat(columnArray[4]));
                data.add(dataObject);
            }
        }
        return data;
    }
}
