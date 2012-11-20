package com.mayo.ws;

import java.io.File;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class SchemaValidator {

	public static String validate(String xmlData) {
		return validate(xmlData, "patient.xsd");
	}

	private static String validate(String xmlData,String filename) {
		
		SchemaFactory factory = SchemaFactory
				.newInstance("http://www.w3.org/2001/XMLSchema");

		File schemaLocation = new File("schema/"+filename);
		Schema schema;
		try {
			schema = factory.newSchema(schemaLocation);

			Validator validator = schema.newValidator();

			Source source = new StreamSource(new StringReader(xmlData));

			validator.validate(source);
			return "true";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return ex.getMessage();
		}

	}

	public static String validateVisit(String xmlData) {
			return validate(xmlData, "visit.xsd");
	}

	public static String validateAssistiveDevice(String content) {
		return validate(content, "assistivedevice.xsd");
	}

	public static String validateBillingCode(String content) {
		return validate(content, "billingcode.xsd");
	}

	public static String validateCondition(String content) {
		return validate(content, "condition.xsd");
	}

	public static String validateFile(String content) {
		return validate(content, "file.xsd");
	}

	public static String validateICD9Diagnostic(String content) {
		return validate(content, "icd9diagnostic.xsd");
	}

	public static String validateICD9ProcedureCode(String content) {
		return validate(content, "icd9procedurecode.xsd");
	}

	public static String validateResearchStudy(String content) {
		return validate(content, "researchstudy.xsd");
	}

	public static String validateStudy(String content) {
		return validate(content, "study.xsd");
	}

	public static String validateSystemUsed(String content) {
		return validate(content, "systemused.xsd");
	}
}