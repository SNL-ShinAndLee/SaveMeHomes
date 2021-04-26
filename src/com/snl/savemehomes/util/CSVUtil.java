package com.snl.savemehomes.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
	
	static public List<String[]> parse(String filePath) {
		List<String[]> dataList = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {	
			String line = null;
			br.readLine(); //맨위 제목줄 생략
			
			while ((line = br.readLine()) != null) {
				String[] parsed = line.split(",");
				dataList.add(parsed);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dataList;
	}
}
