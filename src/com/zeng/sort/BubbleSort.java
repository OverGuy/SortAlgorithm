package com.zeng.sort;

public class BubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = SortTestHelper.generateRandomArray(10000, 0, 1000000);
		SortTestHelper.testSort("BubbleSort", arr, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				bubbleSort1(arr);
			}
			
		});
	}

	public static void bubbleSort1(int arr[]){
		int length = arr.length;
		for(int i = 1; i < length; i ++){
			for(int j = 0; j < length - i; j++){
				if(arr[j + 1] < arr[j]){
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
	
	public static void bubbleSort(int arr[]){
		int length = arr.length;
		int i = length - 1;
		do{
			int swap = 0;
			for(int j = 0; j < i; j++){
				if(arr[j] > arr[j + 1]){
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					
					swap = j;
				}
			}
			i = swap;
		}while(i > 0);
	}
}
