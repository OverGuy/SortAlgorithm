package com.zeng.sort;

public class SortTestHelper {
	
	/**
	 * 生成完全随机数组
	 * @param n 数组的规模
	 * @param rightL 数组中元素的最小值
	 * @param rightR 数组中元素的最大值
	 * @return
	 */
	public static int[] generateRandomArray(int n, int rightL, int rightR){
		if(rightL > rightR || n < 0){
			throw new IllegalArgumentException();
		}
		
		int[] arr = new int[n];
		for(int i = 0; i < n; i ++){
			arr[i] = (int)(Math.random()*(rightR - rightL + 1) + rightL);
		}
		return arr;
	}
	
	/**
	 * 生成一个近乎有序的数组
	 * @param n 数组的规模
	 * @param swapTimes 进行数据交换的对数
	 * @return
	 */
	public static int[] generateNearlyOrderedArray(int n, int swapTimes){
		if(n < 0 || swapTimes < 0){
			throw new IllegalArgumentException();
		}
		int[] arr = new int[n];
		for(int i = 0; i < n; i ++){
			arr[i] = i;
		}
		for(int i = 0; i < swapTimes; i++){
			int posx = (int)(Math.random() * n);
			int posy = (int)(Math.random() * n);
			
			int temp = arr[posx];
			arr[posx] = arr[posy];
			arr[posy] = temp;
		}
		
		return arr;
	}
	
	/**
	 * 打印arr数组的所有内容
	 * @param arr
	 */
	public static void printArray(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	/**
	 * 测试sortClassName所对应的排序算法，对arr数组排序所得到的结果和算法运行时间
	 * @param sortClassName
	 * @param arr
	 */
	public static void testSort(String sortClassName, int[] arr, TestCallBack callBack){
		if(arr == null){
			throw new IllegalArgumentException("arr not null");
		}
		if(callBack == null){
			throw new IllegalArgumentException("callBack not null");
		}
		long startTime = System.currentTimeMillis();
		callBack.sort(arr);
		long endTime = System.currentTimeMillis();
		System.out.println(sortClassName + ": " + arr.length + " " + isSorted(arr) + " " + (endTime - startTime) + "ms");
	}

	/**
	 * 判断arr是否有序
	 * @param arr
	 * @return
	 */
	public static boolean isSorted(int[] arr){
		for(int i = 0; i < arr.length - 1; i ++){
			if(arr[i] > arr[i + 1]){
				return false;
			}
		}
		return true;
	}
}
