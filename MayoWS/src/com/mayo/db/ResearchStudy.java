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
@Table(name = "researchstudy")
public class ResearchStudy implements Serializable
{
	@Id
	@Column (name = "IRBNum")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer irbNum;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "description")
	String description;
	
	public ResearchStudy()
	{
		
	}
	
	public int getIRBNum()
	{
		return irbNum;
	}
	
	public void setIRBNum(int irbNum)
	{
		this.irbNum = irbNum;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String desc)
	{
		this.description = desc;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<researchstudy>");
		sb.append("<IRBNum>" + irbNum + "</IRBNum>");
		sb.append("<name>" + name + "</name>");
		sb.append("<description>" + description + "</description>");
		sb.append("</researchstudy>");
		return sb.toString();
	}
}
