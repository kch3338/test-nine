package main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUtils {
	
	public final String CSV_FILE_EXT 	= "csv";
	public final String JSON_FILE_EXT 	= "json";
	public final String CSV_FILE_PATH 	= "src/main/resources/csv/";
	public final String JSON_FILE_PATH 	= "src/main/resources/json/";
	
	public File getFile(String fileName, String fileExt) throws FileNotFoundException {
		String filePath = (CSV_FILE_EXT.equals(fileExt) ? CSV_FILE_PATH : JSON_FILE_PATH) + fileName;
		
		File file = new File(filePath);
		if(!file.exists()) {
			throw new FileNotFoundException(filePath + " 경로에 파일이 존재하지 않습니다.");
		}
		
		return file;
	}
	
	public String generateFileName(String fileExt) {
		StringBuilder sb = new StringBuilder();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmsss");
		Date date = new Date();
		
		sb.append(formatter.format(date));
		
		for(int i=0; i<5; i++) {
			char ranChar = (char) ((int)(Math.random() * 26) + 97);
			
			sb.append(ranChar);
		}
		
		sb.append(".");
		sb.append(fileExt);
		
		return sb.toString();
	}
	
	public List<String> readFile(String fileName) {
		List<String> rows = new ArrayList<String>();
		
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		
		BufferedReader br = null;
		try {
			File file = getFile(fileName, fileExt);
			
			br = new BufferedReader(new FileReader(file));
			
			String line;
			while((line = br.readLine()) != null) {
				rows.add(line);
				
				if(CSV_FILE_EXT.equals(fileExt)) {
					System.out.println(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			
			return null;
		} finally {
			try {
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return rows;
	}
	
	public String writeJsonFile(String jsonStr) {
		String fileName = generateFileName(JSON_FILE_EXT);
		File file = new File(JSON_FILE_PATH + fileName);
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
			
			return null;
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return fileName;
	}
	
}
