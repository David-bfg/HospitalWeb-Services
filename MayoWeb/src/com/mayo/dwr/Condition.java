package com.mayo.dwr;

import com.mayo.transform.Files;
import com.mayo.transform.MyTransformer;

public class Condition {
	
	
	public String getAllConditionsXML(String clinicNum) {
		System.out.println("getAllConditionsXML");
		String res = HTTPPoster.getInstance().get("Condition/"+clinicNum);
		System.out.println(res);
		String res1 = MyTransformer.getInstance().styleDocumentFromData(res, Files.getInstance().CONDITION_XSL);
		System.out.println(res1);
		return res1;
		}
	
	public String createCondition(String name, String clinicNum){
		System.out.println("createCondition");
		StringBuilder sb = new StringBuilder();
		sb.append("<Condition>");
		sb.append("<name>" + name + "</name>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("</Condition>");
		
		System.out.println(sb); 
		String res = HTTPPoster.getInstance().post("Condition/",sb.toString());
		return res;
	}
	
	
	public String deleteCondition(String clinicNum, String name){
		System.out.println("deleteCondition");
		name = name.replaceAll(" ", "%20");
		String res = HTTPPoster.getInstance().delete("Condition/"+name+","+clinicNum);
		return res;
	}
	
	public String modifyCondition(String oldname, String name, String clinicNum){
		System.out.println("modifyCondition");
		StringBuilder sb = new StringBuilder();
		sb.append("<Condition>");
		sb.append("<name>" + name + "</name>");
		sb.append("<oldname>" + oldname + "</oldname>");
		sb.append("</Condition>");
		
		String res = HTTPPoster.getInstance().put("Condition/"+clinicNum,sb.toString());
		return res;
	}
}
