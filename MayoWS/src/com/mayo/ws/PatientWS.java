package com.mayo.ws;

import java.util.Date;
import java.text.DateFormat;
import java.util.List;
import com.mayo.db.Patient;
import com.mayo.db.PatientRest;

import org.dom4j.*;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/Patient")
public class PatientWS {

	PatientRest patientRest = PatientRest.getInstance();


    @GET
	public String get() {
		List<Patient> patients = patientRest.getAll();
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(Patient patient : patients) {
			Element patientElem = root.addElement("Patient");
			DocumentAssembler.assemblePatientElement(patientElem, patient);			
		}
		
		return document.asXML();
	}
	
	
	
	@Path( "{clinicNum}" )
    @GET
	public String get(@PathParam("clinicNum") String clinicNum) {
		System.out.println("Clinic #: "+clinicNum);
		Patient patient = patientRest.get(Integer.parseInt(clinicNum));
		
		Document document = DocumentHelper.createDocument();
		Element patientElem = document.addElement("Patient");
		DocumentAssembler.assemblePatientElement(patientElem, patient);
		
		return document.asXML();
	}
	
	
	
	@Path( "{clinicNum}" )
    @PUT
	public String put(@PathParam("clinicNum") String clinicNum,String content) {
		System.out.println("Clinic #: "+clinicNum);
		System.out.println(content);
		
		String result = SchemaValidator.validate(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		
		try {
			Document document = DocumentHelper.parseText(content);
			
			int clinicNumInt = Integer.parseInt(clinicNum);
			String lastName = document.selectSingleNode("//Patient/lastname").getText();
			String firstName = document.selectSingleNode("//Patient/firstname").getText();
			String dobStr = document.selectSingleNode("//Patient/dob").getText();
			Date dob = DateTypeConverter.getDate(dobStr);
			char gender = document.selectSingleNode("//Patient/gender").getText().toCharArray()[0];
			Double height = Double.parseDouble(document.selectSingleNode("//Patient/height").getText());
			Double weight = Double.parseDouble(document.selectSingleNode("//Patient/weight").getText());
			char side = document.selectSingleNode("//Patient/side").getText().toCharArray()[0];
			char extremity = document.selectSingleNode("//Patient/extremity").getText().toCharArray()[0];
			char involved = document.selectSingleNode("//Patient/involved").getText().toCharArray()[0];
			char dominant = document.selectSingleNode("//Patient/dominant").getText().toCharArray()[0];
			String measuredSide = document.selectSingleNode("//Patient/measuredSide").getText();
			String problemDescriptor = document.selectSingleNode("//Patient/problemDescriptor").getText();
			
			patientRest.update(clinicNumInt, firstName, lastName, dob, extremity, gender, side, height, weight, involved, dominant, measuredSide, problemDescriptor);
			return "<result>"+clinicNum+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}			
		
	}
	
	
	
    @POST
	public String post(String content) {
		System.out.println(content);
		String result = SchemaValidator.validate(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		try {
			Document document = DocumentHelper.parseText(content);
			
			int clinicNumInt = Integer.parseInt(document.selectSingleNode("//Patient/clinicNum").getText());
			String lastName = document.selectSingleNode("//Patient/lastname").getText();
			String firstName = document.selectSingleNode("//Patient/firstname").getText();
			String dobStr = document.selectSingleNode("//Patient/dob").getText();
			Date dob = DateTypeConverter.getDate(dobStr);
			char gender = document.selectSingleNode("//Patient/gender").getText().toCharArray()[0];
			Double height = Double.parseDouble(document.selectSingleNode("//Patient/height").getText());
			Double weight = Double.parseDouble(document.selectSingleNode("//Patient/weight").getText());
			char side = document.selectSingleNode("//Patient/side").getText().toCharArray()[0];
			char extremity = document.selectSingleNode("//Patient/extremity").getText().toCharArray()[0];
			char involved = document.selectSingleNode("//Patient/involved").getText().toCharArray()[0];
			char dominant = document.selectSingleNode("//Patient/dominant").getText().toCharArray()[0];
			String measuredSide = document.selectSingleNode("//Patient/measuredSide").getText();
			String problemDescriptor = document.selectSingleNode("//Patient/problemDescriptor").getText();
			
			patientRest.post(clinicNumInt, firstName, lastName, dob, extremity, gender, side, height, weight, involved, dominant, measuredSide, problemDescriptor);
			return "<result>"+clinicNumInt+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}
	}
	

	
	@Path( "{clinicNum}" )
    @DELETE
	public String delete(@PathParam("clinicNum") String clinicNum) {
		System.out.println("Clinic #: "+clinicNum);
		patientRest.delete(Integer.parseInt(clinicNum));
		return "delete response";
	}

}