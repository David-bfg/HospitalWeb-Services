package com.mayo.db;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudyRest {
	SessionFactory session; 
	public static StudyRest instance = null;

	private StudyRest()
	{
		session = HibernateUtil.getSessionFactory();
	}
	
	public void close()
	{
		session.close();
	}	
	public static StudyRest getInstance()
	{
		if (instance==null)
		{
			instance = new StudyRest();
		}
		return instance;
	}
	

	public Study getWithName(String studyName)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <Study> pojoList = sess.createQuery("from Study ad where ad.studyName like \'%"+studyName+"%\'").list();
		if(pojoList.size()==0)
		{
			tx.commit();
			return null;
		}
		Study returnStudy = pojoList.get(0);
		tx.commit();
		
		return returnStudy;
	}
	
	public Study get(int id)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <Study> pojoList = sess.createQuery("from Study ad where ad.studyID=\'"+id+"\'").list();
		Study returnStudy = pojoList.get(0);
		tx.commit();
		return returnStudy;
	}
	

	
	public List<Study> getAllStudy(int visitID)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <Study> pojoList = sess.createQuery("Select t from Study t, PatientStudy pad where pad.studyID=t.studyID and pad.visitID=\'"+visitID+"\' ").list();
		tx.commit();
		
		for(Study a: pojoList) {
			System.out.println(a);
		}
		return pojoList;
	}
	
	public void put(String oldName, String newName, int visitID)
	{
			Study study= this.getWithName(oldName);
			if(study!=null) {
			PatientStudyRest.getInstance().delete(study.getStudyID(), visitID);
			post(newName, visitID);
			}
	}
	

	
	public void post(String studyName, int visitID)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <Study> pojoList = sess.createQuery("from Study c where c.studyName=\'"+studyName+"\'").list();
		tx.commit();
		Study current;
		if(pojoList.size()==0)
		{
			sess = session.getCurrentSession();
			tx = sess.beginTransaction();
			Study pojo = new Study();
			pojo.setStudyName(studyName);
			sess.save(pojo);
			tx.commit();
			current = pojo;
		}
		else
			current = pojoList.get(0);
		
		PatientStudyRest.getInstance().post(visitID, current.getStudyID());
	}
	
	
	public void delete(String name, String visitID)
	{
		
		Study adev = this.getWithName(name);
		
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		String del = "delete PatientStudy ad where ad.studyID = :oldstudyID and ad.visitID=:oldVisitID";
		
		int deletedEntities = sess.createQuery(del)
		        .setString( "oldstudyID", ""+adev.getStudyID()).setString("oldVisitID", visitID)
		        .executeUpdate();
		
		tx.commit();
	}
	
	public static void main(String [] arg)
	{		
		//System.out.println(StudyRest.getInstance().getAllStudy(1));
	}
}
