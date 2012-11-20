package com.mayo.db;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Icd9DiagnosticRest {
	
	SessionFactory session;
	public static Icd9DiagnosticRest instance = null;
	
	private Icd9DiagnosticRest()
	{
		session = HibernateUtil.getSessionFactory();
	}
	
	public void close(){
		session.close();
	}
	
	public static Icd9DiagnosticRest getInstance()
	{
		if (instance == null)
		{
			instance = new Icd9DiagnosticRest();
		}
		return instance;
	}
	
	public Icd9Diagnostic get(int id)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<Icd9Diagnostic> pojoList = sess.createQuery("from Icd9Diagnostic v where v.ICD9DiagnoticID=\'" + id + "\'").list();
		Icd9Diagnostic returnIcd9Diagnostic = pojoList.get(0);
		tx.commit();
		return returnIcd9Diagnostic;
	}
	
	public List<Icd9Diagnostic> getAll ()
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<Icd9Diagnostic> pojoList = sess.createQuery("from Icd9Diagnostic").list();
		tx.commit();
		return pojoList;
	}
	
	public List<Icd9Diagnostic> getAllIcd9Diagnostics (String clinicNum)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<Icd9Diagnostic> pojoList = sess.createQuery("from Icd9Diagnostic v where v.clinicNum=\'" + clinicNum + "\'").list();
		tx.commit();
		return pojoList;
	}
	
	public void post(String icd9Diagnostic, String clinicNum)
	{
		Session sess = session.getCurrentSession();
		// Starting the Transaction 
		Transaction tx = sess.beginTransaction();
		
		Icd9Diagnostic pojo = new Icd9Diagnostic();
		pojo.setIcd9Diagnostic(icd9Diagnostic);
		pojo.setClinicNum(Integer.parseInt(clinicNum));

		sess.save(pojo);
		tx.commit();
	}

	public void put (int icd9DiagnosticID, String icd9Diagnostic, String clinicNum)
	{
			Session sess = session.getCurrentSession();
			// Starting the Transaction 
			Transaction tx = sess.beginTransaction();
			
			
			// Creating Pojo 
			List<Icd9Diagnostic> pojoList = sess.createQuery("from Icd9Diagnostic p where p.ICD9DiagnoticID=\'" + icd9DiagnosticID + "\'").list();
			Icd9Diagnostic pojo = pojoList.get(0);
			pojo.setClinicNum(Integer.parseInt(clinicNum));
			pojo.setIcd9Diagnostic(icd9Diagnostic);
			pojo.setIcd9DiagnosticID(icd9DiagnosticID);
			
			// Saving POJO 
			sess.save(pojo);
			
			// Commiting the changes 
			tx.commit();
	}
	
	public void delete (int id)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		String del = "delete Icd9Diagnostic v where v.ICD9DiagnoticID = :oldIcd9DiagnosticID";
		
		int deletedEntities = sess.createQuery( del )
		        .setString( "oldIcd9DiagnosticID", ""+id )
		        .executeUpdate();
		tx.commit();
	}
	
	public static void main(String [] arg)
	{
		Icd9DiagnosticRest.getInstance().delete(7);
	}
}
