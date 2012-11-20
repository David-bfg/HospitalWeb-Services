package com.mayo.db;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PatientStudyRest {
	SessionFactory session; 
	public static PatientStudyRest instance = null;

	private PatientStudyRest()
	{
		session = HibernateUtil.getSessionFactory();
	}
	
	public void close()
	{
		session.close();
	}	
	public static PatientStudyRest getInstance()
	{
		if (instance==null)
		{
			instance = new PatientStudyRest();
		}
		return instance;
	}
	
	

	public void post(int visitID, int studyID) {
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		PatientStudy pojo = new PatientStudy();
		pojo.setStudyID(studyID);
		pojo.setVisitID(visitID);
		sess.save(pojo);
		tx.commit();
	}

	public void delete(int studyID, int visitID) {
		
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		String del = "delete PatientStudy ad where ad.studyID = :oldstudyID and ad.visitID=:oldVisitID";
		
		int deletedEntities = sess.createQuery(del)
		        .setString( "oldstudyID", ""+studyID).setString("oldVisitID", ""+visitID)
		        .executeUpdate();
		
		tx.commit();
	}
	
	public static void main(String[] args) {
	}
}
