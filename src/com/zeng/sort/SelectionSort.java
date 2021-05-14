package com.zeng.sort;

public class SelectionSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = SortTestHelper.generateRandomArray(100000, 0, 1000000);
		SortTestHelper.testSort("selectionSort", arr, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				selectionSort(arr);
			}
			
		});
	}
	
	public static void selectionSort(int[] arrays){
		int len = arrays.length;
		for(int i = 0; i < len; i ++){
			//寻找[i,0)区间的最小值
			int minIndex = i;
			for(int j = i + 1; j < len; j++){
				if(arrays[j] < arrays[minIndex]){
					minIndex = j;
				}
			}
			//最小值和第一个值交换
			int temp = arrays[i];
			arrays[i] = arrays[minIndex];
			arrays[minIndex] = temp;
		}
	}

}
