package com.rdyang.sort;

public class SortUtil {

	/**
	 * 置换两个元素，使用位运算
	 * 注意：如果有i == j的情况，该方法不可使用
	 * 同样 a = a + b;
	 *    b = a - b;
	 *    a = a - b
	 *    一样不可以使用
	 * @param i
	 * @param j
	 */
	public static void swap(int[] data,int i, int j)
	{
		if(null != data)
		{
			data[i] = data[i] ^ data[j];
			data[j] = data[i] ^ data[j];
			data[i] = data[i] ^ data[j];
		}
	}
	
	public static void swapTmp(int[] data, int i, int j)
	{
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}
	
	public static void print(int[] data)
	{
		for (int i : data) {
			System.out.print(i + ",");
		}
		System.out.println();
	}
}
