package com.mayo.dwr;

import com.mayo.transform.Files;
import com.mayo.transform.MyTransformer;

public class ResearchStudy {
	
	public String getAllResearchStudiesXML(int visitID) {
		System.out.println("getAllResearchStudiesXML");
		String res = HTTPPoster.getInstance().get("ResearchStudy/"+visitID);
		System.out.println(res);
		String res1 = MyTransformer.getInstance().styleDocumentFromData(res, Files.getInstance().RESEARCHSTUDY_XSL);
		System.out.println(res1);
		return res1;
		}
	
	public String createResearchStudy(String name, String description, int visitID){
		System.out.println("createResearchStudy");
		StringBuilder sb = new StringBuilder();
		sb.append("<ResearchStudy>");
		sb.append("<name>" + name + "</name>");
		sb.append("<visitID>" + visitID + "</visitID>");
		sb.append("<description>" + description + "</description>");
		sb.append("</ResearchStudy>");
		
		System.out.println(sb); 
		String res = HTTPPoster.getInstance().post("ResearchStudy/",sb.toString());
		return res;
	}
	
	public String deleteResearchStudy(String name, String visitID){
		System.out.println("deleteResearchStudy");
		String res = HTTPPoster.getInstance().delete("ResearchStudy/"+name+","+visitID);
		return res;
	}
	
	public String modifyResearchStudy(String oldName, String name, String description, int visitID){
		System.out.println("modifyResearchStudy");
		StringBuilder sb = new StringBuilder();
		sb.append("<ResearchStudy>");
		sb.append("<name>" + name + "</name>");
		sb.append("<description>" + description + "</description>");
		sb.append("<oldname>" + oldName + "</oldname>");
		sb.append("</ResearchStudy>");
		
		System.out.println(sb); 
		String res = HTTPPoster.getInstance().put("ResearchStudy/"+visitID,sb.toString());
		return res;
	}
	
	
	
	
}
