package com.mayo.dwr;

import com.mayo.transform.Files;
import com.mayo.transform.MyTransformer;

public class Study {

	
	public String getAllStudiesXML(int visitID) {
		System.out.println("getAllStudiesXML");
		String res = HTTPPoster.getInstance().get("Study/"+visitID);
		System.out.println(res);
		String res1 = MyTransformer.getInstance().styleDocumentFromData(res, Files.getInstance().STUDY_XSL);
		System.out.println(res1);
		return res1;
		}
	
	public String createStudy( String studyName, int visitID){
		System.out.println("createStudy");
		StringBuilder sb = new StringBuilder();
		sb.append("<Study>");
		sb.append("<studyName>" + studyName + "</studyName>");
		sb.append("<visitID>" + visitID + "</visitID>");
		sb.append("</Study>");
		
		System.out.println(sb); 
		String res = HTTPPoster.getInstance().post("Study/",sb.toString());
		return res;
	}
	
	public String deleteStudy(String name, int visitID){
		System.out.println("deleteStudy");
		name = name.replaceAll(" ", "%20");
		String res = HTTPPoster.getInstance().delete("Study/"+name+","+visitID);
		return res;
	}
	
	public String modifyStudy(String oldStudyName, String studyName, int visitID){
		System.out.println("modifyStudy");
		StringBuilder sb = new StringBuilder();
		sb.append("<Study>");
		sb.append("<studyName>" + studyName + "</studyName>");
		sb.append("<oldStudyName>" + oldStudyName + "</oldStudyName>");
		sb.append("</Study>");
		
		System.out.println(sb); 
		String res = HTTPPoster.getInstance().put("Study/"+visitID,sb.toString());
		return res;
	}
	
	
	
}
