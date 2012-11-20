package com.mayo.dwr;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.mayo.transform.Files;
import com.mayo.transform.MyTransformer;

public class Search {
	
/*
 

 */
	static Document doc = null;
	static HashMap<String, Integer> map = null;
	
	public String processSearch(String body) {
		System.out.println(body);
		String res = "";
		if(body.equals("<filters></filters>")) {
			res = HTTPPoster.getInstance().get("Patient/");	
		}
		else
			res = HTTPPoster.getInstance().post("Search/",body);
		
		String res1 = MyTransformer.getInstance().styleDocumentFromData(res, Files.getInstance().PATIENT_XSL);
		System.out.println(res+"    "+res1);
		return res1;
	}
	
	public String getSearchHTML(String type) {
		initDoc();
		
			if(type.equalsIgnoreCase("None"))
				return "";
			
			StringBuilder sb = new StringBuilder();
			List<Node> nodes = doc.selectNodes("//"+type+"/*");
			
			for(Node n:nodes)
				{
				String s = n.asXML();
				sb.append(s);
				}
			System.out.println("TYPE:"+type +"\n" + fixme(type, sb.toString()));
			return fixme(type, sb.toString());
	}
	
	
	private String fixme(String type, String data) {
		updateIndex(type);
		int index = map.get(type);
		return data.replaceAll("dummy_name", type+"_"+index);
	}

	private void updateIndex(String type) {
		if(map.containsKey(type))
			map.put(type, map.get(type)+1);
		else
			map.put(type, 0);
	}


	private void initDoc() {
		if (doc == null) {
			File xml = new File(Files.getInstance().FILTERS);
			SAXReader reader = new SAXReader();
			try {
				doc = reader.read(xml);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			map = new HashMap<String, Integer>();
		}
	}
	
	public static void main(String[] args)
	{
		Search s = new Search();
		System.out.println(s.getSearchHTML("Gender"));
	}
}
