package com.mayo.db;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SystemUsedRest {
	SessionFactory session; 
	public static SystemUsedRest instance = null;

	private SystemUsedRest()
	{
		session = HibernateUtil.getSessionFactory();
	}
	
	public void close()
	{
		session.close();
	}	
	public static SystemUsedRest getInstance()
	{
		if (instance==null)
		{
			instance = new SystemUsedRest();
		}
		return instance;
	}
	

	public SystemUsed getWithName(String name)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <SystemUsed> pojoList = sess.createQuery("from SystemUsed ad where ad.name like \'%"+name+"%\'").list();
		if(pojoList.size()==0)
		{
			tx.commit();
			return null;
		}
		SystemUsed returnSystemUsed = pojoList.get(0);
		tx.commit();
		
		return returnSystemUsed;
	}
	
	public SystemUsed get(int systemUsedID)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <SystemUsed> pojoList = sess.createQuery("from SystemUsed ad where ad.systemUsedID=\'"+systemUsedID+"\'").list();
		SystemUsed returnSystemUsed = pojoList.get(0);
		tx.commit();
		return returnSystemUsed;
	}
	
	public List<SystemUsed> getAll()
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <SystemUsed> pojoList = sess.createQuery("from SystemUsed").list();
		tx.commit();
		return pojoList;
	}
	
	public List<SystemUsed> getAllSystemUsed(int visitID)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <SystemUsed> pojoList = sess.createQuery("Select t from SystemUsed t, VisitSystemUsed pad where pad.systemUsedID=t.systemUsedID and pad.visitID=\'"+visitID+"\' ").list();
		tx.commit();
		
		return pojoList;
	}
	
	
	public void put(String oldName, String newName, int visitID)
	{
			SystemUsed sys= this.getWithName(oldName);
			if(sys!=null) {
			VisitSystemUsedRest.getInstance().delete(sys.getSystemUsedID(), visitID);
			post(newName, visitID);
			}
	}
	
	
	public void post(String name, int visitID)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <SystemUsed> pojoList = sess.createQuery("from SystemUsed c where c.name=\'"+name+"\'").list();
		tx.commit();
		SystemUsed current;
		if(pojoList.size()==0)
		{
			sess = session.getCurrentSession();
			tx = sess.beginTransaction();
			SystemUsed pojo = new SystemUsed();
			pojo.setName(name);
			sess.save(pojo);
			tx.commit();
			current = pojo;
		}
		else
			current = pojoList.get(0);
		
		VisitSystemUsedRest.getInstance().post(visitID, current.getSystemUsedID());
	}
	
	
	public void delete(String name, int visitID)
	{
		
		SystemUsed adev = this.getWithName(name);
		
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		String del = "delete VisitSystemUsed ad where ad.systemUsedID = :oldsystemUsedID and ad.visitID=:oldvisitID";
		
		int deletedEntities = sess.createQuery(del)
		        .setString( "oldsystemUsedID", ""+adev.getSystemUsedID()).setString("oldvisitID", ""+visitID)
		        .executeUpdate();
		
		tx.commit();
	}
	
	public static void main(String [] arg)
	{		
	//	System.out.println(SystemUsedRest.getInstance().getAllSystemUsed(4));
		SystemUsedRest.getInstance().post("apple", 3);
	}
}
