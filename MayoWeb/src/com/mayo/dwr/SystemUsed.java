package com.mayo.dwr;

import com.mayo.transform.Files;
import com.mayo.transform.MyTransformer;

public class SystemUsed {

	
	public String getAllSystemsUsedXML(int visitID) {
		System.out.println("getAllSystemsUsedXML");
		String res = HTTPPoster.getInstance().get("SystemUsed/"+visitID);
		System.out.println(res);
		String res1 = MyTransformer.getInstance().styleDocumentFromData(res, Files.getInstance().SYSTEMUSED_XSL);
		System.out.println(res1);
		return res1;
		}
	
	public String createSystemUsed( String name, int visitID){
		System.out.println("createSystemUsed");
		StringBuilder sb = new StringBuilder();
		sb.append("<SystemUsed>");
		sb.append("<visitID>" + visitID + "</visitID>");
		sb.append("<name>" + name + "</name>");
		sb.append("</SystemUsed>");
		
		System.out.println(sb); 
		String res = HTTPPoster.getInstance().post("SystemUsed/",sb.toString());
		return res;
	}
	
	public String deleteSystemUsed(String name, int visitID){
		System.out.println("deleteSystemUsed");
		String res = HTTPPoster.getInstance().delete("SystemUsed/"+name+","+visitID);
		return res;
	}
	
	public String modifySystemUsed(String oldName, String name, String visitID){
		System.out.println("modifySystemUsed");
		StringBuilder sb = new StringBuilder();
		sb.append("<SystemUsed>");
		sb.append("<name>" + name + "</name>");
		sb.append("<oldname>" + oldName + "</oldname>");
		sb.append("</SystemUsed>");
		
		System.out.println(sb); 
		String res = HTTPPoster.getInstance().put("SystemUsed/"+visitID,sb.toString());
		return res;
	}
	
	
	
}
