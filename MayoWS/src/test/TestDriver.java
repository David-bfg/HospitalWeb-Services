package test;

import com.mayo.ws.PatientWS;

public class TestDriver {
	public static void main (String args[]) {
		String patientXml = "<Patient>" +
					"<clinicNum>1000001</clinicNum>" +
				 "<lastname>leoj</lastname>" +
				 "<firstname>Joel</firstname>" +
				 "<dob>1998-06-12</dob>" +
				 "<gender>M</gender>" +
				 "<height>23</height>" +
				 "<weight>120</weight>" +
				 "<side>L</side>" +
				 "<extremity>U</extremity>" +
				 "<involved>U</involved>" +
				 "<dominant>N</dominant>" +
				 "<measuredSide>r</measuredSide>" +
				 "<problemDescriptor>problematic</problemDescriptor>" +
				 
				"</Patient>";
		PatientWS ws = new PatientWS();
		ws.post( patientXml);
		//ws.get("1000001");
		
	}
}
