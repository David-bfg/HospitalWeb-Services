package com.mayo.db;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "visitresearchstudy")
public class VisitResearchStudy implements Serializable {
	@Id
	@Column (name = "visitID")
	Integer visitID;
	
	@Column (name = "IRBNum")
	Integer irbNum;

	public Integer getVisitID() {
		return visitID;
	}

	public void setVisitID(int visitID) {
		this.visitID = visitID;
	}

	public Integer getIrbNum() {
		return irbNum;
	}

	public void setIrbNum(int irbNum) {
		this.irbNum = irbNum;
	}
	
}
