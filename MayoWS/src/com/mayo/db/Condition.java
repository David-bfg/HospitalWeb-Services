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
@Table (name = "`condition`")

public class Condition implements Serializable
{
	@Id
	@Column(name = "conditionID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer conditionID;
	
	@Column(name = "name")
	String name;
	
	public Condition()
	{
		
	}
	
	public Integer getConditionID()
	{
		return conditionID;
	}
	
	public void setConditionID(Integer conditionID)
	{
		this.conditionID = conditionID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<condition>");
		sb.append("<conditionID>" + conditionID + "</conditionID>");
		sb.append("<name>" + name + "</name>");
		sb.append("</condition>");
		return sb.toString();
	}
}
