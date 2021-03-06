package com.zeng.sort;

import java.util.Arrays;

public class QuickSort {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final QuickSort quickSort = new QuickSort();
		final QuickSort2Ways quickSortTwo = new QuickSort2Ways();
		final QuickSort3Ways quickSortThree = new QuickSort3Ways();
		final MergeSort mergeSort = new MergeSort();
		int arr[] = SortTestHelper.generateRandomArray(1000000, 0, 10000000);
		int[] copyArr1 = Arrays.copyOf(arr, arr.length);
		int[] copyArr2 = Arrays.copyOf(arr, arr.length);
		int[] copyArr3 = Arrays.copyOf(arr, arr.length);
		SortTestHelper.testSort("MergeSort", arr, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				mergeSort.mergeSort(arr);
			}});
		SortTestHelper.testSort("QuickSort", copyArr1, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				quickSort.quickSort(arr);
			}});
		SortTestHelper.testSort("QuickSort2", copyArr2, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				quickSortTwo.QuickSort2Ways(arr);
			}});
		SortTestHelper.testSort("QuickSort3", copyArr3, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				quickSortThree.quickSort3Ways(arr);
			}});
		System.out.println();
		
		arr = SortTestHelper.generateNearlyOrderedArray(1000000, 100);
		copyArr1 = Arrays.copyOf(arr, arr.length);
		copyArr2 = Arrays.copyOf(arr, arr.length);
		copyArr3 = Arrays.copyOf(arr, arr.length);
		SortTestHelper.testSort("MergeSort", arr, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				mergeSort.mergeSort(arr);
			}});
		SortTestHelper.testSort("QuickSort", copyArr1, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				quickSort.quickSort(arr);
			}});
		SortTestHelper.testSort("QuickSort2", copyArr2, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				quickSortTwo.QuickSort2Ways(arr);
			}});
		SortTestHelper.testSort("QuickSort3", copyArr3, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				quickSortThree.quickSort3Ways(arr);
			}});
		System.out.println();
		
		arr = SortTestHelper.generateRandomArray(1000000, 0, 10);
		copyArr1 = Arrays.copyOf(arr, arr.length);
		copyArr2 = Arrays.copyOf(arr, arr.length);
		copyArr3 = Arrays.copyOf(arr, arr.length);
		SortTestHelper.testSort("MergeSort", arr, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				mergeSort.mergeSort(arr);
			}});
		SortTestHelper.testSort("QuickSort", copyArr1, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				quickSort.quickSort(arr);
			}});
		SortTestHelper.testSort("QuickSort2", copyArr2, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				quickSortTwo.QuickSort2Ways(arr);
			}});
		SortTestHelper.testSort("QuickSort3", copyArr3, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				quickSortThree.quickSort3Ways(arr);
			}});
		System.out.println();
	}

	
	public void quickSort(int[] arr){
		quickSort(arr, 0, arr.length - 1);
	}
	
	/**
	 * ??????????????????arr[left...right]???????????????????????????????????????????????????
	 * @param arr
	 * @param left
	 * @param right
	 */
	private void quickSort(int[] arr, int left, int right){
		
//		if(left >= right){
//			return;
//		}
		
		//????????????????????????????????????????????????????????????????????????
		if(right - left <= 15){
			insertionSort(arr, left, right);
			return;
		}
		
		//???????????????????????????
		int p = partition(arr, left, right);
		//??????????????????????????????????????????
		quickSort(arr, left, p - 1);
		quickSort(arr, p + 1, right);
	}
	
	/**
	 * ???arr[left...right]????????????partition??????
	 * @param arr
	 * @param left
	 * @param right
	 * @return ??????p?????????arr[left...p-1] < arr[p]; arr[p+1...right] > arr[p]
	 */
	private int partition(int[] arr, int left, int right){
		//????????????????????????
		swap(arr, left, (int)(Math.random() * (right - left + 1) + left));
		
		int v = arr[left];
		//arr[left+1...j] < v; arr[j+1...i) > v
		//??????????????????????????????arr[left+1...j]???arr[j+1...i)
		//??????????????????????????????????????????????????????????????????
		int j = left;
		for(int i = left + 1; i <= right; i++){
			if(arr[i] < v){
				swap(arr, j + 1, i);
				j++;
			}
		}
		swap(arr, left, j);
		
		return j;
	}
	
	/**
	 * ??????????????????????????????????????????[left, right]????????????.
	 * @param arr
	 * @param left
	 * @param right
	 */
	private void insertionSort(int[] arr, int left, int right){
		for(int i = left + 1; i <= right; i ++){
			int e = arr[i];
			int j = i;
			for(; j > left && arr[j - 1] > e; j --){
				arr[j] = arr[j - 1];
			}
			arr[j] = e;
		}
	}
	
	/**
	 * ?????????????????????????????????
	 * @param arr
	 * @param i
	 * @param j
	 */
	private void swap(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
