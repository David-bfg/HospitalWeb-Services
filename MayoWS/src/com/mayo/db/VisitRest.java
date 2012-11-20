package com.mayo.db;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class VisitRest {
	
	SessionFactory session;
	public static VisitRest instance = null;
	
	private VisitRest()
	{
		session = HibernateUtil.getSessionFactory();
	}
	
	public void close(){
		session.close();
	}
	
	public static VisitRest getInstance()
	{
		if (instance == null)
		{
			instance = new VisitRest();
		}
		return instance;
	}
	
	public Visit get(int id)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<Visit> pojoList = sess.createQuery("from Visit v where v.visitID=\'" + id + "\'").list();
		Visit returnVisit = pojoList.get(0);
		tx.commit();
		return returnVisit;
	}
	
	public List<Visit> getAll ()
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<Visit> pojoList = sess.createQuery("from Visit").list();
		tx.commit();
		return pojoList;
	}
	
	public List<Visit> getAllVisits (String clinicNum)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<Visit> pojoList = sess.createQuery("from Visit v where v.clinicNum=\'" + clinicNum + "\'").list();
		tx.commit();
		return pojoList;
	}
	
	public void post(Date date, int clinicNum, String provider, String kinesiologist,
			Date dateProcessingComplete, String physicalTherapist, Date dateAnalysisComplete)
	{
		
		Visit visit = this.getVisit(clinicNum);
		int visitNum = 1;
		if(visit!=null)
			visitNum = visit.getVisitNum()+1;
		
		Session sess = session.getCurrentSession();
		// Starting the Transaction 
		Transaction tx = sess.beginTransaction();
		
		// Creating Pojo 
		Visit pojo = new Visit();
		pojo.setDate(date);
		pojo.setVisitNum(visitNum);
		pojo.setClinicNum(clinicNum);
		pojo.setProvider(provider);
		pojo.setKinesiologist(kinesiologist);
		pojo.setDateProcessingComplete(dateProcessingComplete);
		pojo.setPhysicalTherapist(physicalTherapist);
		pojo.setDateAnalysisComplete(dateAnalysisComplete);
		// Saving POJO 
		sess.save(pojo);
		
		// Commiting the changes 
		tx.commit();
	}

	private Visit getVisit(int clinicNum) {
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<Visit> pojoList = sess.createQuery("from Visit v where v.clinicNum=\'" + clinicNum + "\' order by v.visitNum desc").list();
		tx.commit();
		if(pojoList.size()==0)
			return null;
		
		return pojoList.get(0);
	}

	public void put (int id, Date date, int visitNum, int clinicNum, String provider, String kinesiologist,
				Date dateProcessingComplete, String physicalTherapist, Date dateAnalysisComplete)
	{
			Session sess = session.getCurrentSession();
			// Starting the Transaction 
			Transaction tx = sess.beginTransaction();
			
			
			// Creating Pojo 
			List<Visit> pojoList = sess.createQuery("from Visit p where p.visitID=\'" + id + "\'").list();
			Visit pojo = pojoList.get(0);
			pojo.setVisitID(new Integer(id));
			pojo.setDate(date);
			pojo.setVisitNum(visitNum);
			pojo.setClinicNum(clinicNum);
			pojo.setProvider(provider);
			pojo.setKinesiologist(kinesiologist);
			pojo.setDateProcessingComplete(dateProcessingComplete);
			pojo.setPhysicalTherapist(physicalTherapist);
			pojo.setDateAnalysisComplete(dateAnalysisComplete);
			// Saving POJO 
			sess.save(pojo);
			
			// Commiting the changes 
			tx.commit();
	}
	
	public void delete (int id)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		String del = "delete Visit v where v.visitID = :oldVisitID";
		
		int deletedEntities = sess.createQuery( del )
		        .setString( "oldVisitID", ""+id )
		        .executeUpdate();
		tx.commit();
	}
	
	public static void main(String [] arg)
	{
		System.out.println(VisitRest.getInstance().getVisit(100000006));
		/*
		int id = 500;
		Date date = new Date ();
		date.setDate(20);
		date.setMonth(4);
		date.setYear(2010-1900);
		int visitNum = 4;
		int clinicNum = 100000003;
		String provider = "meHaha!";
		String kinesiologist = "me Again";
		Date dateProcessingComplete = new Date();
		dateProcessingComplete.setDate(21);
		dateProcessingComplete.setMonth(4);
		dateProcessingComplete.setYear(2010-1900);
		String physicalTherapist = "whoelse but me";
		Date dateAnalysisComplete = new Date();
		dateAnalysisComplete.setDate(22);
		dateAnalysisComplete.setMonth(4);
		dateAnalysisComplete.setYear(2010-1900);
		
		//System.out.println(vr.getAll());
		//vr.put(id, date, visitNum, clinicNum, provider, kinesiologist, 
				//dateProcessingComplete, physicalTherapist, dateAnalysisComplete);		
		VisitRest.getInstance().delete(id);
		*/
	}
}
