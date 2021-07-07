package com.unitbv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class App {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://localhost/student";
	private static final String USERNAME = "flavia";
	private static final String PASSWORD = "flavia";
	
	public static void main(String[] args) {
		
		Connection connection = null;
		Statement statement = null;

		try {
			try {
				Class.forName(JDBC_DRIVER);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME,
					PASSWORD);
			statement = connection.createStatement();

			//String sql = "CREATE DATABASE STUDENT;";
			//String sql = "CREATE TABLE PERSON (id INTEGER not null, first_name VARCHAR(255), last_name VARCHAR(255), age INTEGER, PRIMARY KEY(id));";
			//String sql = "INSERT INTO PERSON (id, first_name, last_name, age) values ('2','Andrei','Popescu',22)";
			String injection = "'Ana' or 1=1";
			String sql = "SELECT * from person where first_name="+injection;
			ResultSet results = statement.executeQuery(sql);
			
			while(results.next()){
				String firstname = results.getString("first_name");
				String lastname = results.getString("last_name");
				int age = results.getInt("age");
				
				System.out.println(firstname+"-"+lastname+"-"+age);
			}
			results.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(connection != null)
					connection.close();
				if(statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
