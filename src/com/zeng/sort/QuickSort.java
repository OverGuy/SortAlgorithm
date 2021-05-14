package com.zeng.sort;

public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void quickSort(int[] arr){
		quickSort(arr, 0, arr.length - 1);
	}
	
	private void quickSort(int[] arr, int left, int right){
		
		if(left >= right){
			return;
		}
		
		//先添加一些规划数组
		int p = partition(arr, left, right);
		//再对两部分数组分别做快速排序
		quickSort(arr, left, p - 1);
		quickSort(arr, p + 1, right);
	}
	
	private int partition(int[] arr, int left, int right){
		int v = arr[left];
		int j = left;
		//for(int i = )
		return 0;
	}
}
