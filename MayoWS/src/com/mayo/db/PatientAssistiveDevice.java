package com.mayo.db;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "patientassistivedevice")
public class PatientAssistiveDevice implements Serializable {
	@Id
	@Column (name = "AssistiveDeviceID")
	Integer assistiveDeviceID;
	
	@Column(name = "ClinicNum")
	Integer clinicNum;
	
	public int getPatientAssistiveDevice()
	{
		return assistiveDeviceID;
	}
	
	public void setPatientAssistiveDeviceID(int pad)
	{
		this.assistiveDeviceID = pad;
	}
	
	public int getClinicNum()
	{
		return clinicNum;
	}
	
	public void setClinicNum(int cn)
	{
		this.clinicNum = cn;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<PatientAssistiveDevice>");
		sb.append("<AssistiveDeviceID>" + assistiveDeviceID + "</AssistiveDeviceID>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("</PatientAssistiveDevice>");
		return sb.toString();
	}
}
