package com.mayo.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PatientAssistiveDeviceRest {
	private static PatientAssistiveDeviceRest instance = null;
	
	SessionFactory session;
	
	private PatientAssistiveDeviceRest()
	{
		session = HibernateUtil.getSessionFactory();
	}
	
	public static PatientAssistiveDeviceRest getInstance() {
		if(instance==null)
			instance = new PatientAssistiveDeviceRest();
		return instance;
	}

	public void post(String clinicNum, int assistiveDeviceID) {
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		PatientAssistiveDevice pojo = new PatientAssistiveDevice();
		pojo.setPatientAssistiveDeviceID(assistiveDeviceID);
		pojo.setClinicNum(Integer.parseInt(clinicNum));
		sess.save(pojo);
		tx.commit();
	}

	public void delete(int assistiveDeviceID, String clinicNum) {
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		String del = "delete PatientAssistiveDevice ad where ad.assistiveDeviceID = :assistiveDeviceID and ad.clinicNum=:oldClinic";
		
		int deletedEntities = sess.createQuery(del)
		        .setString( "assistiveDeviceID", ""+assistiveDeviceID).setString("oldClinic", clinicNum)
		        .executeUpdate();
		
		tx.commit();
	}
	
	public static void main(String[] args) {
		PatientAssistiveDeviceRest.getInstance().post( "100000001",1);
		//PatientAssistiveDeviceRest.getInstance().delete(1, "100000001");
	}
}
