package com.mayo.ws;

import org.dom4j.Element;

import com.mayo.db.AssistiveDevice;
import com.mayo.db.BillingCodes;
import com.mayo.db.Patient;
import com.mayo.db.Tape;
import com.mayo.db.Visit;

public class DocumentAssembler {
	
	/**
	 * Assembles a patient element by converting data from Patient into elements
	 * and adding them as children to the patientElem
	 * @param root
	 * @param patient
	 * @return
	 */
	public static void assemblePatientElement(Element patientElem, Patient patient) {
		
		Element clinicNumElem = patientElem.addElement("clinicNum");
		clinicNumElem.addText(patient.getClinicNum() + "");
		
		Element lastNameElem = patientElem.addElement("lastname");
		lastNameElem.addText(patient.getLastname());
		
		Element firstNameElem = patientElem.addElement("firstname");
		firstNameElem.addText(patient.getFirstname());
		
		Element dobElem = patientElem.addElement("dob");
		if(patient.getDOB().toString().contains("00:00:00"))
		{		dobElem.addText(patient.getDOB().toString().substring(0, 
				patient.getDOB().toString().indexOf(" 00:00:00")) );
		}
		else
		{
			dobElem.addText(patient.getDOB().toString());
		}
		Element genderElem = patientElem.addElement("gender");
		genderElem.addText(patient.getGender().toString());
		
		Element heightElem = patientElem.addElement("height");
		heightElem.addText(patient.getHeight() + "");
		
		Element weightElem = patientElem.addElement("weight");
		weightElem.addText(patient.getWeight() + "");
		
		Element sideElem = patientElem.addElement("side");
		sideElem.addText(patient.getSide().toString());
		
		Element extremityElem = patientElem.addElement("extremity");
		extremityElem.addText(patient.getExtremity().toString());
		
		Element involvedElem = patientElem.addElement("involved");
		involvedElem.addText(patient.getInvolved()+"");
		
		Element dominantElem = patientElem.addElement("dominant");
		dominantElem.addText(patient.getDominant()+"");
		
		Element measuredSideElem = patientElem.addElement("measuredSide");
		measuredSideElem.addText(patient.getMeasuredSide()+"");
		
		Element problemDescriptor = patientElem.addElement("problemDescriptor");
		problemDescriptor.addText(patient.getProblemDescriptor()+"");
	}
	
	/**
	 * Assembles a visit element by converting data from Visit into elements
	 * and adding them as children to the visitElem
	 * @param root
	 * @param patient
	 * @return
	 */
	public static void assembleVisitElement(Element visitElem, Visit visit) {
		
		Element visitIdElem = visitElem.addElement("visitID");
		visitIdElem.addText(visit.getVisitID() + "");
		
		Element dateElem = visitElem.addElement("date");
		dateElem.addText(visit.getDate().toString().substring(0, 
				visit.getDate().toString().indexOf(" 00:00:00")) );
		
		Element visitNumElem = visitElem.addElement("visitNum");
		visitNumElem.addText(visit.getVisitNum() + "");
		
		Element clinicNumElem = visitElem.addElement("clinicNum");
		clinicNumElem.addText(visit.getClinicNum() + "");
		
		Element providerElem = visitElem.addElement("provider");
		providerElem.addText(visit.getProvider());
		
		Element kinesiologistElem = visitElem.addElement("kinesiologist");
		kinesiologistElem.addText(visit.getKinesiologist());
		
		Element dateProcessingCompleteElem = visitElem.addElement("dateProcessingComplete");
		dateProcessingCompleteElem.addText(visit.getDateProcessingComplete().toString().substring(0, 
				visit.getDateProcessingComplete().toString().indexOf(" 00:00:00")) );
		
		Element physicalTherapistElem = visitElem.addElement("physicalTherapist");
		physicalTherapistElem.addText(visit.getPhysicalTherapist());
		
		Element dateAnalysisCompleteElem = visitElem.addElement("dateAnalysisComplete");
		dateAnalysisCompleteElem.addText(visit.getDateAnalysisComplete().toString().substring(0, 
				visit.getDateAnalysisComplete().toString().indexOf(" 00:00:00")) );
		
	}
	
	/**
	 * Assembles a patient element by converting data from Patient into elements
	 * and adding them as children to the patientElem
	 * @param root
	 * @param AssistiveDevice
	 * @return
	 */
	public static void assembleAssistivedeviceElement(Element AssistiveDeviceElem, AssistiveDevice Assistivedevice) {
		
		Element AssistivedeviceIDElem = AssistiveDeviceElem.addElement("AssistivedeviceID");
		AssistivedeviceIDElem.addText(Assistivedevice.getAssistiveDeviceID() + "");
		
		Element nameElem = AssistiveDeviceElem.addElement("name");
		nameElem.addText(Assistivedevice.getName());
		
		
		
	}
	
	/**
	 * Assembles a patient element by converting data from Patient into elements
	 * and adding them as children to the patientElem
	 * @param root
	 * @param BillingCodes
	 * @return
	 */
	public static void assembleBillingCodesElement(Element BillingCodesElem, BillingCodes BillingCodes) {
		
		BillingCodesElem.addElement("billingCodeID").addText(BillingCodes.getBillingCodID() + "");

		BillingCodesElem.addElement("visitCharge").addText(BillingCodes.getVisitCharge()+"");
		
		BillingCodesElem.addElement("cptCode").addText(BillingCodes.getCPTCode()+"");
		
		BillingCodesElem.addElement("integerIncrement").addText(BillingCodes.getIntegerIncrement()+"");
		
		BillingCodesElem.addElement("visitID").addText(BillingCodes.getVisitID()+"");
		
		
		
	}
	
	/**
	 * Assembles a patient element by converting data from Patient into elements
	 * and adding them as children to the patientElem
	 * @param root
	 * @param Condition
	 * @return
	 */
	public static void assembleConditionElement(Element ConditionElem, com.mayo.db.Condition Condition) {
		
		ConditionElem.addElement("conditionID").addText(Condition.getConditionID() + "");

		ConditionElem.addElement("name").addText(Condition.getName()+"");
				
	}
	
	
	/**
	 * Assembles a patient element by converting data from Patient into elements
	 * and adding them as children to the patientElem
	 * @param root
	 * @param File
	 * @return
	 */
	public static void assembleFileElement(Element FileElem, com.mayo.db.File File) {
		FileElem.addElement("fileID").addText(File.getId()+"");
		FileElem.addElement("clinicNum").addText(File.getClinicNum() + "");
		FileElem.addElement("filename").addText(File.getFileName()+"");
	}
	
	/**
	 * Assembles a patient element by converting data from Patient into elements
	 * and adding them as children to the patientElem
	 * @param root
	 * @param Icd9Diagnostic
	 * @return
	 */
	public static void assembleIcd9DiagnosticElement(Element Icd9DiagnosticElem, com.mayo.db.Icd9Diagnostic Icd9Diagnostic) {
		
		Icd9DiagnosticElem.addElement("clinicNum").addText(Icd9Diagnostic.getClinicNum() + "");

		Icd9DiagnosticElem.addElement("icd9Diagnostic").addText(Icd9Diagnostic.getIcd9Diagnostic()+"");
		
		Icd9DiagnosticElem.addElement("icd9DiagnosticID").addText(Icd9Diagnostic.getIcd9DiagnosticID()+"");
	}
	
	/**
	 * Assembles a patient element by converting data from Patient into elements
	 * and adding them as children to the patientElem
	 * @param root
	 * @param Condition
	 * @return
	 */
	public static void assembleIcd9procedurecodeElement(Element BillingCodesElem, com.mayo.db.Icd9ProcedureCode Icd9ProcedureCode) {
		
		Element clinicNumElem = BillingCodesElem.addElement("clinicNum");
		clinicNumElem.addText(Icd9ProcedureCode.getClinicNum() + "");

		Element lastNameElem = BillingCodesElem.addElement("icd9ProcedureCode");
		lastNameElem.addText(Icd9ProcedureCode.getIcd9ProcedureCode()+"");
		
		Element lastNameElem1 = BillingCodesElem.addElement("icd9ProcedureCodeID");
		lastNameElem1.addText(Icd9ProcedureCode.getIcd9ProcedureCodeID()+"");
		
		Element procedureDescriptor = BillingCodesElem.addElement("procedureDescriptor");
		procedureDescriptor.addText(Icd9ProcedureCode.getProcedureDescriptor()+"");
	}
	
	/**
	 * Assembles a patient element by converting data from Patient into elements
	 * and adding them as children to the patientElem
	 * @param root
	 * @param ResearchStudy
	 * @return
	 */
	public static void assembleResearchStudyElement(Element ResearchStudyElem, com.mayo.db.ResearchStudy ResearchStudy) {
		
		ResearchStudyElem.addElement("irbNum").addText(ResearchStudy.getIRBNum() + "");

		ResearchStudyElem.addElement("name").addText(ResearchStudy.getName()+"");
		
		ResearchStudyElem.addElement("description").addText(ResearchStudy.getDescription()+"");
		
	}
	

	/**
	 * Assembles a patient element by converting data from Patient into elements
	 * and adding them as children to the patientElem
	 * @param root
	 * @param Study
	 * @return
	 */
	public static void assembleStudyElement(Element StudyElem, com.mayo.db.Study Study) {
		
		StudyElem.addElement("studyID").addText(Study.getStudyID() + "");

		StudyElem.addElement("studyName").addText(Study.getStudyName()+"");
		
	}
	

	/**
	 * Assembles a patient element by converting data from Patient into elements
	 * and adding them as children to the patientElem
	 * @param root
	 * @param SystemUsed
	 * @return
	 */
	public static void assembleSystemUsedElement(Element SystemUsedElem, com.mayo.db.SystemUsed SystemUsed) {
		
		SystemUsedElem.addElement("systemUsedID").addText(SystemUsed.getSystemUsedID() + "");

		SystemUsedElem.addElement("name").addText(SystemUsed.getName()+"");
		
	}

	/**
	 * Assembles a patient element by converting data from Patient into elements
	 * and adding them as children to the patientElem
	 * @param root
	 * @param Tape
	 * @return
	 */
	public static void assembleTapeElement(Element tapeElem, Tape tape) {
		tapeElem.addElement("tapeID").addText(tape.getTapeID() + "");
		tapeElem.addElement("clinicNum").addText(tape.getClinicNum() + "");
		tapeElem.addElement("visitID").addText(tape.getVisitID() + "");
		tapeElem.addElement("tapeNum").addText(tape.getTapeNum() + "");
		tapeElem.addElement("studyID").addText(tape.getStudyID() + "");
		tapeElem.addElement("backup").addText(tape.getBackup() + "");
	}
}
