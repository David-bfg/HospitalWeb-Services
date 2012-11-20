package com.mayo.db;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AssistiveDeviceRest 
{
	SessionFactory session;
	public static AssistiveDeviceRest instance = null;
	
	private AssistiveDeviceRest()
	{
		session = HibernateUtil.getSessionFactory();
	}
	
	public void close()
	{
		session.close();
	}
	
	public static AssistiveDeviceRest getInstance()
	{
		if (instance==null)
		{
			instance = new AssistiveDeviceRest();
		}
		return instance;
	}
	
	public AssistiveDevice getWithName(String name)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <AssistiveDevice> pojoList = sess.createQuery("from AssistiveDevice ad where ad.name=\'"+name+"\'").list();
		if(pojoList.size()==0)
		{
			tx.commit();
			return null;
		}
		AssistiveDevice returnAssistiveDevice = pojoList.get(0);
		tx.commit();
		return returnAssistiveDevice;
	}
	
	public AssistiveDevice get(int id)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <AssistiveDevice> pojoList = sess.createQuery("from AssistiveDevice ad where ad.assistiveDeviceID=\'"+id+"\'").list();
		AssistiveDevice returnAssistiveDevice = pojoList.get(0);
		tx.commit();
		return returnAssistiveDevice;
	}
	
	public List<AssistiveDevice> getAll()
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <AssistiveDevice> pojoList = sess.createQuery("from AssistiveDevice").list();
		tx.commit();
		return pojoList;
	}
	
	public List<AssistiveDevice> getAllAssistiveDevice(String clinicNum)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <AssistiveDevice> pojoList = sess.createQuery("Select t from AssistiveDevice t, PatientAssistiveDevice pad where pad.assistiveDeviceID=t.assistiveDeviceID and pad.clinicNum=\'"+clinicNum+"\' ").list();
		tx.commit();
		
		for(AssistiveDevice a: pojoList) {
			System.out.println(a);
		}
		return pojoList;
	}
	
	public void put(String oldName, String newName, String clinicNum)
	{
			AssistiveDevice aDev= this.getWithName(oldName);
			if(aDev!=null) {
			PatientAssistiveDeviceRest.getInstance().delete(aDev.getAssistiveDeviceID(), clinicNum);
			post(newName, clinicNum);
			}
	}
	
	public void post(String name, String clinicNum)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List <AssistiveDevice> pojoList = sess.createQuery("from AssistiveDevice ad where ad.name=\'"+name+"\'").list();
		tx.commit();
		AssistiveDevice current;
		if(pojoList.size()==0)
		{
			sess = session.getCurrentSession();
			tx = sess.beginTransaction();
			AssistiveDevice pojo = new AssistiveDevice();
			pojo.setName(name);
			sess.save(pojo);
			tx.commit();
			current = pojo;
		}
		else
			current = pojoList.get(0);
		
		PatientAssistiveDeviceRest.getInstance().post(clinicNum, current.getAssistiveDeviceID());
	}
	
	
	public void delete(String name, String clinicNum)
	{
		
		AssistiveDevice adev = this.getWithName(name);
		
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		String del = "delete PatientAssistiveDevice ad where ad.assistiveDeviceID = :oldADID and ad.clinicNum=:oldClinic";
		
		int deletedEntities = sess.createQuery(del)
		        .setString( "oldADID", ""+adev.getAssistiveDeviceID()).setString("oldClinic", clinicNum)
		        .executeUpdate();
		
		tx.commit();
	}
	
	public static void main(String [] arg)
	{		
		///System.out.println(AssistiveDeviceRest.getInstance().get(2));
		//System.out.println(AssistiveDeviceRest.getInstance().getAll());
		//System.out.println(AssistiveDeviceRest.getInstance().getAllAssistiveDevice("100000004"));
		//AssistiveDeviceRest.getInstance().post("Lab Device", "1000004");
		AssistiveDeviceRest.getInstance().getWithName("apple");
		//AssistiveDeviceRest.getInstance().delete("Lab Device", "1000004");
		//AssistiveDeviceRest.getInstance().put(8, "db Device");
		//AssistiveDeviceRest.getInstance().delete(8);
	}
}
