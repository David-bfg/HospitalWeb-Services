package com.mayo.transform;

public class Files {
	private static Files instance = null;
	
	public String path = "/home/davidbfg/workspace/MayoWeb/WebContent/WEB-INF";


	public String LINKS_XSL = path + "/links.xsl";
	public String LINKS_XML = path + "/link.xml";
	public String TOP_LINKS_XML = path + "/top_link.xml";

	public String FILTERS = path + "/filters.xml";
	public String CONF = path + "/conf.xml";
	
	public String PATIENT_XSL = path + "/patient.xsl";
	public String VISIT_XSL = path + "/visit.xsl";
	public String ASSISTIVEDEVICE_XSL = path + "/Assistivedevice.xsl";
	public String BILLINGCODE_XSL = path + "/BillingCodes.xsl";
	public String CONDITION_XSL = path + "/Condition.xsl";
	public String FILE_XSL = path + "/File.xsl";
	public String ICD9DIAGNOSTIC_XSL = path + "/Icd9Diagnostic.xsl";
	public String ICD9PROCEDURECODE_XSL = path + "/Icd9procedurecode.xsl";
	public String RESEARCHSTUDY_XSL = path + "/ResearchStudy.xsl";
	public String STUDY_XSL = path + "/Study.xsl";
	public String SYSTEMUSED_XSL = path + "/SystemUsed.xsl";
	public String TAPE_XSL = path + "/Tape.xsl";

	public void init(String path) {
		System.out.println(path);
		this.path = path;
		LINKS_XSL = path + "links.xsl";
		LINKS_XML = path + "link.xml";
		TOP_LINKS_XML = path + "top_link.xml";
		FILTERS = path + "filters.xml";
		CONF = path + "/conf.xml";
		
		PATIENT_XSL = path + "/patient.xsl";
		VISIT_XSL = path + "/visit.xsl";
		ASSISTIVEDEVICE_XSL = path + "/Assistivedevice.xsl";
		BILLINGCODE_XSL = path + "/BillingCodes.xsl";
		CONDITION_XSL = path + "/Condition.xsl";
		FILE_XSL = path + "/File.xsl";
		ICD9DIAGNOSTIC_XSL = path + "/Icd9Diagnostic.xsl";
		ICD9PROCEDURECODE_XSL = path + "/Icd9procedurecode.xsl";
		RESEARCHSTUDY_XSL = path + "/ResearchStudy.xsl";
		STUDY_XSL = path + "/Study.xsl";
		SYSTEMUSED_XSL = path + "/SystemUsed.xsl";
		TAPE_XSL = path + "/Tape.xsl";
	}
	
	public static Files getInstance() {
		if(instance==null)
			instance = new Files();
		
		return instance;
	}
}
