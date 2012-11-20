package com.mayo.ws;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.mayo.db.Condition;
import com.mayo.db.ConditionRest;

@Path("/Condition")
public class ConditionWS {
	
	ConditionRest ConditionRest = com.mayo.db.ConditionRest.getInstance();

	
	
	@Path( "{clinicNum}" )
    @GET
	public String get(@PathParam("clinicNum") String clinicNum) {
		System.out.println("Clinic #: "+clinicNum);
		List<Condition> Conditions = ConditionRest.getAllCondition(clinicNum);
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(Condition Condition : Conditions) {
			Element ConditionElem = root.addElement("Condition");
			DocumentAssembler.assembleConditionElement(ConditionElem, Condition);			
		}
		
		return document.asXML();
		
	}
	
	
	
	@Path( "{clinicNum}" )
    @PUT
	public String put(@PathParam("clinicNum") String clinicNum, String content) {
		//System.out.println("Condition #: "+ConditionID);
		System.out.println(content);
		
		String result = SchemaValidator.validateCondition(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		
		try {
			Document document = DocumentHelper.parseText(content);
			
			String newname = document.selectSingleNode("//Condition/name").getText();
			String oldname = document.selectSingleNode("//Condition/oldname").getText();
			
			ConditionRest.put(oldname, newname, clinicNum);
			return "<result>"+"</OK>"+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}		
		
	}
	
	
	
    @POST
	public String post(String content) {
		System.out.println(content);
		String result = SchemaValidator.validateCondition(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		try {
			Document document = DocumentHelper.parseText(content);
			
			String clinicNum = document.selectSingleNode("//Condition/clinicNum").getText();
			String name = document.selectSingleNode("//Condition/name").getText();
			
			ConditionRest.post(name, clinicNum);
			return "<result>"+"<OK/>"+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}
	}
	

	
	@Path( "{ConditionID}" )
    @DELETE
	public String delete(@PathParam("ConditionID") String ConditionID) {
		System.out.println("Condition #: "+ConditionID);
		ConditionRest.delete(ConditionID.split(",")[0],ConditionID.split(",")[1]);
		return "delete response";
	}
}
