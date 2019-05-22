package com.rdyang.sort.test;

import org.junit.Test;

import com.rdyang.sort.BubbleSort;
import com.rdyang.sort.SortUtil;

public class BubbleSortTest extends SortBaseTest{

	BubbleSort sort = new BubbleSort();
	@Test
	public void test()
	{
		final int[][] d = deepClone();
		for (int i = 0; i < d.length; i++) {
			SortUtil.print(d[i]);
			sort.sort(d[i]);
		}
	}
	
	@Test
	public void testOptimizedSort()
	{
		int[][] data = deepClone();
		for(int i = 0; i < data.length; i++)
		{
			SortUtil.print(data[i]);
			sort.optimizedSort(data[i]);
		}
	}
	
	@Test
	public void testOptimizedSort1()
	{
		int[][] data = deepClone();
		for(int i = 0; i < data.length; i++)
		{
			SortUtil.print(data[i]);
			sort.optimizedSort1(data[i]);
		}
	}
	
	@Test
	public void testOptimizedSort2()
	{
		int[][] data = deepClone();
		for (int i = 0; i < data.length; i++) {
			SortUtil.print(data[i]);
			sort.OptimizedSort2(data[i]);
		}
	}
}
