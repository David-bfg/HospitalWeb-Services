package com.mayo.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class AutoCompleteRest {
	private static AutoCompleteRest instance = null;
	private static HashMap<String, String> map = new HashMap<String, String>();

	public AutoCompleteRest() {
		map.put("AssistiveDevice", "Name,assistivedevice");
		map.put("Conditions", "name,condition");
		map.put("ICD9Diagnostic", "ICD9Diagnostic,icd9diagnostic");
		map.put("ICD9ProcedureCode", "ICD9ProcedureCode,icd9procedurecode");
		map.put("SystemUsed", "name,systemused");
		map.put("ResearchStudy", "name,researchstudy");
		map.put("Study", "studyName,study");
		map.put("Provider", "provider,visit");
		map.put("kinesiologist","kinesiologist,visit");
		map.put("physicalTherapist","physicalTherapist,visit");
	}

	public String getList(String category) {
		dbBase.getInstance().openConnection();
		Connection conn = dbBase.getInstance().getConn();
		if (map.containsKey(category.trim())) {
			String[] vals = map.get(category).split(",");
			if (vals.length == 2) {
				StringBuilder sb = new StringBuilder();
				String query = "SELECT distinct(" + vals[0] + ") FROM `" + vals[1]+"`";
				System.out.println(query);
				try {
					Statement s = conn.createStatement();
					ResultSet rs = s.executeQuery(query);
					while (rs.next()) {
						sb.append(rs.getString(1));
						sb.append("|");
					}
					String ss = sb.toString();
					return ss.substring(0,ss.length()-1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	public static AutoCompleteRest getInstance() {
		if (instance == null)
			instance = new AutoCompleteRest();

		return instance;
	}

}
