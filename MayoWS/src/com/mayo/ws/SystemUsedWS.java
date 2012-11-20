package com.mayo.ws;

import java.util.Date;
import java.text.DateFormat;
import java.util.List;
import com.mayo.db.SystemUsed;
import com.mayo.db.SystemUsedRest;

import org.dom4j.*;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/SystemUsed")
public class SystemUsedWS {
	
	SystemUsedRest SystemUsedRest = com.mayo.db.SystemUsedRest.getInstance();

	
	@Path( "{visitID}" )
    @GET
	public String get(@PathParam("visitID") String visitID) {
		System.out.println("visitID #: "+visitID);
		List<SystemUsed> SystemUseds = SystemUsedRest.getAllSystemUsed(Integer.parseInt(visitID));
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(SystemUsed SystemUsed : SystemUseds) {
			Element SystemUsedElem = root.addElement("SystemUsed");
			DocumentAssembler.assembleSystemUsedElement(SystemUsedElem, SystemUsed);			
		}
		
		return document.asXML();
	}
	
	
	
	@Path( "{SystemUsedID}" )
    @PUT
	public String put(@PathParam("SystemUsedID") String visitID,String content) {
System.out.println(content);
		
		String result = SchemaValidator.validateSystemUsed(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		
		try {
			Document document = DocumentHelper.parseText(content);
			
			String name = document.selectSingleNode("//SystemUsed/name").getText();
			String oldname = document.selectSingleNode("//SystemUsed/oldname").getText();
			
			SystemUsedRest.put(oldname, name, Integer.parseInt(visitID));
			return "<result>"+"</OK>"+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}
	}
	
	
	
    @POST
	public String post(String content) {
		System.out.println(content);
		String result = SchemaValidator.validateSystemUsed(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		try {
			Document document = DocumentHelper.parseText(content);
			
			int visitIDInt = Integer.parseInt(document.selectSingleNode("//SystemUsed/visitID").getText());
			String name = document.selectSingleNode("//SystemUsed/name").getText();
			
			SystemUsedRest.post(name, visitIDInt);
			return "<result>"+"<OK/>"+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}
	}
	

	
	@Path( "{SystemUsedID}" )
    @DELETE
	public String delete(@PathParam("SystemUsedID") String SystemUsedID) {
		System.out.println("SystemUsed #: "+SystemUsedID);
		SystemUsedRest.delete(SystemUsedID.split(",")[0], Integer.parseInt(SystemUsedID.split(",")[1]));
		return "delete response";
	}

}
