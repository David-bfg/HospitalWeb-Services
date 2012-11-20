package com.mayo.db;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BillingCodesRest {
	
	SessionFactory session;
	public static BillingCodesRest instance = null;
	
	private BillingCodesRest()
	{
		session = HibernateUtil.getSessionFactory();
	}
	
	public void close(){
		session.close();
	}
	
	public static BillingCodesRest getInstance()
	{
		if (instance == null)
		{
			instance = new BillingCodesRest();
		}
		return instance;
	}
	
	public BillingCodes get(int id)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<BillingCodes> pojoList = sess.createQuery("from BillingCodes b where b.billingCodeID=\'" + id + "\'").list();
		BillingCodes returnBillingCodes = pojoList.get(0);
		tx.commit();
		return returnBillingCodes;
	}
	
	public List<BillingCodes> getAll ()
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<BillingCodes> pojoList = sess.createQuery("from BillingCodes").list();
		tx.commit();
		return pojoList;
	}
	
	public List<BillingCodes> getAllBillingCodes (int visitID)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		List<BillingCodes> pojoList = sess.createQuery("from BillingCodes b where b.visitID=\'" + visitID + "\'").list();
		tx.commit();
		return pojoList;
	}
	
	public void post(double visitCharge,int CPTCode,int integerIncrement, int visitID)
	{
		Session sess = session.getCurrentSession();
		// Starting the Transaction 
		Transaction tx = sess.beginTransaction();
		
		
		// Creating Pojo 
		BillingCodes pojo = new BillingCodes();
		pojo.setCPTCode(CPTCode);
		pojo.setIntegerIncrement(integerIncrement);
		pojo.setVisitID(visitID);
		pojo.setVisitCharge(visitCharge);
		// Saving POJO 
		sess.save(pojo);
		
		// Commiting the changes 
		tx.commit();
	}

	public void put(int id, double visitCharge,int CPTCode,int integerIncrement, int visitID)
	{
			Session sess = session.getCurrentSession();
			// Starting the Transaction 
			Transaction tx = sess.beginTransaction();
			
			
			// Creating Pojo 
			List<BillingCodes> pojoList = sess.createQuery("from BillingCodes b where b.billingCodeID=\'" + id + "\'").list();
			BillingCodes pojo = pojoList.get(0);
			
			pojo.setVisitCharge(visitCharge);
			pojo.setCPTCode(CPTCode);
			pojo.setIntegerIncrement(integerIncrement);
			pojo.setVisitID(visitID);
			
			// Saving POJO 
			sess.save(pojo);
			
			// Commiting the changes 
			tx.commit();
	}
	
	public void delete (int id)
	{
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		String del = "delete BillingCodes b where b.billingCodeID = :oldVillingCodeID";
		
		int deletedEntities = sess.createQuery( del )
		        .setString( "oldVillingCodeID", ""+id )
		        .executeUpdate();
		tx.commit();
	}
	
	public static void main(String [] arg)
	{
		//System.out.println(BillingCodesRest.getInstance().get(10000121));
		//System.out.println(BillingCodesRest.getInstance().getAll());
		//System.out.println(BillingCodesRest.getInstance().getAllBillingCodes(10));
		//BillingCodesRest.getInstance().post(180.9, 566, 9, 16);
		//BillingCodesRest.getInstance().put(10000127, 890, 99, 25, 98);
		//BillingCodesRest.getInstance().delete(10000126);
	}
}
