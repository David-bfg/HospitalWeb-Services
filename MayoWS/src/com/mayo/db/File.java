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
@Table (name="file")

public class File implements Serializable 
{
	@Id
	@Column(name="fileId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	
	@Column(name="filename")
	String filename;
	
	@Column(name="clinicNum")
	Integer clinicNum;
	
	public File()
	{
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFileName()
	{
		return filename;
	}
	
	public void setFileName(String fileName)
	{
		this.filename = fileName;
	}
	
	public int getClinicNum()
	{
		return clinicNum;
	}
	
	public void setClinicNum(int clinicNum)
	{
		this.clinicNum = clinicNum;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<file>");
		sb.append("<fileId>"+id+"</fileId>");
		sb.append("<filename>" + filename + "</filename>");
		sb.append("<clinicNum>" + clinicNum + "</clinicNum>");
		sb.append("</file>");
		return sb.toString();
	}

}
