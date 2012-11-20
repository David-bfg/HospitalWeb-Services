package com.mayo.db;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class VisitResearchStudyRest {
	SessionFactory session; 
	public static VisitResearchStudyRest instance = null;

	private VisitResearchStudyRest()
	{
		session = HibernateUtil.getSessionFactory();
	}
	
	public void close()
	{
		session.close();
	}
	
	
	public static VisitResearchStudyRest getInstance()
	{
		if (instance==null)
		{
			instance = new VisitResearchStudyRest();
		}
		return instance;
	}
	

	public void post( int visitID,int irbNum) {
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		VisitResearchStudy pojo = new VisitResearchStudy();
		pojo.setIrbNum(irbNum);
		pojo.setVisitID(visitID);
		sess.save(pojo);
		tx.commit();
	}

	public void delete(int irbNum, int visitID) {
		
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		String del = "delete VisitResearchStudy ad where ad.visitID = :oldvisitID and ad.irbNum=:oldirbNum";
		
		int deletedEntities = sess.createQuery(del)
		        .setString( "oldvisitID", ""+irbNum).setString("oldirbNum", ""+visitID)
		        .executeUpdate();
		
		tx.commit();
	}
	
	public static void main(String[] args) {
		//VisitResearchStudyRest.getInstance().post( "100000001",1);
		//VisitResearchStudyRest.getInstance().delete(1, "100000001");
	}
}
