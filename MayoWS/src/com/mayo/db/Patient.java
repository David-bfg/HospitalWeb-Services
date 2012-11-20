package com.mayo.db;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient implements Serializable {

	public Patient() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "clinicNum")
	Integer clinicNum;

	@Column(name = "side")
	Character side;
	
	@Column(name = "extremity")
	Character extremity;

	@Column(name = "lastname")
	String lastname;

	@Column(name = "firstname")
	String firstname;

	@Column(name = "gender")
	Character gender;

	@Column(name = "height")
	Double height;

	@Column(name = "DOB")
	Date DOB;

	@Column(name = "weight")
	Double weight;
	
	@Column(name = "involved")
	Character involved;
	
	@Column(name = "dominant")
	Character dominant;
	
	@Column (name = "`Measured Side`")
	String measuredSide;
	
	@Column (name = "`Problem Descriptor`")
	String problemDescriptor;

	public Character getInvolved() {
		return involved;
	}

	public void setInvolved(Character involved) {
		this.involved = involved;
	}

	public Character getDominant() {
		return dominant;
	}

	public void setDominant(Character dominant) {
		this.dominant = dominant;
	}

	public String getMeasuredSide() {
		return measuredSide;
	}

	public void setMeasuredSide(String measuredSide) {
		this.measuredSide = measuredSide;
	}

	public String getProblemDescriptor() {
		return problemDescriptor;
	}

	public void setProblemDescriptor(String problemDescriptor) {
		this.problemDescriptor = problemDescriptor;
	}

	public Integer getClinicNum() {
		return clinicNum;
	}

	public void setClinicNum(Integer clinicNum) {
		this.clinicNum = clinicNum;
	}

	public Character getSide() {
		return side;
	}

	public void setSide(Character side) {
		this.side = side;
	}

	public Character getExtremity() {
		return extremity;
	}

	public void setExtremity(Character extremity) {
		this.extremity = extremity;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double double1) {
		this.height = double1;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dob) {
		DOB = dob;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double double1) {
		this.weight = double1;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<Patient>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("<lastname>" + lastname + "</lastname>");
		sb.append("<firstname>" + firstname + "</firstname>");
		sb.append("<dob>" + DOB + "</dob>");
		sb.append("<gender>" + gender + "</gender>");
		sb.append("<height>" + height + "</height>");
		sb.append("<weight>" + weight + "</weight>");
		sb.append("<side>" + side + "</side>");
		sb.append("<extremity>" + extremity + "</extremity>");
		sb.append("<involved>" + involved + "</involved>");
		sb.append("<dominant>" + dominant + "</dominant>");
		sb.append("<measuredSide>" + measuredSide + "</measuredSide>");
		sb.append("<problemDescriptor>" + problemDescriptor + "</problemDescriptor>");
		sb.append("</Patient>");
		return sb.toString();
	}
}
