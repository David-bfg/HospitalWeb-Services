package com.mayo.dwr;

import com.mayo.transform.Files;
import com.mayo.transform.MyTransformer;

public class Icd9Diagnostic {
	
	
	public String getAllIcd9DiagnosticXML(String clinicNum) {
		System.out.println("getAllIcd9DiagnosticXML");
		String res = HTTPPoster.getInstance().get("ICD9Diagnostic/"+clinicNum);
		System.out.println(res);
		String res1 = MyTransformer.getInstance().styleDocumentFromData(res, Files.getInstance().ICD9DIAGNOSTIC_XSL);
		System.out.println(res1);
		return res1;
		}
	
	public String createIcd9Diagnostic(String icd9Diagnostic, int clinicNum){
		System.out.println("createIcd9Diagnostic");
		StringBuilder sb = new StringBuilder();
		sb.append("<Icd9Diagnostic>");
		sb.append("<icd9Diagnostic>" + icd9Diagnostic + "</icd9Diagnostic>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("</Icd9Diagnostic>");
		
		System.out.println(sb); 
		String res = HTTPPoster.getInstance().post("ICD9Diagnostic/",sb.toString());
		return res;
	}

	public String deleteIcd9Diagnostic(int icd9DiagnosticID){
		System.out.println("deleteIcd9Diagnostic");
		String res = HTTPPoster.getInstance().delete("ICD9Diagnostic/"+icd9DiagnosticID);
		return res;
	}

	public String modifyIcd9Diagnostic(int icd9DiagnosticID, String icd9Diagnostic, int clinicNum){
		System.out.println("modifyIcd9Diagnostic");
		StringBuilder sb = new StringBuilder();
		sb.append("<Icd9Diagnostic>");
		sb.append("<icd9DiagnosticID>" + icd9DiagnosticID + "</icd9DiagnosticID>");
		sb.append("<icd9Diagnostic>" + icd9Diagnostic + "</icd9Diagnostic>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		
		sb.append("</Icd9Diagnostic>");
		
		String res = HTTPPoster.getInstance().put("ICD9Diagnostic/"+icd9DiagnosticID,sb.toString());
		return res;
	}
}
