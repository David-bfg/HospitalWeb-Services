package com.mayo.db;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class dbBase {

	private static dbBase instance = null;
	
	// default url
	private String DB_URL = "jdbc:mysql://localhost/mayo";

	// default user
	private String user = "root";

	// default pass
	private String pswd = "";

	private String DB_DRIVER = "com.mysql.jdbc.Driver";

	private Connection conn;

	public static dbBase getInstance() {
		if(instance==null) {
		instance = new dbBase();
		instance.initialize();
		}
		
		return instance;
	}
	
	private void initialize() {
		String file= "hibernate.cfg.xml";
		File xml = new File(file);
		Document doc;
		SAXReader reader = new SAXReader();
		try {
			doc = reader.read(xml);
			DB_URL = doc.selectSingleNode("//hibernate-configuration/session-factory/property[@name='connection.url']").getText();
			DB_DRIVER = doc.selectSingleNode("//hibernate-configuration/session-factory/property[@name='connection.driver_class']").getText();
			user = doc.selectSingleNode("//hibernate-configuration/session-factory/property[@name='connection.username']").getText();
			pswd = doc.selectSingleNode("//hibernate-configuration/session-factory/property[@name='connection.password']").getText();
			
			
			System.out.println(DB_URL);
			System.out.println(DB_DRIVER);
			System.out.println(user);
			System.out.println(pswd);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		String DB_URL = "jdbc:mysql://localhost/mayo";

		// default user
		 String user = "root";

		// default pass
		 String pswd = "";
	}

	public String openConnection() {
		try {
			Class.forName(DB_DRIVER).newInstance();
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(DB_URL, user, pswd);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}
	
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public Connection getConn() {
		return conn;
	}	
	
	public static void main(String[] args)
	{
		dbBase.getInstance();
	}
}
