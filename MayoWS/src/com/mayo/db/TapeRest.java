package com.mayo.db;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TapeRest {
	
	SessionFactory session;
	public static TapeRest instance = null;
	
	private TapeRest()
	{
		session = HibernateUtil.getSessionFactory();
	}
	
	public void close(){
		session.close();
	}
	
	public static TapeRest getInstance()
	{
		if (instance == null)
		{
			instance = new TapeRest();
		}
		return instance;
	}
	
	public Tape get(int tapeID)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<Tape> pojoList = sess.createQuery("from Tape b where b.tapeID=\'" + tapeID + "\'").list();
		Tape returnTape = pojoList.get(0);
		tx.commit();
		return returnTape;
	}
	
	public List<Tape> getAll ()
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<Tape> pojoList = sess.createQuery("from Tape").list();
		tx.commit();
		return pojoList;
	}
	
	public List<Tape> getAllTapeForClinicNum(String clinicNum) {
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<Tape> pojoList = sess.createQuery("from Tape b where b.clinicNum=\'" + clinicNum + "\'").list();
		tx.commit();
		return pojoList;
	}
	
	public List<Tape> getAllTapeForVisit (int visitID)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<Tape> pojoList = sess.createQuery("from Tape b where b.visitID=\'" + visitID + "\'").list();
		tx.commit();
		return pojoList;
	}
	
	public void post( String clinicNum,int tapeNum, int studyID, String backup, int visitID)
	{
		Session sess = session.getCurrentSession();
		// Starting the Transaction 
		Transaction tx = sess.beginTransaction();
		
		
		// Creating Pojo 
		Tape pojo = new Tape();
		pojo.setClinicNum(Integer.parseInt(clinicNum));
		pojo.setTapeNum(tapeNum);
		pojo.setStudyID(studyID);
		pojo.setBackup(backup);
		pojo.setVisitID(visitID);
		
		sess.save(pojo);
		
		// Commiting the changes 
		tx.commit();
	}

	public void put(int tapeID, String clinicNum,int tapeNum, int studyID, String backup, int visitID)
	{
			Session sess = session.getCurrentSession();
			// Starting the Transaction 
			Transaction tx = sess.beginTransaction();
			
			List<Tape> pojoList = sess.createQuery("from Tape b where b.tapeID=\'" + tapeID + "\'").list();
			Tape pojo = pojoList.get(0);
			
			pojo.setClinicNum(Integer.parseInt(clinicNum));
			pojo.setTapeNum(tapeNum);
			pojo.setStudyID(studyID);
			pojo.setBackup(backup);
			pojo.setVisitID(visitID);
			
			// Saving POJO 
			sess.save(pojo);
			
			tx.commit();
	}
	
	public void delete (int tapeID)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		String del = "delete Tape b where b.tapeID = :oldtapeID";
		
		int deletedEntities = sess.createQuery( del )
		        .setString( "oldtapeID", ""+tapeID )
		        .executeUpdate();
		tx.commit();
	}
	
	public static void main(String [] arg)
	{
		
	}

	
}
