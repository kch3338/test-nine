package main.java;

import java.util.List;

public class Converter {
	
	public String toJson(List<String> rows) {
		StringBuilder jsonStr = new StringBuilder();
		
		if(rows != null && rows.size() > 0) {
			String[] keys = rows.get(0).split(",");
			
			jsonStr.append("[\n");
			
			rows.remove(0);
			rows.stream()
				.filter(row -> !"".equals(row.trim()))
				.map(row -> row.split(","))
				.forEach(value -> {
					jsonStr.append("{\n");
				
					for(int i=0; i<keys.length; i++) {
						jsonStr.append("\"")
							   .append(keys[i])
							   .append("\":\"")
							   .append(value[i])
							   .append("\",\n");
					}
				
					jsonStr.replace(jsonStr.lastIndexOf(","), jsonStr.length(), "\n");
					jsonStr.append("},\n");
				});
				
			jsonStr.replace(jsonStr.lastIndexOf(","), jsonStr.length(), "\n");
			jsonStr.append("]");	
		}
		
		return jsonStr.toString();
	}
}
