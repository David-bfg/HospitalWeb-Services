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
@Table(name = "study")
public class Study implements Serializable
{
	@Id
	@Column (name = "studyID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer studyID;
	
	@Column(name = "studyName")
	String studyName;
	
	public Study()
	{
		
	}
	
	public int getStudyID()
	{
		return studyID;
	}
	
	public void setStudyID(int studyID)
	{
		this.studyID = studyID;
	}
	
	public String getStudyName()
	{
		return studyName;
	}
	
	public void setStudyName(String studyName)
	{
		this.studyName = studyName;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<study>");
		sb.append("<studyID>" + studyID + "</studyID>");
		sb.append("<studyName>" + studyName + "</studyName>");
		sb.append("</study>");
		return sb.toString();
	}
}
