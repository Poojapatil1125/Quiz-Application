package com.Project;

public class GradeTest {
public String getGrade(int Grade)
{
	if(Grade>=8 && Grade<=10)
	{
		return "Class A";
	}
	else if(Grade>=6 && Grade<8)
	{
		return "Class B";
	}
	else if(Grade==5)
	{
		return "Class C";
	}
	return "Fail";
}
}
