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

import com.mayo.db.File;
import com.mayo.db.FileRest;

@Path("/File")
public class FileWS {
	
	FileRest FileRest = com.mayo.db.FileRest.getInstance();
	
	
	@Path( "{clinicNum}" )
    @GET
	public String get(@PathParam("clinicNum") String clinicNum) {
		List<File> Files = FileRest.getAllFiles(clinicNum);
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(File File : Files) {
			Element FileElem = root.addElement("File");
			DocumentAssembler.assembleFileElement(FileElem, File);			
		}
		
		return document.asXML();
	}
	
	
	@Path( "{FileID}" )
    @PUT
	public String put(@PathParam("FileID") String FileID,String content) {
		System.out.println("File #: "+FileID);
		System.out.println(content);
		
		String result = SchemaValidator.validateFile(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		
		try {
			Document document = DocumentHelper.parseText(content);
			
			int fileID = Integer.parseInt(FileID);
			int clinicNum = Integer.parseInt(document.selectSingleNode("//File/clinicNum").getText());
			String filename = document.selectSingleNode("//File/filename").getText();
			
			FileRest.put(fileID, filename, clinicNum);
			return "<result>"+FileID+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}			
		
	}
	
	
    @POST
	public String post(String content) {
		System.out.println(content);
		String result = SchemaValidator.validateFile(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		try {
			Document document = DocumentHelper.parseText(content);
			
			String filename = document.selectSingleNode("//File/filename").getText();
			int description = Integer.parseInt(document.selectSingleNode("//File/clinicNum").getText());
			
			FileRest.post(filename, description);
			return "<result>"+filename+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}
	}
	

	
	@Path( "{FileID}" )
    @DELETE
	public String delete(@PathParam("FileID") String fileID) {
		FileRest.delete(Integer.parseInt(fileID));
		return "delete response";
	}
}
