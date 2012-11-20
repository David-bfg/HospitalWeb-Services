package com.mayo.db;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class VisitSystemUsedRest {
	SessionFactory session; 
	public static VisitSystemUsedRest instance = null;

	private VisitSystemUsedRest()
	{
		session = HibernateUtil.getSessionFactory();
	}
	
	public void close()
	{
		session.close();
	}	
	public static VisitSystemUsedRest getInstance()
	{
		if (instance==null)
		{
			instance = new VisitSystemUsedRest();
		}
		return instance;
	}
	
	

	public void post(int visitID, int systemUsedID) {
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		VisitSystemUsed pojo = new VisitSystemUsed();
		pojo.setSystemUsedID(systemUsedID);
		pojo.setVisitID(visitID);
		
		sess.save(pojo);
		tx.commit();
	}

	public void delete(int id, int visitID) {		
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		String del = "delete VisitSystemUsed ad where ad.systemUsedID = :oldsystemUsedID and ad.visitID=:oldvisitID";
		
		int deletedEntities = sess.createQuery(del)
		        .setString( "oldsystemUsedID", ""+id).setString("oldvisitID", ""+visitID)
		        .executeUpdate();
		
		tx.commit();
	}
	
	public static void main(String[] args) {
		//VisitSystemUsedRest.getInstance().post( "100000001",1);
		//VisitSystemUsedRest.getInstance().delete(1, "100000001");
	}
}
