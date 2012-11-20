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
@Table(name = "icd9diagnostic")

public class Icd9Diagnostic implements Serializable 
{
	@Id
	@Column(name = "ICD9DiagnoticID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer ICD9DiagnoticID;
	
	@Column(name = "ICD9Diagnostic")
	String ICD9Diagnostic;
	
	@Column (name = "clinicNum")
	Integer clinicNum;
	
	public Icd9Diagnostic()
	{
		
	}
	
	public int getIcd9DiagnosticID()
	{
		return ICD9DiagnoticID;
	}
	
	public void setIcd9DiagnosticID(int icd9DiagnosticID)
	{
		this.ICD9DiagnoticID = icd9DiagnosticID;
	}
	
	public String getIcd9Diagnostic()
	{
		return ICD9Diagnostic;
	}
	
	public void setIcd9Diagnostic(String icd9Diagnostic)
	{
		this.ICD9Diagnostic = icd9Diagnostic;
	}
	
	public int getClinicNum()
	{
		return clinicNum;
	}
	
	public void setClinicNum(int clinicNum)
	{
		this.clinicNum = clinicNum;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<icd9diagnostic>");
		sb.append("<ICD9DiagnoticID>" + ICD9DiagnoticID + "</ICD9DiagnoticID>");
		sb.append("<ICD9Diagnostic>" + ICD9Diagnostic + "</ICD9Diagnostic>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("</icd9diagnostic>");
		return sb.toString();
	}

}
