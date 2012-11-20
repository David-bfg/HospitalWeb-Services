package com.mayo.dwr;

import com.mayo.transform.Files;
import com.mayo.transform.MyTransformer;
public class Patient {
	
	public String getPatient(int id){
		String res = HTTPPoster.getInstance().get("Patient/" + id);
		String res1 = MyTransformer.getInstance().styleDocumentFromData(res, Files.getInstance().PATIENT_XSL);
		return res1;
	}
	
	public String getAllPatientXML() {
	
		String res = HTTPPoster.getInstance().get("Patient/");
	String res1 = MyTransformer.getInstance().styleDocumentFromData(res, Files.getInstance().PATIENT_XSL);
	System.out.println(res);
	System.out.println(res1);
	return res1;
	}
	
	
	public String createPatient(int clinicNum, String lastname, String firstname, String dob,
			String gender, String height, String weight, String side, String extremity,
			String involved, String dominant, String measuredSide, String problemDescriptor) {
		
		System.out.println("createPatient");
		
		StringBuilder sb = new StringBuilder();
		sb.append("<Patient>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("<lastname>" + lastname + "</lastname>");
		sb.append("<firstname>" + firstname + "</firstname>");
		sb.append("<dob>" + dob + "</dob>");
		sb.append("<gender>" + gender + "</gender>");
		sb.append("<height>" + height + "</height>");
		sb.append("<weight>" + weight + "</weight>");
		sb.append("<side>" + side + "</side>");
		sb.append("<extremity>" + extremity + "</extremity>");
		sb.append("<involved>" + involved + "</involved>");
		sb.append("<dominant>" + dominant + "</dominant>");
		sb.append("<measuredSide>" + measuredSide + "</measuredSide>");
		sb.append("<problemDescriptor>" + problemDescriptor + "</problemDescriptor>");
		sb.append("</Patient>");
		
		String res = HTTPPoster.getInstance().post("Patient/",sb.toString());
		return res;
	}
	
	public boolean deletePatient(int id){
		System.out.println("deletePatient");
		
		String res = HTTPPoster.getInstance().delete("Patient/"+id);
		return true;
	}
	
	public String modifyPatient(int clinicNum, String lastname, String firstname, String dob,
			String gender, String height, String weight, String side, String extremity,
			String involved, String dominant, String measuredSide, String problemDescriptor){
		System.out.println("modifyPatientData");
		
		StringBuilder sb = new StringBuilder();
		sb.append("<Patient>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("<lastname>" + lastname + "</lastname>");
		sb.append("<firstname>" + firstname + "</firstname>");
		sb.append("<dob>" + dob + "</dob>");
		sb.append("<gender>" + gender + "</gender>");
		sb.append("<height>" + height + "</height>");
		sb.append("<weight>" + weight + "</weight>");
		sb.append("<side>" + side + "</side>");
		sb.append("<extremity>" + extremity + "</extremity>");
		sb.append("<involved>" + involved + "</involved>");
		sb.append("<dominant>" + dominant + "</dominant>");
		sb.append("<measuredSide>" + measuredSide + "</measuredSide>");
		sb.append("<problemDescriptor>" + problemDescriptor + "</problemDescriptor>");
		sb.append("</Patient>");
		
		String res = HTTPPoster.getInstance().put("Patient/"+clinicNum,sb.toString());
		return res;
	}
}
