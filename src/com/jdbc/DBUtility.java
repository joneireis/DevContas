package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtility {
    
	private static Connection connection = null;
        
        
        

	public static Connection getConnection() {
            
            
		if (connection != null)
			return connection;
		else {
			// Store the database URL in a string
			String serverName = "192.168.96.93";
			String portNumber = "1521";
			String sid = "orcl";
			String dbUrl = "jdbc:oracle:thin:@//" + serverName + ":" + portNumber + "/" + sid;
			try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// set the url, username and password for the database
			connection = DriverManager.getConnection(dbUrl, "DBAIMPAR", "dbamvimpar");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return connection;
		}
	}
}