package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static final String URL="jdbc:mysql://localhost:3306/mohit";
    private static final String USER="root";
    private static final String PASSWORD="root";
    
    static {
    	try {
    		
    		
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		
    	}
    	catch(ClassNotFoundException e) {
    		throw new RuntimeException("Failed to load Mysql jdbc Driver",e);
             			
    	}
    }
    public static Connection getConnection() throws SQLException{
    	return DriverManager.getConnection(URL,USER,PASSWORD);
    	
    }
    public static void closeConnection(Connection connection) {
    	if(connection != null) {
    		try {
    			connection.close();
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}
    	}
    }
}
