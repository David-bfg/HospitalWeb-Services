package com.mayo.db;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "assistivedevice")
public class AssistiveDevice implements Serializable 
{
	@Id
	@Column (name = "AssistiveDeviceID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer assistiveDeviceID;
	
	@Column(name = "Name")
	String name;
	
	public AssistiveDevice()
	{
		
	}
	
	public int getAssistiveDeviceID()
	{
		return assistiveDeviceID;
	}
	
	public void setAssistiveDeviceID(int assistiveDeviceID)
	{
		this.assistiveDeviceID = assistiveDeviceID;
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
		sb.append("<assistivedevice>");
		sb.append("<AssistiveDeviceID>" + assistiveDeviceID + "</AssistiveDeviceID>");
		sb.append("<Name>" + name + "</Name>");
		sb.append("</assistivedevice>");
		return sb.toString();
	}
}
