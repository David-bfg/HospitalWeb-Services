package com.mayo.ws;

import java.util.Date;
import java.text.DateFormat;
import java.util.List;
import com.mayo.db.ResearchStudy;
import com.mayo.db.ResearchStudyRest;

import org.dom4j.*;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/ResearchStudy")
public class ResearchStudyWS {
	

	ResearchStudyRest ResearchStudyRest = com.mayo.db.ResearchStudyRest.getInstance();	

	
	@Path( "{visitID}" )
    @GET
	public String get(@PathParam("visitID") String visitID) {
		System.out.println("visitID #: "+visitID);
		List<ResearchStudy> ResearchStudys = ResearchStudyRest.getAllResearchStudy(Integer.parseInt(visitID));
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(ResearchStudy ResearchStudy : ResearchStudys) {
			Element ResearchStudyElem = root.addElement("ResearchStudy");
			DocumentAssembler.assembleResearchStudyElement(ResearchStudyElem, ResearchStudy);			
		}
		
		return document.asXML();
	}
	
	
	
	@Path( "{ResearchStudyID}" )
    @PUT
	public String put(@PathParam("ResearchStudyID") String visitID,String content) {
		System.out.println(content);
		
		String result = SchemaValidator.validateResearchStudy(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		
		try {
			Document document = DocumentHelper.parseText(content);
			
			String name = document.selectSingleNode("//ResearchStudy/name").getText();
			String oldname = document.selectSingleNode("//ResearchStudy/oldname").getText();
			String description = document.selectSingleNode("//ResearchStudy/description").getText();
			
			ResearchStudyRest.put(oldname, name, description, Integer.parseInt(visitID));
			return "<result>"+"</OK>"+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}		
	}
	
	
	
    @POST
	public String post(String content) {
		System.out.println(content);
		String result = SchemaValidator.validateResearchStudy(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		try {
			Document document = DocumentHelper.parseText(content);

			String name = document.selectSingleNode("//ResearchStudy/name").getText();
			String description = document.selectSingleNode("//ResearchStudy/description").getText();
			int visitID = Integer.parseInt(document.selectSingleNode("//ResearchStudy/visitID").getText());
			

			ResearchStudyRest.post(name, description, visitID);
			return "<result>"+"</result>"; 


			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}
	}
	

	
	@Path( "{ResearchStudyID}" )
    @DELETE
	public String delete(@PathParam("ResearchStudyID") String ResearchStudyID) {
		System.out.println("ResearchStudy #: "+ResearchStudyID);
		ResearchStudyRest.delete(ResearchStudyID.split(",")[0],ResearchStudyID.split(",")[1], Integer.parseInt(ResearchStudyID.split(",")[2]));
		return "delete response";
	}

}
