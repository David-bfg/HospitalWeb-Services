package com.mayo.db;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "patientcondition")
public class PatientCondition implements Serializable{
	@Id
	@Column(name = "conditionID")
	Integer conditionID;
	

	@Column(name = "clinicNum")
	Integer clinicNum;
	public Integer getConditionID() {
		return conditionID;
	}

	public void setConditionID(Integer conditionID) {
		this.conditionID = conditionID;
	}

	public Integer getClinicNum() {
		return clinicNum;
	}

	public void setClinicNum(Integer clinicNum) {
		this.clinicNum = clinicNum;
	}

}
