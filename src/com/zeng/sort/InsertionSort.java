package com.zeng.sort;

public class InsertionSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] arr = SortTestHelper.generateRandomArray(100000, 0, 3);
		int[] arr = SortTestHelper.generateRandomArray(10000, 0, 1000000);
		SortTestHelper.testSort("InsertionSort", arr, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				insertionSort(arr, 0, arr.length - 1);
			}
			
		});
	}
	
	public static void insertionSort1(int[] arr){
		int length = arr.length;
		for(int i = 1; i < length; i ++){
			for(int j = i; j > 0; j --){
				if(arr[j - 1] > arr[j]){
					int temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}else{
					break;
				}
			}
		}
	}
	
	
	public static void insertionSort(int[] arr){
		int length = arr.length;
		for(int i = 1; i < length; i ++){
			int e = arr[i];
			int j = i;
			for( ; j > 0 && arr[j - 1] > e; j --){
				arr[j] = arr[j - 1];
			}
			arr[j] = e;
		}
	}
	
	/**
	 * 插入排序算法，对数组中子数组[left, right]进行排序.
	 * @param arr
	 * @param left
	 * @param right
	 */
	public static void insertionSort(int[] arr, int left, int right){
		for(int i = left + 1; i <= right; i ++){
			int e = arr[i];
			int j = i;
			for(; j > left && arr[j - 1] > e; j --){
				arr[j] = arr[j - 1];
			}
			arr[j] = e;
		}
	}
}
