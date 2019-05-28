package com.sopoteelo;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AverageCalculator {

	public static void convertCurrency(List<CSVInputData> list) {
		for (CSVInputData data : list) {
			switch (data.getCurrency()) {
			case Currency.INR:
				data.setAverageIncome(data.getAverageIncome() / 66f);
				break;
			case Currency.GBP:
				data.setAverageIncome(data.getAverageIncome() / 0.67f);
				break;
			case Currency.SGD:
				data.setAverageIncome(data.getAverageIncome() / 1.5f);
				break;
			case Currency.HKD:
				data.setAverageIncome(data.getAverageIncome() / 8f);
				break;
			case Currency.USD:
				data.setAverageIncome(data.getAverageIncome());
				break;
			}
		}
	}

	public static Map<String, Average> averageIncome(List<CSVInputData> list) {
		Map<String, Average> countryMap = new HashMap<String, Average>();
		list.forEach(data -> {
			int count = 0;
			if (data.getCountry().equals("")) {
				{
					Average avg = countryMap.get(data.getCity() + "," + data.getGender()) == null ? new Average(0, 0.0f)
							: countryMap.get(data.getCity() + "," + data.getGender());
					avg.setAvg(avg.getAvg() + avg.getAvg() + data.getAverageIncome());
					avg.setCount(avg.getCount() + 1);
					countryMap.put(data.getCity() + "," + data.getGender(), avg);
				}
			} else {
				if (countryMap.containsKey(data.getCountry() + "," + data.getGender())) {
					Average avg = countryMap.get(data.getCountry() + "," + data.getGender()) == null
							? new Average(0, 0.0f) : countryMap.get(data.getCountry() + "," + data.getGender());
					avg.setAvg(avg.getAvg() + data.getAverageIncome());
					avg.setCount(avg.getCount() + 1);

					countryMap.put(data.getCountry() + "," + data.getGender(), avg);
				} else {
					countryMap.put(data.getCountry() + "," + data.getGender(), new Average(1, data.getAverageIncome()));
				}
			}

		});
		return countryMap;
	}

	public static void outputFile(String path) {
		try {
			// reading data rowwise
			List<CSVInputData> data = RowsFromInputFile.csvRows(path + "sampleInput.csv");
			convertCurrency(data);
			data.sort(Comparator.comparing(CSVInputData::getCountry));
			Map<String, Average> map = averageIncome(data);
			map.forEach((K, V) -> V.setAvg(V.getAvg() / V.getCount()));
			Map<String, Average> map1 = map.entrySet().stream().sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
							LinkedHashMap::new));
			ReadWriteCsvFile.writeCSV(path + "sampleOutput.csv", map);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		outputFile("D:/Code/VNFManager8/Sopoteelo/");

	}
}
