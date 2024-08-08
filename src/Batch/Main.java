package Batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
  public static void main(String[] args) throws ClassNotFoundException, SQLException {
	String url="jdbc:mysql://localhost:3306/devdb";
	String username="root";
	String password="root";
	try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	System.out.println("driver loaded Successfully");
	}catch(ClassNotFoundException e) {
			e.printStackTrace();
	}
	try {
		Connection connection= DriverManager.getConnection(url,username,password);
		System.out.println("connection establish.....");
		connection.setAutoCommit(false);
		Statement statement= connection.createStatement();
		statement.addBatch("INSERT INTO employees(name, job_title,salary) values('Kirhan','Senior Java developer',100000)");
		statement.addBatch("INSERT INTO employees(name, job_title,salary) values('Ravindra','Farmer',150000)");
		statement.addBatch("INSERT INTO employees(name, job_title,salary) values('Sanjay','Lead Java developer',200000)");
		int [] batchResult=statement.executeBatch();
		
		connection.commit();
		System.out.println("batch executed SuccessFully...");
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
}
}
