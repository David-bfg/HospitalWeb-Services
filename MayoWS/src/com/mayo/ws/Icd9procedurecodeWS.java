package com.mayo.ws;

import java.util.Date;
import java.text.DateFormat;
import java.util.List;
import com.mayo.db.Icd9ProcedureCode;
import com.mayo.db.Icd9ProcedureCodeRest;

import org.dom4j.*;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/ICD9ProcedureCode")
public class Icd9procedurecodeWS {
	
	Icd9ProcedureCodeRest Icd9procedurecodeRest = com.mayo.db.Icd9ProcedureCodeRest.getInstance();


    @GET
	public String get() {
		List<Icd9ProcedureCode> Icd9procedurecodes = Icd9procedurecodeRest.getAll();
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(Icd9ProcedureCode Icd9procedurecode : Icd9procedurecodes) {
			Element Icd9procedurecodeElem = root.addElement("Icd9procedurecode");
			DocumentAssembler.assembleIcd9procedurecodeElement(Icd9procedurecodeElem, Icd9procedurecode);			
		}
		
		return document.asXML();
	}
	
	
	
	@Path( "{clinicNum}" )
    @GET
	public String get(@PathParam("clinicNum") String clinicNum) {
		System.out.println("Clinic #: "+clinicNum);
		List<Icd9ProcedureCode> Icd9procedurecodes = Icd9procedurecodeRest.getAllIcd9ProcedureCodes(clinicNum);
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(Icd9ProcedureCode Icd9procedurecode : Icd9procedurecodes) {
			Element Icd9procedurecodeElem = root.addElement("Icd9procedurecode");
			DocumentAssembler.assembleIcd9procedurecodeElement(Icd9procedurecodeElem, Icd9procedurecode);			
		}
		
		return document.asXML();
	}
	
	
	
	@Path( "{Icd9procedurecodeID}" )
    @PUT
	public String put(@PathParam("Icd9procedurecodeID") String Icd9procedurecodeID,String content) {
		System.out.println("Icd9procedurecode #: "+Icd9procedurecodeID);
		System.out.println(content);
		
		String result = SchemaValidator.validateICD9ProcedureCode(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		
		try {
			Document document = DocumentHelper.parseText(content);
			
			int cod = Integer.parseInt(Icd9procedurecodeID);
			
			String icd9ProcedureCode = document.selectSingleNode("//Icd9ProcedureCode/icd9ProcedureCode").getText();
			String clinicNum = document.selectSingleNode("//Icd9ProcedureCode/clinicNum").getText();
			String procedureDescriptor = document.selectSingleNode("//Icd9ProcedureCode/procedureDescriptor").getText();
			
			Icd9procedurecodeRest.put(cod, icd9ProcedureCode, clinicNum, procedureDescriptor);
			return "<result>"+Icd9procedurecodeID+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}			
		
	}
	
	
	
    @POST
	public String post(String content) {
		System.out.println(content);
		String result = SchemaValidator.validateICD9ProcedureCode(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		try {
			Document document = DocumentHelper.parseText(content);
			
			String icd9ProcedureCode = document.selectSingleNode("//Icd9ProcedureCode/icd9ProcedureCode").getText();
			String clinicNum = document.selectSingleNode("//Icd9ProcedureCode/clinicNum").getText();
			String procedureDescriptor = document.selectSingleNode("//Icd9ProcedureCode/procedureDescriptor").getText();
			
			Icd9procedurecodeRest.post(icd9ProcedureCode, clinicNum, procedureDescriptor);
			return "<result>"+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}
	}
	

	
	@Path( "{Icd9procedurecodeID}" )
    @DELETE
	public String delete(@PathParam("Icd9procedurecodeID") String Icd9procedurecodeID) {
		System.out.println("Icd9procedurecode #: "+Icd9procedurecodeID);
		Icd9procedurecodeRest.delete(Integer.parseInt(Icd9procedurecodeID));
		return "delete response";
	}

}
