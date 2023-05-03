package com.pizza.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseConnection {
	
	private DataBaseConnection() { //기본생성자 뽑고 private으로 막아라

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	private static DataBaseConnection connection
					= new DataBaseConnection();
	
	public static DataBaseConnection getInstance() {
		if(connection == null) {
			connection = new DataBaseConnection();
		}
		return connection;
	}
	

	// Connection 객체를 리턴하는 메서드
	public Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "hr";
		String upw = "hr";
		
		return DriverManager.getConnection(url, uid, upw);


	}

}
