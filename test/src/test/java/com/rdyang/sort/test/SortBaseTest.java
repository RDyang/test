package com.rdyang.sort.test;

import java.util.Random;

import org.junit.Test;

import com.rdyang.sort.SortUtil;

public class SortBaseTest {

	protected int[][] data;
	
	{
		Random r = new Random(100);
		data = new int[1][10];
		for(int i = 0; i < data.length; i++)
		{
			for(int j = 0; j < data[i].length; j++)
			{
				int nextInt = r.nextInt(100);
				data[i][j] = nextInt;
			}
		}
	}
	
	@Test
	public void testSortUtilSwap()
	{
		int[] data = new int[2];
		data[0] = 74;
		data[1] = 74;
		SortUtil.swap(data, 0, 1);
		SortUtil.print(data);
	}
	
	public int[][] deepClone()
	{
		int[][] d = new int[data.length][data[0].length];
		for (int i = 0; i < d.length; i++) {
			for (int j = 0; j < d[i].length; j++) {
				d[i][j] = data[i][j];
			}
		}
		return d;
	}
}
