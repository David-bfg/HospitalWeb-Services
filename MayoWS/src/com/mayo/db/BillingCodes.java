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
@Table(name = "billingcodes")
public class BillingCodes implements Serializable{
	@Id
	@Column(name = "billingCodeID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer billingCodeID;
	
	@Column(name = "visitCharge")
	Double visitCharge;
	
	@Column(name = "CPTCode")
	Integer CPTCode;
	
	@Column(name = "integerIncrement")
	Integer integerIncrement;
	
	@Column(name = "visitID")
	Integer visitID;
	
	public BillingCodes ()
	{
		
	}
	
	public int getBillingCodID()
	{
		return billingCodeID;
	}
	
	public void setBillingCodeID(int bcID)
	{
		this.billingCodeID = bcID;
	}
	
	public double getVisitCharge()
	{
		return visitCharge;
	}
	
	public void setVisitCharge(double visitCharge)
	{
		this.visitCharge = visitCharge;
	}
	
	public int getCPTCode()
	{
		return CPTCode;
	}
	
	public void setCPTCode(int CPTCode)
	{
		this.CPTCode = CPTCode;
	}
	
	public int getIntegerIncrement()
	{
		return integerIncrement;
	}
	
	public void setIntegerIncrement(int intIncrement)
	{
		this.integerIncrement = intIncrement;
	}
	
	public int getVisitID()
	{
		return visitID;
	}
	
	public void setVisitID(int visitID)
	{
		this.visitID = visitID;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<billingcodes>");
		sb.append("<billingCodeID>" + billingCodeID + "</billingCodeID>");
		sb.append("<visitCharge>" + visitCharge + "</visitCharge>");
		sb.append("<CPTCode>" + CPTCode + "</CPTCode>");
		sb.append("<integerIncrement>" + integerIncrement + "</integerIncrement>");
		sb.append("<visitID>" + visitID + "</visitID>");
		sb.append("</billingcodes>");
		return sb.toString();
	}
}
