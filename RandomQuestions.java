package com.Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RandomQuestions {
   public static void main(String[] args) throws SQLException{
	System.out.println("**************WELCOME TO QUIZ COMPETITION****************");
	System.out.println("             ");
	Scanner scanner=new Scanner (System.in);
	System.out.println("Select the option \n1. Start Examination \n2. Search Result \n3. Show MeritList");
	//Show the function of Application
	System.out.println("        ");
	int Option=scanner .nextInt();
	Connection Connection=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	//Start the exam
	if(Option==1)
	{
		System.out.println("Enter the UserName");
         String Name=scanner.next();
         System.out.println("Enter the Password");
         String Password=scanner.next();
         StartExamination startExamination=new StartExamination();
         }
	else if(Option==2)
	{
		GetResult getResult=new GetResult();
		getResult.getResult();
	}
	else if(Option==3)
	{
		try
		{
			StartExamination startExamination=new StartExamination();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection=DriverManager.getConnection("jdbc.mysql://localhost:3306/test","root","root");
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
		finally
		{
			Connection.close();
			preparedStatement.close();
			resultSet.close();
		}
	}
}
}
