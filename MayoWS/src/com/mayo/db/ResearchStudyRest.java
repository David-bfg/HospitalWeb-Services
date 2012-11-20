package com.mayo.db;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ResearchStudyRest {
	SessionFactory session; 
	public static ResearchStudyRest instance = null;

	private ResearchStudyRest()
	{
		session = HibernateUtil.getSessionFactory();
	}
	
	public void close()
	{
		session.close();
	}	
	public static ResearchStudyRest getInstance()
	{
		if (instance==null)
		{
			instance = new ResearchStudyRest();
		}
		return instance;
	}
	

	public ResearchStudy getWithName(String name, String desc)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <ResearchStudy> pojoList = sess.createQuery("from ResearchStudy ad where ad.name like \'"+name+"\' and ad.description like \'"+desc+"\'").list();
		if(pojoList.size()==0)
		{
			tx.commit();
			return null;
		}
		ResearchStudy returnResearchStudy = pojoList.get(0);
		tx.commit();
		
		return returnResearchStudy;
	}
	

	public List<ResearchStudy> getAllResearchStudy(int visitID)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <ResearchStudy> pojoList = sess.createQuery("Select t from ResearchStudy t, VisitResearchStudy pad where pad.irbNum=t.irbNum and pad.visitID=\'"+visitID+"\' ").list();
		tx.commit();
		
		return pojoList;
	}
	
	
	public void put(String oldName, String newName, String desc, int visitID)
	{
			ResearchStudy resStudy= this.getWithName(oldName, desc);
			if(resStudy!=null) {
			VisitResearchStudyRest.getInstance().delete(resStudy.getIRBNum(), visitID);
			post(newName, desc, visitID);
			}
	}
	
	
	public void post(String name,String desc, int visitID)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <ResearchStudy> pojoList = sess.createQuery("from ResearchStudy c where c.name=\'"+name+"\' and c.description=\'"+desc+"\'").list();
		tx.commit();
		ResearchStudy current;

		if(pojoList.size()==0)
		{
			sess = session.getCurrentSession();
			tx = sess.beginTransaction();
			ResearchStudy pojo = new ResearchStudy();
			pojo.setName(name);
			pojo.setDescription(desc);
			sess.save(pojo);
			tx.commit();
			current = pojo;
		}
		else
			current = pojoList.get(0);
		
		VisitResearchStudyRest.getInstance().post(visitID, current.getIRBNum());
	}
	
	
	public void delete(String name, String desc, int visitID)
	{
		ResearchStudy adev = this.getWithName(name, desc);
		
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		String del = "delete VisitResearchStudy ad where ad.visitID = :oldvisitID and ad.irbNum=:oldirbNum";
		
		int deletedEntities = sess.createQuery(del)
		        .setString( "oldvisitID", ""+adev.getIRBNum()).setString("oldirbNum", visitID+"")
		        .executeUpdate();
		
		tx.commit();
	}
	
	public static void main(String [] arg)
	{
		ResearchStudyRest res = new ResearchStudyRest();
		//System.out.println(res.getAllResearchStudy(1));
		res.post("ResStudy3", "Description3", 2);
	}
}
