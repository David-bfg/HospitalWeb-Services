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
@Table (name="tape")
public class Tape implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="tapeID")
	Integer tapeID;
	
	@Column(name="clinicNum")
	Integer clinicNum;
	
	@Column(name="visitID")
	Integer visitID;
	
	@Column(name="tapeNum")
	Integer tapeNum;
	
	@Column(name="studyID")
	Integer studyID;
	
	@Column(name="backup")
	String backup;
	
	public Tape()
	{
		
	}
	
	public int getTapeID()
	{
		return tapeID;
	}
	
	public void setTapeID(int tapeID)
	{
		this.tapeID = tapeID;
	}
	
	public int getClinicNum()
	{
		return clinicNum;
	}
	
	public void setClinicNum(int clinicNum)
	{
		this.clinicNum = clinicNum;
	}

	public int getVisitID()
	{
		return visitID;
	}
	
	public void setVisitID(int visitID)
	{
		this.visitID = visitID;
	}
	
	public int getTapeNum()
	{
		return tapeNum;
	}
	
	public void setTapeNum(int tapeNum)
	{
		this.tapeNum = tapeNum;
	}
	
	public int getStudyID()
	{
		return studyID;
	}
	
	public void setStudyID(int studyID)
	{
		this.studyID = studyID;
	}
	
	public String getBackup()
	{
		return backup;
	}
	
	public void setBackup(String backup)
	{
		this.backup = backup;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<tape>");
		sb.append("<tapeID>" + tapeID + "</tapeID>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("<visitID>" + visitID + "</visitID>");
		sb.append("<tapeNum>" + tapeNum + "</tapeNum>");
		sb.append("<studyID>" + studyID + "</studyID>");
		sb.append("<backup>" + backup + "</backup>");
		sb.append("</tape>");
		return sb.toString();
	}
}
