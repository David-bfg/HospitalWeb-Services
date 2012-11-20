package com.mayo.dwr;

import com.mayo.transform.Files;
import com.mayo.transform.MyTransformer;

public class Tape {

	
	public String getAllTapesXML(String clinicNum) {
		System.out.println("getAllTapesXML");
		String res = HTTPPoster.getInstance().get("Tape/"+clinicNum);
		System.out.println(res);
		String res1 = MyTransformer.getInstance().styleDocumentFromData(res, Files.getInstance().TAPE_XSL);
		System.out.println(res1);
		return res1;
		}
	
	public String createTape(int clinicNum, int visitID, int tapeNum, int studyID, int backup){
		System.out.println("createTape");
		StringBuilder sb = new StringBuilder();
		sb.append("<Tape>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("<visitID>" + visitID + "</visitID>");
		sb.append("<tapeNum>" + tapeNum + "</tapeNum>");
		sb.append("<studyID>" + studyID + "</studyID>");
		sb.append("<backup>" + backup + "</backup>");
		sb.append("</Tape>");
		
		System.out.println(sb); 
		String res = HTTPPoster.getInstance().post("Tape/",sb.toString());
		return res;
	}
	
	public String deleteTape(int tapeID){
		System.out.println("deleteTape");
		String res = HTTPPoster.getInstance().delete("Tape/"+tapeID);
		return res;
	}
	
	public String modifyTape(int tapeID, int clinicNum, int visitID, int tapeNum, int studyID, int backup){
		System.out.println("modifyTape");
		StringBuilder sb = new StringBuilder();
		sb.append("<Tape>");
		sb.append("<tapeID>" + tapeID + "</tapeID>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("<visitID>" + visitID + "</visitID>");
		sb.append("<tapeNum>" + tapeNum + "</tapeNum>");
		sb.append("<studyID>" + studyID + "</studyID>");
		sb.append("<backup>" + backup + "</backup>");
		sb.append("</Tape>");
		
		String res = HTTPPoster.getInstance().put("Tape/"+tapeID,sb.toString());
		return res;
	}
}
	

