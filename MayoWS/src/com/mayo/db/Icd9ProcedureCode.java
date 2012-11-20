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
@Table (name = "icd9procedurecode")
public class Icd9ProcedureCode implements Serializable 
{
	@Id
	@Column (name = "ICD9ProcedureCodeID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer ICD9ProcedureCodeID;
	
	@Column (name = "ICD9ProcedureCode")
	String ICD9ProcedureCode;
	
	@Column (name = "clinicNum")
	Integer clinicNum;
	
	@Column (name = "`Procedure Descriptor`")
	String procedureDescriptor;
	
	public Icd9ProcedureCode ()
	{
		
	}
	
	public String getProcedureDescriptor()
	{
		return this.procedureDescriptor;
	}
	
	public void setprocedureDescriptor(String procedureDescriptor)
	{
		this.procedureDescriptor = procedureDescriptor;
	}
	
	public int getIcd9ProcedureCodeID()
	{
		return this.ICD9ProcedureCodeID;
	}
	
	public void setIcd9ProcedureCodeID(int icd9ProcedureCodeID)
	{
		this.ICD9ProcedureCodeID = icd9ProcedureCodeID;
	}
	
	public String getIcd9ProcedureCode()
	{
		return ICD9ProcedureCode;
	}
	
	public void setIcd9ProcedureCode(String icd9ProcedureCode)
	{
		this.ICD9ProcedureCode = icd9ProcedureCode;
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
		sb.append("<icd9procedurecode>");
		sb.append("<ICD9ProcedureCodeID>" + ICD9ProcedureCodeID + "</ICD9ProcedureCodeID>");
		sb.append("<ICD9ProcedureCode>" + ICD9ProcedureCode + "</ICD9ProcedureCode>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("<procedureDescriptor>" + procedureDescriptor + "</procedureDescriptor>");
		sb.append("</icd9procedurecode>");
		return sb.toString();
	}
}
