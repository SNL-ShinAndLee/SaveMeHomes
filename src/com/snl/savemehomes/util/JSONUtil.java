package com.snl.savemehomes.util;

import java.util.HashMap;
import java.util.Map;

public class JSONUtil {
	static public Map<String, String> parse(String JSONString){
		Map<String, String> map = new HashMap<String,String>();
		String json = JSONString.substring(2, JSONString.length()-2);		
		String[] parts = json.replaceAll("^\\{|\\}$","").split("\"?(:|,)(?![^\\{]*\\})\"?");
		for (int i = 0; i < parts.length -1; i+=2)
			map.put(parts[i], parts[i+1]);
		
		return map;
	}
}
