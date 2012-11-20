package com.mayo.dwr;

import com.mayo.transform.Files;
import com.mayo.transform.MyTransformer;

public class BillingCode {

	public String getAllBillingCodesXML(int visitID) {
		System.out.println("getAllBillingCodesXML");
		String res = HTTPPoster.getInstance().get("BillingCodes/"+visitID);
		System.out.println(res);
		String res1 = MyTransformer.getInstance().styleDocumentFromData(res, Files.getInstance().BILLINGCODE_XSL);
		System.out.println(res1);
		return res1;
		}
	
	public String createBillingCode( String visitCharge, int cptCode, int integerIncrement, int visitID){
		System.out.println("createBillingCode");
		StringBuilder sb = new StringBuilder();
		sb.append("<BillingCodes>");
		sb.append("<visitCharge>" + visitCharge + "</visitCharge>");
		sb.append("<cptCode>" + cptCode + "</cptCode>");
		sb.append("<integerIncrement>" + integerIncrement + "</integerIncrement>");
		sb.append("<visitID>" + visitID + "</visitID>");
		sb.append("</BillingCodes>");
		
		System.out.println(sb); 
		String res = HTTPPoster.getInstance().post("BillingCodes/",sb.toString());
		return res;
	}
	
	public String deleteBillingCode(int billingCodeID){
		System.out.println("deleteBillingCode");
		String res = HTTPPoster.getInstance().delete("BillingCodes/"+billingCodeID);
		return res;
	}
	
	public String modifyBillingCode(int billingCodeID, String visitCharge, int cptCode, int integerIncrement, int visitID){
		System.out.println("modifyBillingCode");
		StringBuilder sb = new StringBuilder();
		sb.append("<BillingCodes>");
		sb.append("<billingCodeID>" + billingCodeID + "</billingCodeID>");
		sb.append("<visitCharge>" + visitCharge + "</visitCharge>");
		sb.append("<cptCode>" + cptCode + "</cptCode>");
		sb.append("<integerIncrement>" + integerIncrement + "</integerIncrement>");
		sb.append("<visitID>" + visitID + "</visitID>");
		sb.append("</BillingCodes>");
		
		System.out.println(sb); 
		String res = HTTPPoster.getInstance().put("BillingCodes/"+billingCodeID,sb.toString());
		return res;
	}
	
	public static void main(String[] args)
	{
		BillingCode b = new BillingCode();
		
	}
}
