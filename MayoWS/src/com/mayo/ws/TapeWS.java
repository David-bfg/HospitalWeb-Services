package com.mayo.ws;

import java.util.Date;
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

import com.mayo.db.Tape;
import com.mayo.db.TapeRest;

@Path("/Tape")
public class TapeWS {

	TapeRest TapeRest = com.mayo.db.TapeRest.getInstance();


    @GET
	public String get() {
		List<Tape> Tapes = TapeRest.getAll();
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(Tape Tape : Tapes) {
			Element TapeElem = root.addElement("Tape");
			DocumentAssembler.assembleTapeElement(TapeElem, Tape);			
		}
		
		return document.asXML();
	}
	
	
	
	@Path( "{param}" )
    @GET
	public String get(@PathParam("param") String param) {
		if(param.length()>5)
			return this.getFromClinicNum(param);
		else
			return this.getFromVisitID(param);
	}
	
	
	private String getFromVisitID(String visitID) {
		System.out.println("visitID #: "+visitID);
		List<Tape> Tapes  = TapeRest.getAllTapeForVisit(Integer.parseInt(visitID));
		
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(Tape Tape : Tapes) {
			Element TapeElem = root.addElement("Tape");
			DocumentAssembler.assembleTapeElement(TapeElem, Tape);			
		}
		
		return document.asXML();
	}

	
	
	private String getFromClinicNum(String clinicNum) {
		System.out.println("Clinic #: "+clinicNum);
		List<Tape> Tapes  = TapeRest.getAllTapeForClinicNum(clinicNum);
		
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(Tape Tape : Tapes) {
			Element TapeElem = root.addElement("Tape");
			DocumentAssembler.assembleTapeElement(TapeElem, Tape);			
		}
		
		return document.asXML();
	}



	@Path( "{tapeID}" )
    @PUT
	public String put(@PathParam("tapeID") String tapeID,String content) {
		System.out.println("Tape #: "+tapeID);
		System.out.println(content);
		
		String result = SchemaValidator.validate(content);
		if(!Boolean.parseBoolean(result))
		{
			//return "<error><desc>" + result + "</desc></error>";
		}
		
		try {
			Document document = DocumentHelper.parseText(content);
			
			int tapeIDInt = Integer.parseInt(tapeID);
			String clinicNum = document.selectSingleNode("//Tape/clinicNum").getText();
			int visitID = Integer.parseInt(document.selectSingleNode("//Tape/visitID").getText());
			int tapeNum = Integer.parseInt(document.selectSingleNode("//Tape/tapeNum").getText());
			int studyID = Integer.parseInt(document.selectSingleNode("//Tape/studyID").getText());
			String backup = document.selectSingleNode("//Tape/backup").getText();
			
			TapeRest.put(tapeIDInt, clinicNum, tapeNum, studyID, backup, visitID);
			return "<result>"+tapeID+"</result>"; 
			
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
			//return "<error><desc>" + result + "</desc></error>";
		}
		try {
			Document document = DocumentHelper.parseText(content);
			
			//int tapeID = Integer.parseInt(document.selectSingleNode("//Tape/tapeID").getText());
			String clinicNum = document.selectSingleNode("//Tape/clinicNum").getText();
			int visitID = Integer.parseInt(document.selectSingleNode("//Tape/visitID").getText());
			int tapeNum = Integer.parseInt(document.selectSingleNode("//Tape/tapeNum").getText());
			int studyID = Integer.parseInt(document.selectSingleNode("//Tape/studyID").getText());
			String backup = document.selectSingleNode("//Tape/backup").getText();
			
			TapeRest.post(clinicNum, tapeNum, studyID, backup, visitID);
			return "<result>"+"<OK/>"+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}
	}
	

	
	@Path( "{tapeID}" )
    @DELETE
	public String delete(@PathParam("tapeID") String tapeID) {
		System.out.println("Tape #: "+tapeID);
		TapeRest.delete(Integer.parseInt(tapeID));
		return "delete response";
	}
}
