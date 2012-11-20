package com.mayo.db;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Icd9ProcedureCodeRest {
	
	SessionFactory session;
	public static Icd9ProcedureCodeRest instance = null;
	
	private Icd9ProcedureCodeRest()
	{
		session = HibernateUtil.getSessionFactory();
	}
	
	public void close(){
		session.close();
	}
	
	public static Icd9ProcedureCodeRest getInstance()
	{
		if (instance == null)
		{
			instance = new Icd9ProcedureCodeRest();
		}
		return instance;
	}
	
	public Icd9ProcedureCode get(int id)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<Icd9ProcedureCode> pojoList = sess.createQuery("from Icd9ProcedureCode v where v.ICD9ProcedureCodeID=\'" + id + "\'").list();
		Icd9ProcedureCode returnIcd9ProcedureCode = pojoList.get(0);
		tx.commit();
		return returnIcd9ProcedureCode;
	}
	
	public List<Icd9ProcedureCode> getAll ()
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<Icd9ProcedureCode> pojoList = sess.createQuery("from Icd9ProcedureCode").list();
		tx.commit();
		return pojoList;
	}
	
	public List<Icd9ProcedureCode> getAllIcd9ProcedureCodes (String clinicNum)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<Icd9ProcedureCode> pojoList = sess.createQuery("from Icd9ProcedureCode v where v.clinicNum=\'" + clinicNum + "\'").list();
		tx.commit();
		return pojoList;
	}
	
	public void post(String icd9ProcedureCode, String clinicNum, String procedureDescriptor)
	{
		Session sess = session.getCurrentSession();
		// Starting the Transaction 
		Transaction tx = sess.beginTransaction();
		
		Icd9ProcedureCode pojo = new Icd9ProcedureCode();
		pojo.setIcd9ProcedureCode(icd9ProcedureCode);
		pojo.setClinicNum(Integer.parseInt(clinicNum));
		pojo.setprocedureDescriptor(procedureDescriptor);

		sess.save(pojo);
		tx.commit();
	}

	public void put (int icd9ProcedureCodeID, String icd9ProcedureCode, String clinicNum, String procedureDescriptor)
	{
			Session sess = session.getCurrentSession();
			// Starting the Transaction 
			Transaction tx = sess.beginTransaction();
			
			
			// Creating Pojo 
			List<Icd9ProcedureCode> pojoList = sess.createQuery("from Icd9ProcedureCode p where p.ICD9ProcedureCodeID=\'" + icd9ProcedureCodeID + "\'").list();
			Icd9ProcedureCode pojo = pojoList.get(0);
			
			pojo.setIcd9ProcedureCode(icd9ProcedureCode);
			pojo.setprocedureDescriptor(procedureDescriptor);
			pojo.setClinicNum(Integer.parseInt(clinicNum));
			
			// Saving POJO 
			sess.save(pojo);
			
			// Commiting the changes 
			tx.commit();
	}
	
	public void delete (int id)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		String del = "delete Icd9ProcedureCode v where v.ICD9ProcedureCodeID = :oldIcd9ProcedureCodeID";
		
		int deletedEntities = sess.createQuery(del)
		        .setString( "oldIcd9ProcedureCodeID", ""+id )
		        .executeUpdate();
		tx.commit();
	}
	
	public static void main(String [] arg)
	{
		Icd9ProcedureCodeRest icdObject = new Icd9ProcedureCodeRest();
		//System.out.println(Icd9ProcedureCodeRest.getInstance().get(1));
		//System.out.println(icdObject.getAll());
		System.out.println(icdObject.getAllIcd9ProcedureCodes("100000002"));
		//icdObject.post(8, "84.3", "8", "problemDescriptor");
		//icdObject.put(8, "aooooo", "90", "paoooooooo");
		//icdObject.delete(8);
		
	}
}
