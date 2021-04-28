package com.snl.savemehomes.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLUtil {
	static public List<Map<String, String>> parse(String requestUrl) throws Exception {
		List<Map<String, String>> list = new ArrayList<>();
		
	      // 1. 빌더 팩토리 생성.
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        
        // 2. 빌더 팩토리로부터 빌더 생성
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        
        // 3. 빌더를 통해 XML 문서를 파싱해서 Document 객체로 가져온다.
        Document document = builder.parse(requestUrl);
        
        // 문서 구조 안정화 ?
        document.getDocumentElement().normalize();
        
        // XML 데이터 중 <person> 태그의 내용을 가져온다.
        NodeList personTagList = document.getElementsByTagName("item");
        
        // <person> 태그 리스트를 하나씩 돌면서 값들을 가져온다.
        for (int i = 0; i < personTagList.getLength(); ++i) {
            Map<String, String> m = new HashMap<>();
            // <person> 태그의 하위 노드들을 가져온다. ( 여기서 노드는 태그를 의미한다.)
            NodeList childNodes = personTagList.item(i).getChildNodes();
            for (int j = 0; j < childNodes.getLength(); ++j) {
            	m.put(childNodes.item(j).getNodeName().trim(), childNodes.item(j).getTextContent().trim());
            }
            list.add(m);
        }
		
		return list;
	}
}
