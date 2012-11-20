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

import com.mayo.db.BillingCodes;
import com.mayo.db.BillingCodesRest;

@Path("/BillingCodes")
public class BillingCodesWS {
	
	BillingCodesRest BillingCodesRest = com.mayo.db.BillingCodesRest.getInstance();


    @GET
	public String get() {
		List<BillingCodes> BillingCodess = BillingCodesRest.getAll();
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(BillingCodes BillingCodes : BillingCodess) {
			Element BillingCodesElem = root.addElement("BillingCodes");
			DocumentAssembler.assembleBillingCodesElement(BillingCodesElem, BillingCodes);			
		}
		
		return document.asXML();
	}
	
	
	
	@Path( "{visitID}" )
    @GET
	public String get(@PathParam("visitID") String visitID) {
		System.out.println("visitID #: "+visitID);
		List<BillingCodes>  BillingCodes = BillingCodesRest.getAllBillingCodes(Integer.parseInt(visitID));
		
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("list");
		
		for(BillingCodes billingCode : BillingCodes) {
			Element BillingCodesElem = root.addElement("BillingCodes");
			DocumentAssembler.assembleBillingCodesElement(BillingCodesElem, billingCode);			
		}
		
		return document.asXML();
	}
	
	
	@Path( "{BillingCodesID}" )
    @PUT
	public String put(@PathParam("BillingCodesID") String BillingCodesID,String content) {
		System.out.println("BillingCodes #: "+BillingCodesID);
		System.out.println(content);
		
		String result = SchemaValidator.validateBillingCode(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		
		try {
			Document document = DocumentHelper.parseText(content);
			
			int BillingCodesIDInt = Integer.parseInt(BillingCodesID);
			double visitCharge = Double.parseDouble(document.selectSingleNode("//BillingCodes/visitCharge").getText());
			int cptCode = Integer.parseInt(document.selectSingleNode("//BillingCodes/cptCode").getText());
			int integerIncrement = Integer.parseInt(document.selectSingleNode("//BillingCodes/integerIncrement").getText());
			int visitID = Integer.parseInt(document.selectSingleNode("//BillingCodes/visitID").getText());
			
			BillingCodesRest.put(BillingCodesIDInt, visitCharge, cptCode, integerIncrement, visitID);
			return "<result>"+BillingCodesID+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}			
		
	}
	
	
	
    @POST
	public String post(String content) {
		System.out.println(content);
		String result = SchemaValidator.validateBillingCode(content);
		if(!Boolean.parseBoolean(result))
		{
			return "<error><desc>" + result + "</desc></error>";
		}
		try {
			Document document = DocumentHelper.parseText(content);
			
			double visitCharge = Double.parseDouble(document.selectSingleNode("//BillingCodes/visitCharge").getText());
			int cptCode = Integer.parseInt(document.selectSingleNode("//BillingCodes/cptCode").getText());
			int integerIncrement = Integer.parseInt(document.selectSingleNode("//BillingCodes/integerIncrement").getText());
			int visitID = Integer.parseInt(document.selectSingleNode("//BillingCodes/visitID").getText());
			
			BillingCodesRest.post(visitCharge, cptCode, integerIncrement, visitID);
			return "<result>"+"<OK/>"+"</result>"; 
			
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<error />";
		}
	}
	
    
	@Path( "{BillingCodesID}" )
    @DELETE
	public String delete(@PathParam("BillingCodesID") String BillingCodesID) {
		System.out.println("BillingCodes #: "+BillingCodesID);
		BillingCodesRest.delete(Integer.parseInt(BillingCodesID));
		return "delete response";
	}
}
