package com.mayo.dwr;

import com.mayo.transform.Files;
import com.mayo.transform.MyTransformer;

public class File {

	
	public String getAllFilesXML(String clinicNum) {
		System.out.println("getAllFilesXML");
		String res = HTTPPoster.getInstance().get("File/"+clinicNum);
		System.out.println(res);
		String res1 = MyTransformer.getInstance().styleDocumentFromData(res, Files.getInstance().FILE_XSL);
		System.out.println(res1);
		return res1;
		}
	
	public String createFile(String filename, int clinicNum){
		System.out.println("createFile");
		StringBuilder sb = new StringBuilder();
		sb.append("<File>");
		sb.append("<filename>" + filename + "</filename>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("</File>");
		
		System.out.println(sb); 
		String res = HTTPPoster.getInstance().post("File/",sb.toString());
		return res;
	}
	
	public String deleteFile(int fileID){
		System.out.println("deleteFile");
		String res = HTTPPoster.getInstance().delete("File/"+fileID);
		return res;
	}
	
	public String modifyFile(int fileID, String filename, int clinicNum){
		System.out.println("modifyFile");
		StringBuilder sb = new StringBuilder();
		sb.append("<File>");
		sb.append("<filename>" + filename + "</filename>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("</File>");
		
		System.out.println(sb); 
		String res = HTTPPoster.getInstance().put("File/"+fileID,sb.toString());
		return res;
	}
}
