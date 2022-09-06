package com.Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetResult {
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
 public void getResult() throws SQLException
 {
	 try
	 {
		 Scanner scanner=new Scanner(System.in);
		 System.out.println("Enter the UserName");
		 String Name=scanner.next();
		 System.out.println("Enter the Password");
		 String Password=scanner.next();
		 StartExamination startExamination=new StartExamination();
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
		 preparedStatement=connection.prepareStatement("select * from studentdetails where (StudentName,Password)=(?,?)");
		 preparedStatement.setString(1, Name);
		 preparedStatement.setString(2, Password);
		 resultSet=preparedStatement.executeQuery();
		 while(resultSet.next())
		 {
			 System.out.println("StudentName="+resultSet.getString(2));
			 System.out.println("Score="+resultSet.getInt(4));
			 System.out.println("Grade="+resultSet.getString(5));
		 }
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	 finally {
		 connection.close();
		 preparedStatement.close();
		 resultSet.close();
	 }
 }
}
