package com.mayo.ws;

import java.util.Date;
import java.text.DateFormat;
import java.util.List;
import com.mayo.db.Icd9Diagnostic;
import com.mayo.db.Icd9DiagnosticRest;

import org.dom4j.*;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/ICD9Diagnostic")
public class Icd9DiagnosticWS {

	Icd9DiagnosticRest Icd9DiagnosticRest = com.mayo.db.Icd9DiagnosticRest.getInstance();


    @GET
	public String get() {
		List<Icd9Diagnostic> Icd9Diagnostics = Icd9DiagnosticRest.getAll();
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(Icd9Diagnostic Icd9Diagnostic : Icd9Diagnostics) {
			Element Icd9DiagnosticElem = root.addElement("Icd9Diagnostic");
			DocumentAssembler.assembleIcd9DiagnosticElement(Icd9DiagnosticElem, Icd9Diagnostic);			
		}
		
		return document.asXML();
	}
	
	
	
	@Path( "{clinicNum}" )
    @GET
	public String get(@PathParam("clinicNum") String clinicNum) {
		System.out.println("Clinic #: "+clinicNum);
		List<Icd9Diagnostic> Icd9Diagnostics = Icd9DiagnosticRest.getAllIcd9Diagnostics(clinicNum);
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(Icd9Diagnostic Icd9Diagnostic : Icd9Diagnostics) {
			Element Icd9DiagnosticElem = root.addElement("Icd9Diagnostic");
			DocumentAssembler.assembleIcd9DiagnosticElement(Icd9DiagnosticElem, Icd9Diagnostic);			
		}
		
		return document.asXML();
	}
	
	
	
	@Path( "{Icd9DiagnosticID}" )
    @PUT
	public String put(@PathParam("Icd9DiagnosticID") String Icd9DiagnosticID,String content) {
		System.out.println("Icd9Diagnostic #: "+Icd9DiagnosticID);
		System.out.println(content);
		
		String result = SchemaValidator.validateICD9Diagnostic(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		
		try {
			Document document = DocumentHelper.parseText(content);
			
			int Icd9DiagnosticIDInt = Integer.parseInt(Icd9DiagnosticID);
			String icd9Diagnostic = document.selectSingleNode("//Icd9Diagnostic/icd9Diagnostic").getText();
			String clinicNum = document.selectSingleNode("//Icd9Diagnostic/clinicNum").getText();
			
			Icd9DiagnosticRest.put(Icd9DiagnosticIDInt, icd9Diagnostic, clinicNum);
			return "<result>"+Icd9DiagnosticID+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}			
		
	}
	
	
	
    @POST
	public String post(String content) {
		System.out.println(content);
		String result = SchemaValidator.validateICD9Diagnostic(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		try {
			Document document = DocumentHelper.parseText(content);
			
			String icd9Diagnostic = document.selectSingleNode("//Icd9Diagnostic/icd9Diagnostic").getText();
			String clinicNum = document.selectSingleNode("//Icd9Diagnostic/clinicNum").getText();
			
			Icd9DiagnosticRest.post(icd9Diagnostic, clinicNum);
			return "<result>"+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}
	}
	

	
	@Path( "{Icd9DiagnosticID}" )
    @DELETE
	public String delete(@PathParam("Icd9DiagnosticID") String Icd9DiagnosticID) {
		System.out.println("Icd9Diagnostic #: "+Icd9DiagnosticID);
		Icd9DiagnosticRest.delete(Integer.parseInt(Icd9DiagnosticID));
		return "delete response";
	}

}
