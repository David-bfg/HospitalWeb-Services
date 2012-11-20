package com.mayo.db;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FileRest {
	
	SessionFactory session;
	public static FileRest instance = null;
	
	private FileRest()
	{
		session = HibernateUtil.getSessionFactory();
	}
	
	public void close(){
		session.close();
	}
	
	public static FileRest getInstance()
	{
		if (instance == null)
		{
			instance = new FileRest();
		}
		return instance;
	}
	
	public File get(int id)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<File> pojoList = sess.createQuery("from File f where f.id=\'" + id + "\'").list();
		File returnFile = pojoList.get(0);
		tx.commit();
		return returnFile;
	}
	
	
	public List<File> getAllFiles (String clinicNum)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<File> pojoList = sess.createQuery("from File f where f.clinicNum=\'" + clinicNum + "\'").list();
		tx.commit();
		return pojoList;
	}
	
	public void post(String filename, int clinicNum)
	{
		Session sess = session.getCurrentSession();
		// Starting the Transaction 
		Transaction tx = sess.beginTransaction();
		
		// Creating Pojo 
		File pojo = new File();
		pojo.setFileName(filename);
		pojo.setClinicNum(clinicNum);
		// Saving POJO 
		sess.save(pojo);
		
		// Commiting the changes 
		tx.commit();
	}

	public void put (int id, String filename, int clinicNum)
	{
			Session sess = session.getCurrentSession();
			// Starting the Transaction 
			Transaction tx = sess.beginTransaction();
			
			
			// Creating Pojo 
			List<File> pojoList = sess.createQuery("from File f where f.id=\'" + id + "\'").list();
			File pojo = pojoList.get(0);
			pojo.setFileName(filename);
			pojo.setClinicNum(clinicNum);
			// Saving POJO 
			sess.save(pojo);
			
			// Commiting the changes 
			tx.commit();
	}
	
	public void delete (int id)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		String del = "delete File f where f.id = :oldId";
		
		int deletedEntities = sess.createQuery( del )
		        .setString( "oldId", "" + id)
		        .executeUpdate();
		tx.commit();
	}
	
	public static void main(String [] arg)
	{
		//System.out.println(FileRest.getInstance().get("file1.xml"));
		//System.out.println(FileRest.getInstance().getAll());
		//System.out.println(FileRest.getInstance().getAllFiles("100000003"));
		//FileRest.getInstance().post("file5.txt", 100000004);
		//FileRest.getInstance().put(4,"file.txt", 100000001);
		FileRest.getInstance().delete(4);
	}
}
