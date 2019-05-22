package com.rdyang.algorithm.string.test;

import org.junit.Test;

import com.rdyang.algorithm.string.ResetString;

public class ResetStringTest {

	ResetString rs = new ResetString();
	
	@Test
	public void test()
	{
		String str = "#a#d##ewr";
		String drawSpecialChar = rs.drawSpecialChar(str, '#');
		System.out.println(drawSpecialChar);
	}
	
	@Test
	public void test1()
	{
		String str = "#";
		String drawSpecialChar = rs.drawSpecialChar(str, '#');
		System.out.println(drawSpecialChar);
	}
	
	@Test
	public void test2()
	{
		String str = "dsadf";
		String drawSpecialChar = rs.drawSpecialChar(str, '#');
		System.out.println(drawSpecialChar);
	}
	
	@Test
	public void test3()
	{
		String str = "abdec#badwa";
		String drawSpecialChar = rs.drawSpecialChar(str, 'a');
		System.out.println(drawSpecialChar);
	}
	
	@Test
	public void test4()
	{
		String drawSpecialChar = rs.drawSpecialChar(null, '#');
		System.out.println(drawSpecialChar);
	}
	
	@Test
	public void test5()
	{
		String str = "a#cc#eds#we";
		String drawSpecialChar = rs.drawSpecialChar(str, (char) 0);
		System.out.println(drawSpecialChar);
	}
}
