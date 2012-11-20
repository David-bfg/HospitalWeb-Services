package com.mayo.db;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PatientConditionRest {
	SessionFactory session; 
	public static PatientConditionRest instance = null;

	private PatientConditionRest()
	{
		session = HibernateUtil.getSessionFactory();
	}
	
	public void close()
	{
		session.close();
	}	
	public static PatientConditionRest getInstance()
	{
		if (instance==null)
		{
			instance = new PatientConditionRest();
		}
		return instance;
	}
	
	

	public void post(String clinicNum, int conditionID) {
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		PatientCondition pojo = new PatientCondition();
		pojo.setConditionID(conditionID);
		pojo.setClinicNum(Integer.parseInt(clinicNum));
		sess.save(pojo);
		tx.commit();
	}

	public void delete(int id, String clinicNum) {
		
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		String del = "delete PatientCondition ad where ad.conditionID = :oldconditionID and ad.clinicNum=:oldClinic";
		
		int deletedEntities = sess.createQuery(del)
		        .setString( "oldconditionID", ""+id).setString("oldClinic", clinicNum)
		        .executeUpdate();
		
		tx.commit();
	}
	
	public static void main(String[] args) {
		//PatientConditionRest.getInstance().post( "100000001",1);
		//PatientConditionRest.getInstance().delete(1, "100000001");
	}
}
