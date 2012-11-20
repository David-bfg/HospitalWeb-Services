package com.mayo.ws;

import java.util.Date;
import java.text.DateFormat;
import java.util.List;
import com.mayo.db.Patient;
import com.mayo.db.PatientRest;
import com.mayo.db.VisitRest;

import org.dom4j.*;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.mayo.db.Visit;

@Path("/Visit")
public class VisitWS {
	
	VisitRest visitRest = VisitRest.getInstance();


    @GET
	public String get() {
    	List<Visit> visits = visitRest.getAll();
    	
    	Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(Visit visit : visits) {
			Element patientElem = root.addElement("Visit");
			DocumentAssembler.assembleVisitElement(patientElem, visit);			
		}
		
		return document.asXML();
		
	}
	
	
    public String getVisitsWithClinic(String clinicNum) {
    	List<Visit> visits = visitRest.getAllVisits(clinicNum);
    	
    	Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(Visit visit : visits) {
			Element patientElem = root.addElement("Visit");
			DocumentAssembler.assembleVisitElement(patientElem, visit);			
		}
		
		return document.asXML();
		
	}
	
	
	@Path( "{visitID}" )
    @GET
	public String get(@PathParam("visitID") String visitID) {
		if(visitID.length()>5)
		{
			return this.getVisitsWithClinic(visitID);
		}
		
		Visit visit = visitRest.get(Integer.parseInt(visitID));
		
		Document document = DocumentHelper.createDocument();
		Element visitElem = document.addElement("Visit");
		DocumentAssembler.assembleVisitElement(visitElem, visit);
		
		return document.asXML();
	}
	
	
	
	@Path( "{visitID}" )
    @PUT
	public String put(@PathParam("visitID") String visitID,String content) {
		//Don't have a xml style sheet yet for Visit
		
		String result = SchemaValidator.validateVisit(content);
		if(!Boolean.parseBoolean(result))
		{
			System.out.println(result);
			return "<error><desc>" + result + "</desc></error>";
		}
		
		
		int visitIDint = Integer.parseInt(visitID);
		
		try {
			Document document = DocumentHelper.parseText(content);
			
			String dateStr = document.selectSingleNode("//Visit/date").getText();
			Date date = DateTypeConverter.getDate(dateStr);
			
			int visitNum = Integer.parseInt(document.selectSingleNode("//Visit/visitNum").getText());
			
			int clinicNum = Integer.parseInt(document.selectSingleNode("//Visit/clinicNum").getText());
			
			String provider = document.selectSingleNode("//Visit/provider").getText();
			
			String kinesiologist = document.selectSingleNode("//Visit/kinesiologist").getText();
			
			String dateProcessingCompleteStr = document.selectSingleNode("//Visit/dateProcessingComplete").getText();
			Date dateProcessingComplete = DateTypeConverter.getDate(dateProcessingCompleteStr);
			
			String physicalTherapist = document.selectSingleNode("//Visit/physicalTherapist").getText();
			
			String dateAnalysisCompleteStr = document.selectSingleNode("//Visit/dateAnalysisComplete").getText();
			Date dateAnalysisComplete = DateTypeConverter.getDate(dateAnalysisCompleteStr);
			
			visitRest.put(visitIDint, date, visitNum, clinicNum, provider, kinesiologist, dateProcessingComplete, physicalTherapist, dateAnalysisComplete);
			
			return "<result>"+clinicNum+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}			
	}
	
	
	
    @POST
	public String post(String content) {
    	System.out.println("POSTING");
    	System.out.println(content);
    	
    	 String result = SchemaValidator.validateVisit(content);
		if(!Boolean.parseBoolean(result))
		{
			System.out.println(result);
			return "<error><desc>" + result + "</desc></error>";
		}
		
    	try {
			Document document = DocumentHelper.parseText(content);
			
			String dateStr = document.selectSingleNode("//Visit/date").getText();
			Date date = DateTypeConverter.getDate(dateStr);
			
			int clinicNum = Integer.parseInt(document.selectSingleNode("//Visit/clinicNum").getText());
			
			String provider = document.selectSingleNode("//Visit/provider").getText();
			
			String kinesiologist = document.selectSingleNode("//Visit/kinesiologist").getText();
			
			String dateProcessingCompleteStr = document.selectSingleNode("//Visit/dateProcessingComplete").getText();
			Date dateProcessingComplete = DateTypeConverter.getDate(dateProcessingCompleteStr);
			
			String physicalTherapist = document.selectSingleNode("//Visit/physicalTherapist").getText();
			
			String dateAnalysisCompleteStr = document.selectSingleNode("//Visit/dateAnalysisComplete").getText();
			Date dateAnalysisComplete = DateTypeConverter.getDate(dateAnalysisCompleteStr);
			
			visitRest.post(date, clinicNum, provider, kinesiologist, dateProcessingComplete, physicalTherapist, dateAnalysisComplete);
			
			return "<result>"+clinicNum+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}			
	}
	

	
	@Path( "{visitID}" )
    @DELETE
	public String delete(@PathParam("visitID") String visitID) {
		visitRest.delete(Integer.parseInt(visitID));
		return "delete response";
	}
	

}