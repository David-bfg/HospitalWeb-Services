package com.mayo.db;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "visitsystemused")
public class VisitSystemUsed implements Serializable{

	@Id
	@Column (name = "systemUsedID")
	Integer systemUsedID;
	
	@Column (name = "visitID")
	Integer visitID;

	public Integer getSystemUsedID() {
		return systemUsedID;
	}

	public void setSystemUsedID(int systemUsedID) {
		this.systemUsedID = systemUsedID;
	}

	public Integer getVisitID() {
		return visitID;
	}

	public void setVisitID(int visitID) {
		this.visitID = visitID;
	}
}
