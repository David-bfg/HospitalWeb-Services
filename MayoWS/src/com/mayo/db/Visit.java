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
@Table(name = "visit")

public class Visit implements Serializable
{

	@Id
	@Column(name = "visitID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer visitID;
	
	@Column (name = "date")
	Date date;
	
	@Column (name = "visitNum")
	Integer visitNum;
	
	@Column (name = "clinicNum")
	Integer clinicNum;
	
	@Column (name = "provider")
	String provider;
	
	@Column (name = "kinesiologist")
	String kinesiologist;
	
	@Column (name = "dateProcessingComplete")
	Date dateProcessingComplete;
	
	@Column (name = "physicalTherapist")
	String physicalTherapist;
	
	@Column (name = "DateAnalysisComplete")
	Date dateAnalysisComplete;

	public Visit ()
	{
		
	}
	
	public Integer getVisitID() {
		return visitID;
	}

	public void setVisitID(Integer visitID) {
		this.visitID = visitID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getVisitNum() {
		return visitNum;
	}

	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}

	public Integer getClinicNum() {
		return clinicNum;
	}

	public void setClinicNum(Integer clinicNum) {
		this.clinicNum = clinicNum;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getKinesiologist() {
		return kinesiologist;
	}

	public void setKinesiologist(String kinesiologist) {
		this.kinesiologist = kinesiologist;
	}

	public Date getDateProcessingComplete() {
		return dateProcessingComplete;
	}

	public void setDateProcessingComplete(Date dateProcessingComplete) {
		this.dateProcessingComplete = dateProcessingComplete;
	}

	public String getPhysicalTherapist() {
		return physicalTherapist;
	}

	public void setPhysicalTherapist(String physicalTherapist) {
		this.physicalTherapist = physicalTherapist;
	}

	public Date getDateAnalysisComplete() {
		return dateAnalysisComplete;
	}

	public void setDateAnalysisComplete(Date dateAnalysisComplete) {
		this.dateAnalysisComplete = dateAnalysisComplete;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<Visit>");
		sb.append("<visitID>" + visitID + "</visitID>");
		sb.append("<date>" + date + "</date>");
		sb.append("<visitNum>" + visitNum + "</visitNum>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("<provider>" + provider + "</provider>");
		sb.append("<kinesiologist>" + kinesiologist + "</kinesiologist>");
		sb.append("<dateProcessingComplete>" + dateProcessingComplete + "</dateProcessingComplete>");
		sb.append("<physicalTherapist>" + physicalTherapist + "</physicalTherapist>");
		sb.append("<dateAnalysisComplete>" + dateAnalysisComplete + "</dateAnalysisComplete>");
		sb.append("</Visit>");
		return sb.toString();
	}
}
