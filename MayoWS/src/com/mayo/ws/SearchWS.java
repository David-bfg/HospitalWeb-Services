package com.mayo.ws;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.mayo.db.Patient;
import com.mayo.db.PatientRest;

@Path("/Search/")
public class SearchWS {

	private static Map<String, String> filters = new HashMap<String, String>();
	private static Map<String, String> relation = new HashMap<String, String>();
	private static Map<String, String> classes = new HashMap<String, String>();
	
	public SearchWS() {
		filters.put("ClinicNumber", "clinicNum");
		filters.put("Diagnosis", "icd9diagnostic.ICD9Diagnostic");
		filters.put("Procedure", "icd9procedurecode.ICD9ProcedureCode");
		filters.put("Condition", "condition.name");
		filters.put("DOB", "DOB");
		filters.put("Gender", "gender");
		filters.put("Extremity", "extremity");
		filters.put("Side", "side");
		filters.put("Involved", "involved");
		filters.put("Dominant", "dominant");
		filters.put("Provider", "visit.provider");
		filters.put("Therapist", "visit.physicalTherapist");
		filters.put("Kinesiologist", "visit.kinesiologist");
		filters.put("SystemUsed", "systemused.name");
		filters.put("StudyName", "study.studyName");
		filters.put("ResearchStudy", "researchstudy.name");
		filters.put("AssistiveDevice", "assistivedevice.Name");

		relation.put("Diagnosis", "patient.clinicNum=icd9diagnostic.clinicNum");
		relation.put("Procedure", "patient.clinicNum=icd9procedurecode.clinicNum");
		relation.put("Condition", "patient.clinicNum=patientcondition.clinicNum AND patientcondition.conditionID=condition.conditionID");
		relation.put("DOB", "");
		relation.put("Gender", "");
		relation.put("Extremity", "");
		relation.put("Side", "");
		relation.put("Involved", "");
		relation.put("Dominant", "");
		relation.put("ClinicNumber", "");
		relation.put("Provider", "patient.clinicNum=visit.clinicNum");
		relation.put("Therapist", "patient.clinicNum=visit.clinicNum");
		relation.put("Kinesiologist", "patient.clinicNum=visit.clinicNum");
		relation.put("SystemUsed", "patient.clinicNum=visit.clinicNum AND visit.visitID=visitsystemused.visitID AND visitsystemused.systemUsedID=systemused.systemUsedID");
		relation.put("StudyName", "patient.clinicNum=visit.clinicNum AND visit.visitID=patientstudy.visitID AND patientstudy.studyID=study.studyID");
		relation.put("ResearchStudy", "patient.clinicNum=visit.clinicNum AND visit.visitID=visitresearchstudy.visitID AND visitresearchstudy.IRBNum=researchstudy.IRBNum");
		relation.put("AssistiveDevice", "patient.clinicNum=patientassistivedevice.clinicNum AND patientassistivedevice.AssistiveDeviceID=assistivedevice.AssistiveDeviceID");
	
	
		classes.put("Diagnosis", "icd9diagnostic");
		classes.put("Procedure", "icd9procedurecode");
		classes.put("Condition", "patientcondition, condition");
		classes.put("DOB", "");
		classes.put("Gender", "");
		classes.put("Extremity", "");
		classes.put("Side", "");
		classes.put("Involved", "");
		classes.put("Dominant", "");
		classes.put("ClinicNumber","");
		classes.put("Provider", "visit");
		classes.put("Therapist", "visit");
		classes.put("Kinesiologist", "visit");
		classes.put("SystemUsed", "visit, visitsystemused,systemused");
		classes.put("StudyName", "visit, patientstudy, study");
		classes.put("ResearchStudy", "visit,visitresearchstudy, researchstudy");
		classes.put("AssistiveDevice", "patientassistivedevice, assistivedevice");
		
	}
	
	 @POST
	public String post(String content) {
		 String content1 = "<filters>" +
		 		" <filter name=\"Diagnosis\" value=\"diag\" type=\"like\" />" +
		 		" <filter name=\"Procedure\" value=\"proc\" type=\"like\" />" +
		 		" <filter name=\"Condition\" value=\"cond\" type=\"like\" />" +
		 		" <filter name=\"DOB\" value=\"1923-02-12\" type=\"gt\" />" +
		 		" <filter name=\"DOB\" value=\"2003-12-11\" type=\"lt\" />" +
		 		" <filter name=\"Gender\" value=\"M\" type=\"equals\" />" +
		 		" <filter name=\"Extremity\" value=\"L\" type=\"equals\" />" +
		 		" <filter name=\"Side\" value=\"L\" type=\"equals\" />" +
		 		" <filter name=\"Involved\" value=\"I\" type=\"equals\" />" +
		 		" <filter name=\"Dominant\" value=\"D\" type=\"equals\" />" +
		 		" <filter name=\"Provider\" value=\"prov\" type=\"like\" />" +
		 		" <filter name=\"Therapist\" value=\"one\" type=\"equals\" />" +
		 		" <filter name=\"Kinesiologist\" value=\"one\" type=\"equals\" />" +
		 		" <filter name=\"SystemUsed\" value=\"sys\" type=\"like\" />" +
		 		" <filter name=\"StudyName\" value=\"stud\" type=\"like\" />" +
		 		" <filter name=\"ResearchStudy\" value=\"res\" type=\"like\" />" +
		 		" <filter name=\"AssistiveDevice\" value=\"dev\" type=\"like\" />" +
		 		" </filters>";
		 
		 try {
			Document document = DocumentHelper.parseText(content);
			List<Node> list = document.selectNodes("//filters/filter");
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * from patient WHERE ");
			HashSet<String> set = new HashSet<String>();
			int i = 0;
			
			for(Node node:list)
			{
				
				String name = ((Element)node).attributeValue("name");
				String value = ((Element)node).attributeValue("value");
				String type = ((Element)node).attributeValue("type");
				
				appendToSet(set, name);
				
				if (filters.get(name).length() > 0) {
						if (type.equals("like")) {
							if(i!=0)
								sb.append(" AND ");
							sb.append(" " + filters.get(name) + " LIKE " + "'%" + value + "%' ");
	
							if(relation.get(name).length()>0)
							{
							sb.append(" AND ");
							sb.append(relation.get(name) +" ");
							}
						}
						else if (type.equals("not_like")) {
							if(i!=0)
								sb.append(" AND ");
							sb.append(" " + filters.get(name) + " NOT LIKE " + "'%" + value + "%' ");
	
							if(relation.get(name).length()>0)
							{
							sb.append(" AND ");
							sb.append(relation.get(name) +" ");
							}
	
						}
						else if (type.equals("equals")) {
							if(i!=0)
								sb.append(" AND ");
							sb.append(" " + filters.get(name) + "=" + "'" + value + "' ");
							
							if(relation.get(name).length()>0)
							{
							sb.append(" AND ");
							sb.append(relation.get(name) +" ");
							}
						} else if (type.equals("lt")) {
							if(i!=0)
								sb.append(" AND ");
							sb.append(" " + filters.get(name) + "<=" + "'" + value + "' ");
							
							if(relation.get(name).length()>0)
							{
							sb.append(" AND ");
							sb.append(relation.get(name) +" ");
							}
	
						} else if (type.equals("gt")) {
							if(i!=0)
								sb.append(" AND ");
							sb.append(" " + filters.get(name) + ">=" + "'" + value + "' ");
							
							if(relation.get(name).length()>0)
							{
							sb.append(" AND ");
							sb.append(relation.get(name) +" ");
							}
							}
				}
			i++;	
			}

			System.out.println(set);
			String s = getData(set);
			
			System.out.println("\n\n"+sb.toString().replace("WHERE", s+" WHERE ") +"\n\n");
			String res =  sb.toString().replace("WHERE", s+" WHERE ");
			List<Patient> lista =  PatientRest.getInstance().getAll(res);
			
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("list");
			
			for(Patient patient : lista) {
				Element patientElem = root.addElement("Patient");
				DocumentAssembler.assemblePatientElement(patientElem, patient);			
			}
			
			return doc.asXML();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return "";
	}
	 
	 private String getData(HashSet<String> set) {
		StringBuilder sb = new StringBuilder();
		
		 for (String s: set) {
			if(s.trim().length()>0)
			{
				sb.append(","+ s.trim());
			}
		}
		 return sb.toString();
	}

	private void appendToSet(HashSet<String> set, String name) {
		String s = classes.get(name);
		String ss[] = s.split(",");
		for (String d: ss)
		{
			set.add(d.trim());
		}
	}

	public static void main(String[] args)
	 {
		 SearchWS s= new SearchWS();
		 s.post("");
	 }
}