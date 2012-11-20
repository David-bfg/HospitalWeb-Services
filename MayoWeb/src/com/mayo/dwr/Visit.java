package com.mayo.dwr;

import java.util.Date;

import com.mayo.transform.Files;
import com.mayo.transform.MyTransformer;

public class Visit {

	public String getVisits(int id){
		String res = HTTPPoster.getInstance().get("Visit/" + id);
		return res;
	}
	
	public String getAllVisitsXML(String clinicNum) {
		System.out.println("getAllVisitsXML");
		String res = HTTPPoster.getInstance().get("Visit/"+clinicNum);
		System.out.println(res);
		String res1 = MyTransformer.getInstance().styleDocumentFromData(res, Files.getInstance().VISIT_XSL);
		System.out.println(res1);
		return res1;
		}
	
	public String createVisit(String date, int clinicNum, 
			String provider, String kinesiologist, String dateProcessingComplete, 
			String physicalTherapist, String dateAnalysisComplete){
		System.out.println("createVisit");
		StringBuilder sb = new StringBuilder();
		sb.append("<Visit>");
		sb.append("<date>" + date + "</date>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("<provider>" + provider + "</provider>");
		sb.append("<kinesiologist>" + kinesiologist + "</kinesiologist>");
		sb.append("<dateProcessingComplete>" + dateProcessingComplete + 
				"</dateProcessingComplete>");
		sb.append("<physicalTherapist>" + physicalTherapist + "</physicalTherapist>");
		sb.append("<dateAnalysisComplete>" + dateAnalysisComplete + 
				"</dateAnalysisComplete>");
		sb.append("</Visit>");
		
		System.out.println(sb); 
		String res = HTTPPoster.getInstance().post("Visit/",sb.toString());
		return res;
	}
	
	public String deleteVisit(int visitID){
		System.out.println("deleteVisit");
		String res = HTTPPoster.getInstance().delete("Visit/"+visitID);
		return res;
	}
	
	public String modifyVisit(int visitID, String date, int visitNum, int clinicNum, 
			String provider, String kinesiologist, String dateProcessingComplete, 
			String physicalTherapist, String dateAnalysisComplete){
		System.out.println("modifyVisitData");
		StringBuilder sb = new StringBuilder();
		sb.append("<Visit>");
		sb.append("<visitID>" + visitID + "</visitID>");
		sb.append("<date>" + date + "</date>");
		sb.append("<visitNum>" + visitNum + "</visitNum>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("<provider>" + provider + "</provider>");
		sb.append("<kinesiologist>" + kinesiologist + "</kinesiologist>");
		sb.append("<dateProcessingComplete>" + dateProcessingComplete + 
				"</dateProcessingComplete>");
		sb.append("<physicalTherapist>" + physicalTherapist + "</physicalTherapist>");
		sb.append("<dateAnalysisComplete>" + dateAnalysisComplete + 
				"</dateAnalysisComplete>");
		sb.append("</Visit>");
		
		String res = HTTPPoster.getInstance().put("Visit/"+visitID,sb.toString());
		return res;
	}
}
