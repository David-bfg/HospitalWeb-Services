package com.mayo.dwr;

import com.mayo.transform.Files;
import com.mayo.transform.MyTransformer;

public class AssistiveDevice {

	public String getAllAssistiveDevicesXML(String clinicNum) {
		System.out.println("getAllAssistiveDevicesXML");
		String res = HTTPPoster.getInstance().get("AssistiveDevice/"+clinicNum);
		System.out.println(res);
		String res1 = MyTransformer.getInstance().styleDocumentFromData(res, Files.getInstance().ASSISTIVEDEVICE_XSL);
		System.out.println(res1);
		return res1;
		}
	
	public String createAssistiveDevice(String name, String clinicNum){
		System.out.println("createAssistiveDevice");
		StringBuilder sb = new StringBuilder();
		sb.append("<Assistivedevice>");
		sb.append("<name>" + name + "</name>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("</Assistivedevice>");
		
		System.out.println(sb); 
		String res = HTTPPoster.getInstance().post("AssistiveDevice/",sb.toString());
		return res;
	}
	
	public String deleteAssistiveDevice(String name, String clinicNum){
		System.out.println("deleteAssistiveDevice");
		String res = HTTPPoster.getInstance().delete("AssistiveDevice/"+name+","+clinicNum);
		return res;
	}
	
	public String modifyAssistiveDevice(String oldname, String name, String clinicNum){
		System.out.println("modifyAssistiveDevice");
		StringBuilder sb = new StringBuilder();
		sb.append("<Assistivedevice>");
		sb.append("<name>" + name + "</name>");
		sb.append("<oldname>" + oldname + "</oldname>");
		sb.append("</Assistivedevice>");
		
		String res = HTTPPoster.getInstance().put("AssistiveDevice/"+clinicNum,sb.toString());
		return res;
	}
	
	public static void main(String[] args) {
		//AssistiveDevice as = new AssistiveDevice();
		//as.deleteAssistiveDevice("yahoo","100000001");
		
		String res = "<list><File><fileID>7</fileID><filename>yo</filename><clinicNum>343443</clinicNum>" +
				"</File></list>";
		String res1 = MyTransformer.getInstance().styleDocumentFromData(res, Files.getInstance().FILE_XSL);
		System.out.println(res1);
		
	}	
}
