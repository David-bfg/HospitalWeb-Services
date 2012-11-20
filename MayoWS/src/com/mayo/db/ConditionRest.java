package com.mayo.db;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ConditionRest {
	SessionFactory session; 
	public static ConditionRest instance = null;

	private ConditionRest()
	{
		session = HibernateUtil.getSessionFactory();
	}
	
	public void close()
	{
		session.close();
	}	
	public static ConditionRest getInstance()
	{
		if (instance==null)
		{
			instance = new ConditionRest();
		}
		return instance;
	}
	

	public Condition getWithName(String name)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <Condition> pojoList = sess.createQuery("from Condition ad where ad.name like \'%"+name+"%\'").list();
		if(pojoList.size()==0)
		{
			tx.commit();
			return null;
		}
		Condition returnCondition = pojoList.get(0);
		tx.commit();
		
		return returnCondition;
	}
	
	
	public Condition get(int id)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <Condition> pojoList = sess.createQuery("from Condition ad where ad.conditionID=\'"+id+"\'").list();
		Condition returnCondition = pojoList.get(0);
		tx.commit();
		return returnCondition;
	}
	
	
	public List<Condition> getAllCondition(String clinicNum)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <Condition> pojoList = sess.createQuery("Select t from Condition t, PatientCondition pad where pad.conditionID=t.conditionID and pad.clinicNum=\'"+clinicNum+"\' ").list();
		tx.commit();
		
		for(Condition a: pojoList) {
			System.out.println(a);
		}
		return pojoList;
	}
	
	public void put(String oldName, String newName, String clinicNum)
	{
			Condition cond= this.getWithName(oldName);
			if(cond!=null) {
			PatientConditionRest.getInstance().delete(cond.getConditionID(), clinicNum);
			post(newName, clinicNum);
			}
	}
	
	public void post(String name, String clinicNum)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <Condition> pojoList = sess.createQuery("from Condition c where c.name=\'"+name+"\'").list();
		tx.commit();
		Condition current;
		if(pojoList.size()==0)
		{
			sess = session.getCurrentSession();
			tx = sess.beginTransaction();
			Condition pojo = new Condition();
			pojo.setName(name);
			sess.save(pojo);
			tx.commit();
			current = pojo;
		}
		else
			current = pojoList.get(0);
		
		PatientConditionRest.getInstance().post(clinicNum, current.getConditionID());
	}
	
	
	public void delete(String name, String clinicNum)
	{
		
		Condition adev = this.getWithName(name);
		
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		String del = "delete PatientCondition ad where ad.conditionID = :oldADID and ad.clinicNum=:oldClinic";
		
		int deletedEntities = sess.createQuery(del)
		        .setString( "oldADID", ""+adev.getConditionID()).setString("oldClinic", clinicNum)
		        .executeUpdate();
		
		tx.commit();
	}
	
	public static void main(String [] arg)
	{
		// System.out.println(ConditionRest.getInstance().get(2));
		// System.out.println(ConditionRest.getInstance().getAll());
		//System.out.println(ConditionRest.getInstance().getAllCondition("100000003"));
		// System.out.println(ConditionRest.getInstance().getWithName("stable"));
		// System.out.println(ConditionRest.getInstance().getAll());
			
	//System.out.println(ConditionRest.getInstance().getAllCondition("100000004"));
		//ConditionRest.getInstance().post("stable", "1000004");
		//ConditionRest.getInstance().delete("Stable", "1000004");
		//ConditionRest.getInstance().put(4, "db Device");
		//ConditionRest.getInstance().delete(8);
	}
}
