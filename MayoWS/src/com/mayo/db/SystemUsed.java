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
@Table(name = "systemused")
public class SystemUsed implements Serializable
{
	@Id
	@Column (name = "systemUsedID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer systemUsedID;
	
	@Column(name = "name")
	String name;
	
	public SystemUsed()
	{
		
	}
	
	public int getSystemUsedID()
	{
		return systemUsedID;
	}
	
	public void setSystemUsedID(int systemUsedID)
	{
		this.systemUsedID = systemUsedID;
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
		sb.append("<systemused>");
		sb.append("<systemUsedID>" + systemUsedID + "</systemUsedID>");
		sb.append("<name>" + name + "</name>");
		sb.append("</systemused>");
		return sb.toString();
	}
}
