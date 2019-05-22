package com.rdyang.sort.test;

import org.junit.Test;

import com.rdyang.sort.QuickSort;
import com.rdyang.sort.SortUtil;

public class QuickSortTest extends SortBaseTest {
	
	private QuickSort quickSort = new QuickSort();
	@Test
	public void test() 
	{
		int[][] data = deepClone();
		for(int i = 0; i < data.length; i++)
		{
			SortUtil.print(data[i]);
			quickSort.sort(data[i], 0, data[i].length - 1);
			SortUtil.print(data[i]);
		}
	}

}
