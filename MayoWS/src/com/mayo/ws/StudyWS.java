package com.mayo.ws;

import java.util.Date;
import java.text.DateFormat;
import java.util.List;
import com.mayo.db.Study;
import com.mayo.db.StudyRest;

import org.dom4j.*;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/Study")
public class StudyWS {
	
	StudyRest StudyRest = com.mayo.db.StudyRest.getInstance();
	
	
	
	@Path( "{visitID}" )
    @GET
	public String get(@PathParam("visitID") String visitID) {
		System.out.println("visitID #: "+visitID);
		
		List<Study> Studys = StudyRest.getAllStudy(Integer.parseInt(visitID));
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(Study Study : Studys) {
			Element StudyElem = root.addElement("Study");
			DocumentAssembler.assembleStudyElement(StudyElem, Study);			
		}
		
		return document.asXML();
	}
	
	
	
	@Path( "{StudyID}" )
    @PUT
	public String put(@PathParam("StudyID") String visitID,String content) {
		//System.out.println("Study #: "+StudyID);
		System.out.println(content);
		
		String result = SchemaValidator.validateStudy(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		
		try {
			Document document = DocumentHelper.parseText(content);
			
			String name = document.selectSingleNode("//Study/studyName").getText();
			String oldname = document.selectSingleNode("//Study/oldStudyName").getText();
			
			StudyRest.put(oldname, name, Integer.parseInt(visitID));
			return "<result>"+"</OK>"+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}
		
	}
	
	
	
    @POST
	public String post(String content) {
		System.out.println(content);
		String result = SchemaValidator.validateStudy(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		try {
			Document document = DocumentHelper.parseText(content);
			
			int visitIDInt = Integer.parseInt(document.selectSingleNode("//Study/visitID").getText());
			String studyName = document.selectSingleNode("//Study/studyName").getText();
			
			StudyRest.post(studyName, visitIDInt);
			return "<result>"+"<OK/>"+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}
	}

	
	@Path( "{StudyID}" )
    @DELETE
	public String delete(@PathParam("StudyID") String StudyID) {
		System.out.println("Study #: "+StudyID);
		StudyRest.delete(StudyID.split(",")[0],StudyID.split(",")[1]);
		return "delete response";
	}

}
