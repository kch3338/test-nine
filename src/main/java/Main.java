package main.java;

import java.io.IOException;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws IOException {
		FileUtils fileUtils = new FileUtils();
		Converter converter = new Converter();
		
		List<String> csvRows = fileUtils.readFile("top20.csv");
		if(csvRows == null) {
			throw new IOException(fileUtils.CSV_FILE_EXT + " 파일 읽기에 실패하였습니다.");
		}
		
		String jsonStr = converter.toJson(csvRows);
				
		String jsonFileName = fileUtils.writeJsonFile(jsonStr);
		
		List<String> jsonRows = fileUtils.readFile(jsonFileName);
		if(jsonRows == null) {
			throw new IOException(fileUtils.JSON_FILE_EXT + " 파일 읽기에 실패하였습니다.");
		}
		
		jsonRows.stream()
				.filter(row -> row.startsWith("\"licenseOrgan\""))
				.map(row -> row.replace("\"", ""))
				.map(row -> row.replace(",", ""))
				.map(row -> row.replace("licenseOrgan:", ""))
				.forEach(row -> {
					System.out.println(row);
				});
	}

}
