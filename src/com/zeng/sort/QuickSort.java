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
		
		int p = partition(arr, left, right);
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
