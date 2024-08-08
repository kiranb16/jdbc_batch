package Batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main2 {
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
		String query="insert into employees(name, job_title,salary) values(?,?,?)";
		PreparedStatement preparedStatement= connection.prepareStatement(query);
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("name");
			String name=scanner.nextLine();
			System.out.println("job_title");
			String job_title=scanner.nextLine();
			System.out.println("salary");
			double salary=scanner.nextDouble();
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, job_title);
			preparedStatement.setDouble(3, salary);
			preparedStatement.addBatch();
			System.out.println("Add your value Y/N");
			String decision= scanner.nextLine();
			if(decision.equalsIgnoreCase("N")) {
				break;
			}
			
			int [] result=preparedStatement.executeBatch();
			connection.commit();
			System.out.println("batch executed successfully... ");
		}
		
		
		System.out.println("batch executed SuccessFully...");
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
}
}
