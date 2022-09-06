package com.Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StartExamination {
	public int getStartExam(String StudentName,String Password) throws SQLException
	{
		int score=0;
		  Connection connection=null;
		  PreparedStatement preparedStatement=null;
		  ResultSet resultSet=null;
		Scanner scanner=new Scanner(System.in);
		GradeTest gradeTest=new GradeTest();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			ArrayList<Integer> al=new ArrayList<Integer>();
			for(int i=1;i<=10;i++)
			{
				al.add(i);
			}
			Collections.shuffle(al);
			for(int i=0;i<10;i++)
			{
				preparedStatement=connection.prepareStatement("select * from Quiz Application where Qid= "+al.get(i));
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next())
				{
					System.out.println("Question: "+resultSet.getString(2));
					System.out.println("                                 ");
					System.out.println("1. "+resultSet.getString(4));
					System.out.println("2. "+resultSet.getString(5));
					System.out.println("3. "+resultSet.getString(6));
					System.out.println("4. "+resultSet.getString(7));
					int Answer=resultSet.getInt(3);
					int StudentAnswer=scanner.nextInt();
					System.out.println("------------------------------");
					if(StudentAnswer==Answer)
					{
						score++;
					}
				}
			}
			preparedStatement=connection.prepareStatement("insert into studentdetails(StudentName,Password,Score,Grade) Values(?,?,?,?)");
			preparedStatement.setString(1, StudentName);
			preparedStatement.setString(2, Password);
			preparedStatement.setInt(3, score);
			preparedStatement.setString(4, gradeTest.getGrade(score));
			int i=preparedStatement.executeUpdate();
			System.out.println("                      ");
			System.out.println("Congratulations,Your Exam is Completed");
			System.out.println("                                 ");
			preparedStatement=connection.prepareStatement("select * from studentdetails where StudentName= "+"'"+StudentName+"'"+"And Password="+"'"+Password+"'");
			resultSet=preparedStatement.executeQuery();
		     while(resultSet.next())
		     {
		    	 System.out.println("Remember your UserName and Password for Score retrieval in future.");
		     }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			connection.close();
			preparedStatement.close();
			resultSet.close();
		}
		return score;
	}
  public static void main(String[] args) {
	
}
}
