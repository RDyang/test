package com.rdyang.sort;

/**
 * 冒泡排序，从头开始相邻两个元素比较，若前者大于后者，则互换位置。
 * 一轮排序下来，最大元素下沉到最后一个位置。
 * @author root
 *
 */
public class BubbleSort {

	public void sort(int[] data)
	{
		if (null == data)
		{
			throw new NullPointerException();
		}
		int times = 0;
		for(int i = 0; i < data.length; i++)
		{
			for (int j = 0; j < data.length - i - 1; j++)
			{
				if (data[j] > data[j + 1])
				{
					SortUtil.swap(data, j, j + 1);
				}
				times++;
			}
		}
		System.out.println("times : " + times);
		SortUtil.print(data);
	}
	
	public void optimizedSort(int[] data)
	{
		if(null == data)
		{
			throw new NullPointerException();
		}
		int times = 0;
		for(int i = 0; i < data.length; i++)
		{
			boolean flag = false;
			for(int j = 0; j < data.length - i - 1; j++)
			{
				if(data[j] > data[j + 1])
				{
					SortUtil.swap(data, j, j + 1);
					flag = true;
				}
				times++;
			}
			
			if (flag == false)
			{
				break;
			}
		}
		System.out.println("optimized times : " + times);
		SortUtil.print(data);
	}
	
	public void optimizedSort1(int[] data)
	{
		if(null == data)
		{
			throw new NullPointerException();
		}
		int times = 0;
		int size = data.length;
		for(int i = 0; i < size; i++)
		{
			boolean flag = false;
			for(int j = 0; j < size - 1; j++)
			{
				if(data[j] > data[j + 1])
				{
					SortUtil.swap(data, j, j + 1);
					flag = true;
				}
				times++;
			}
			size--;
			if (flag == false)
			{
				break;
			}
		}
		System.out.println("optimized1 times : " + times);
		SortUtil.print(data);
	}
	
	public void OptimizedSort2(int[] data)
	{
		if(null == data)
		{
			throw new NullPointerException();
		}
		int times = 0;
		int size = data.length;
		for(int i = 0; i < size; i++)
		{
			int size1 = 0;
			boolean flag = false;
			for(int j = 0; j < size - 1; j++)
			{
				if(data[j] > data[j + 1])
				{
					SortUtil.swap(data, j, j + 1);
					flag = true;
					size1 = j;
				}
				times++;
			}
			size = size1;
			if (flag == false)
			{
				break;
			}
		}
		System.out.println("optimized2 times : " + times);
		SortUtil.print(data);
	}
}
