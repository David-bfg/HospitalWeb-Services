package com.mayo.dwr;

import com.mayo.transform.Files;
import com.mayo.transform.MyTransformer;

public class Icd9ProcedureCode {

	
	public String getAllIcd9ProcedureCodesXML(String clinicNum) {
		System.out.println("getAllIcd9ProcedureCodesXML");
		String res = HTTPPoster.getInstance().get("ICD9ProcedureCode/"+clinicNum);
		System.out.println(res);
		String res1 = MyTransformer.getInstance().styleDocumentFromData(res, Files.getInstance().ICD9PROCEDURECODE_XSL);
		System.out.println(res1);
		return res1;
		}
	
	public String createIcd9ProcedureCode(String icd9ProcedureCode, int clinicNum, String procedureDescriptor){
		System.out.println("createIcd9ProcedureCode");
		StringBuilder sb = new StringBuilder();
		sb.append("<Icd9ProcedureCode>");
		sb.append("<icd9ProcedureCode>" + icd9ProcedureCode + "</icd9ProcedureCode>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("<procedureDescriptor>" + procedureDescriptor + "</procedureDescriptor>");
		sb.append("</Icd9ProcedureCode>");
		
		System.out.println(sb); 
		String res = HTTPPoster.getInstance().post("ICD9ProcedureCode/",sb.toString());
		return res;
	}
	
	public String deleteIcd9ProcedureCode(int clinicNum){
		System.out.println("deleteIcd9ProcedureCode");
		String res = HTTPPoster.getInstance().delete("ICD9ProcedureCode/"+clinicNum);
		return res;
	}
	
	public String modifyIcd9ProcedureCode(int icd9ProcedureCodeID, String icd9ProcedureCode, int clinicNum, String procedureDescriptor){
		System.out.println("modifyIcd9ProcedureCode");
		StringBuilder sb = new StringBuilder();
		sb.append("<Icd9ProcedureCode>");
		sb.append("<icd9ProcedureCode>" + icd9ProcedureCode + "</icd9ProcedureCode>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("<procedureDescriptor>" + procedureDescriptor + "</procedureDescriptor>");
		sb.append("</Icd9ProcedureCode>");
		
		System.out.println("----\n"+sb); 
		String res = HTTPPoster.getInstance().put("ICD9ProcedureCode/"+icd9ProcedureCodeID,sb.toString());
		return res;
	}
}
