package com.mayo.db;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PatientRest {
	
	SessionFactory session;
	public static PatientRest instance = null;
	
	PatientRest(){
		// Getting the Session Factory and session 
		session = HibernateUtil.getSessionFactory();
		
	}
	
	public void close(){
		session.close();
	}
	
	public Patient get(int id){
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		
		List<Patient> pojoList = sess.createQuery("from Patient p where p.clinicNum=\'" + id + "\'").list();
		Patient returnPatient = pojoList.get(0);
		
		tx.commit();
		
		return returnPatient;
	}
	
	public List<Patient> getAll(){
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		
		List<Patient> pojoList = sess.createQuery("from Patient").list();
		
		tx.commit();
		
		return pojoList;
	}
	
	public List<Patient> getAll(String query){
		List<Patient> list = new ArrayList<Patient>();
		dbBase.getInstance().openConnection();
		Connection conn = dbBase.getInstance().getConn();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(query);
			while(rs.next())
			{
				Patient pojo = new Patient();
				pojo.setClinicNum(rs.getInt(1));
				pojo.setDOB(rs.getDate(2));
				pojo.setWeight(rs.getDouble(3));
				
				pojo.setHeight(rs.getDouble(4));
				
				pojo.setGender(rs.getString(5).charAt(0));
				pojo.setSide(rs.getString(6).charAt(0));
				pojo.setExtremity(rs.getString(7).charAt(0));
			
				pojo.setLastname(rs.getString(8));
				pojo.setFirstname(rs.getString(9));
				
				pojo.setInvolved(rs.getString(10).charAt(0));
				pojo.setDominant(rs.getString(11).charAt(0));
				pojo.setMeasuredSide(rs.getString(12));
				pojo.setProblemDescriptor(rs.getString(13));
				list.add(pojo);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		return null;	
		}
		
	}
	
	
	public void post(int id, String FName, String LName, Date dob, char extremity, char gender, char side, double height, double weight, char involved, char dominant, String measuredSide, String problemDescriptor){
		Session sess = session.getCurrentSession();
		// Starting the Transaction 
		Transaction tx = sess.beginTransaction();
		
		
		// Creating Pojo 
		Patient pojo = new Patient();
		pojo.setClinicNum(new Integer(id));
		pojo.setFirstname(FName);
		pojo.setDOB(dob);
		pojo.setExtremity(new Character(extremity));
		pojo.setGender(new Character(gender));
		pojo.setHeight(new Double(height));
		pojo.setLastname(LName);
		pojo.setSide(new Character(side));
		pojo.setWeight(new Double(weight));
		pojo.setInvolved(involved);
		pojo.setDominant(dominant);
		pojo.setMeasuredSide(measuredSide);
		pojo.setProblemDescriptor(problemDescriptor);
		// Saving POJO 
		sess.save(pojo);
		
		// Commiting the changes 
		tx.commit();
	}
	
	public void update(int id, String FName, String LName, Date dob, char extremity, char gender, char side, double height, double weight, char involved, char dominant, String measuredSide, String problemDescriptor){
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();
		
		List<Patient> pojoList = sess.createQuery("from Patient p where p.clinicNum=\'" + id + "\'").list();
		Patient pojo = pojoList.get(0);
		pojo.setClinicNum(new Integer(id));
		pojo.setFirstname(FName);
		pojo.setDOB(dob);
		pojo.setExtremity(new Character(extremity));
		pojo.setGender(new Character(gender));
		pojo.setHeight(new Double(height));
		pojo.setLastname(LName);
		pojo.setSide(new Character(side));
		pojo.setWeight(new Double(weight));
		pojo.setInvolved(involved);
		pojo.setDominant(dominant);
		pojo.setMeasuredSide(measuredSide);
		pojo.setProblemDescriptor(problemDescriptor);
		// Saving POJO 
		sess.save(pojo);
		
		tx.commit();
	}
	
	public void delete(int id){
		Session sess = session.getCurrentSession();
		Transaction tx = sess.beginTransaction();

		String hqlDelete = "delete Patient c where c.clinicNum = :oldclinicNum";
	
		int deletedEntities = sess.createQuery( hqlDelete )
		        .setString( "oldclinicNum", ""+id )
		        .executeUpdate();
		tx.commit();
	}
	
	
	public static PatientRest getInstance() {
		if(instance==null)
			instance = new PatientRest();
		
		return instance;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		PatientRest actions = new PatientRest();
		int id = 31;
		//actions.post(id, "FName", "LName", new Date(), 'U', 'M', 'L', 128, 30);
		//actions.post(100000006, "Alloo", "Bhanta", new Date(), 'U', 'M', 'L', 167, 120, 'I', 'D', "Bilateral", "INJURY TO PERONEAL NERVE");
		//actions.update(100000006, "Alloo", "Gobi", new Date(), 'U', 'F', 'R', 100, 200, 'I', 'D', "Bilateral", "INJURY TO PERONEAL NERVE");
		//System.out.println(actions.getAll(""));
		//actions.delete(100000006);
		//System.out.println(actions.getAll());
		//actions.close();
		System.out.println(actions.get(100000001));
	//	System.out.println("Record Printed");	
		

		
	}

}
