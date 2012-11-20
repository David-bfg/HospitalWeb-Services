package com.mayo.db;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patientstudy")
public class PatientStudy implements Serializable{

	@Id
	@Column (name = "studyID")
	Integer studyID;
	
	@Column (name = "visitID")
	Integer visitID;
	
	public int getStudyID()
	{
		return studyID;
	}
	
	public void setStudyID(int studyID)
	{
		this.studyID = studyID;
	}
	
	public int getVisitID()
	{
		return visitID;
	}
	
	public void setVisitID(int visitID)
	{
		this.visitID = visitID;
	}
}
