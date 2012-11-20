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

import com.mayo.db.AssistiveDevice;
import com.mayo.db.AssistiveDeviceRest;

@Path("/AssistiveDevice")
public class AssistivedeviceWS {
		
	AssistiveDeviceRest assistiveDeviceRest = AssistiveDeviceRest.getInstance();


    @GET
	public String get() {
		List<AssistiveDevice> Assistivedevices = assistiveDeviceRest.getAll();
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(AssistiveDevice Assistivedevice : Assistivedevices) {
			Element AssistivedeviceElem = root.addElement("Assistivedevice");
			DocumentAssembler.assembleAssistivedeviceElement(AssistivedeviceElem, Assistivedevice);			
		}
		
		return document.asXML();
	}
	
	
	
	@Path( "{clinicNum}" )
    @GET
	public String get(@PathParam("clinicNum") String clinicNum) {
		System.out.println("Clinic #: "+clinicNum);
		List<AssistiveDevice> Assistivedevices = assistiveDeviceRest.getAllAssistiveDevice(clinicNum);
		
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(AssistiveDevice Assistivedevice : Assistivedevices) {
			Element AssistivedeviceElem = root.addElement("Assistivedevice");
			DocumentAssembler.assembleAssistivedeviceElement(AssistivedeviceElem, Assistivedevice);			
		}
		
		return document.asXML();
	}
	
	
	
	@Path( "{ClinicNum}")
    @PUT
	public String put(@PathParam("ClinicNum") String clinicNum, String content) {
		//System.out.println("Assistivedevice #: "+AssistivedeviceID);
		System.out.println(content);
		
		String result = SchemaValidator.validateAssistiveDevice(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		
		try {
			Document document = DocumentHelper.parseText(content);
			
			String newname = document.selectSingleNode("//Assistivedevice/name").getText();
			String oldname = document.selectSingleNode("//Assistivedevice/oldname").getText();
			
			assistiveDeviceRest.put(oldname, newname, clinicNum);
			return "<result>"+"</OK>"+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}			
		
	}
	
	
	
    @POST
	public String post(String content) {
		System.out.println(content);
		String result = SchemaValidator.validateAssistiveDevice(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		try {
			Document document = DocumentHelper.parseText(content);
			
			String clinicNum = document.selectSingleNode("//Assistivedevice/clinicNum").getText();
			String name = document.selectSingleNode("//Assistivedevice/name").getText();
			
			assistiveDeviceRest.post(name, clinicNum);
			return "<result>"+"</OK>"+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}
	}
	

	
	@Path( "{AssistivedeviceID}" )
    @DELETE
	public String delete(@PathParam("AssistivedeviceID") String AssistivedeviceID) {
		System.out.println("Assistivedevice #: "+AssistivedeviceID);
		assistiveDeviceRest.delete(AssistivedeviceID.split(",")[0],AssistivedeviceID.split(",")[1]);
		return "delete response";
	}

}
