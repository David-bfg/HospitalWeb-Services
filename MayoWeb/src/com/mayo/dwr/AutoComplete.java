package com.mayo.dwr;

public class AutoComplete {
	public String getAllAssistiveDevices() {
		System.out.println("Sending get All Assistive Device");
		return HTTPPoster.getInstance().get("AutoComplete/AssistiveDevice");
	}
	
	public String getAllConditions() {
		return HTTPPoster.getInstance().get("AutoComplete/Conditions");
	}
	
	public String getAllICD9Diagnostic() {
		return HTTPPoster.getInstance().get("AutoComplete/ICD9Diagnostic");
	}
	
	public String getAllICD9ProcedureCode() {
		return HTTPPoster.getInstance().get("AutoComplete/ICD9ProcedureCode");
	}
	
	public String getAllSystemUsed() {
		return HTTPPoster.getInstance().get("AutoComplete/SystemUsed");
	}
	
	public String getAllStudy() {
		return HTTPPoster.getInstance().get("AutoComplete/Study");
	}
	
	public String getAllResearchStudy() {
		return HTTPPoster.getInstance().get("AutoComplete/ResearchStudy");
	}
	
	public String getAllProvider() {
		return HTTPPoster.getInstance().get("AutoComplete/Provider");
	}
	
	public String getAllKinesiologist() {
		return HTTPPoster.getInstance().get("AutoComplete/kinesiologist");
	}
	
	public String getAllPhysicalTherapist() {
		return HTTPPoster.getInstance().get("AutoComplete/physicalTherapist");
	}
	
	
	public static void main(String[] ars) {
		AutoComplete a = new AutoComplete();
		String s =a.getAllAssistiveDevices();
		System.out.println(s);
	}
}
