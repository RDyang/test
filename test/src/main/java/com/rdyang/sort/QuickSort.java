package com.rdyang.sort;

/**
 * 快排，以一个数为基准，然后从两边开始扫描，
 * 左边扫描到比基准数大的，然后从右开始找，找到比基准数小的然后置换
 * @author root
 *
 */
public class QuickSort {

	public void sort(int[] data, int left, int right)
	{
//		if (null == data || left < 0 || right < 0)
//		{
//			throw new NullPointerException();
//		}
		if(left < right)
		{
			int index = partition(data, left, right);
			sort(data, left, index - 1);
			sort(data, index + 1, right);
		}
	}
	
	public int partition(int[] data, int left, int right)
	{
		int key = data[left];
		while(left < right)
		{
			while(data[right] >= key && right > left)
			{
				right--;
			}
			SortUtil.swapTmp(data, left, right);
			while(data[left] <= key && left < right)
			{
				left++;
			}
			SortUtil.swapTmp(data, left, right);
		}
		SortUtil.swapTmp(data, left, right);
		return left;
	}
}
