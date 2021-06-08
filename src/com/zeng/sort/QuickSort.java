package com.zeng.sort;

import java.util.Arrays;

public class QuickSort {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final QuickSort quickSort = new QuickSort();
		final QuickSortTwoWays quickSortTwo = new QuickSortTwoWays();
		final MergeSort mergeSort = new MergeSort();
		int arr[] = SortTestHelper.generateRandomArray(1000000, 0, 10000000);
		int[] copyArr1 = Arrays.copyOf(arr, arr.length);
		int[] copyArr2 = Arrays.copyOf(arr, arr.length);
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
				quickSortTwo.quickSort2(arr);
			}});
		System.out.println();
		
		arr = SortTestHelper.generateNearlyOrderedArray(1000000, 100);
		copyArr1 = Arrays.copyOf(arr, arr.length);
		copyArr2 = Arrays.copyOf(arr, arr.length);
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
				quickSortTwo.quickSort2(arr);
			}});
		System.out.println();
		
		arr = SortTestHelper.generateRandomArray(1000000, 0, 10);
		copyArr1 = Arrays.copyOf(arr, arr.length);
		copyArr2 = Arrays.copyOf(arr, arr.length);
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
				quickSortTwo.quickSort2(arr);
			}});
		System.out.println();
	}

	
	public void quickSort(int[] arr){
		quickSort(arr, 0, arr.length - 1);
	}
	
	/**
	 * 使用递归，对arr[left...right]部分进行快速排序，区间是前闭后闭的
	 * @param arr
	 * @param left
	 * @param right
	 */
	private void quickSort(int[] arr, int left, int right){
		
//		if(left >= right){
//			return;
//		}
		
		//优化：对元素量比较少的部分，用插入排序法进行优化
		if(right - left <= 15){
			insertionSort(arr, left, right);
			return;
		}
		
		//先添加一些规划数组
		int p = partition(arr, left, right);
		//再对两部分数组分别做快速排序
		quickSort(arr, left, p - 1);
		quickSort(arr, p + 1, right);
	}
	
	/**
	 * 对arr[left...right]部分进行partition操作
	 * @param arr
	 * @param left
	 * @param right
	 * @return 返回p，使得arr[left...p-1] < arr[p]; arr[p+1...right] > arr[p]
	 */
	private int partition(int[] arr, int left, int right){
		//优化：随机化快速
		swap(arr, left, (int)(Math.random() * (right - left + 1) + left));
		
		int v = arr[left];
		//arr[left+1...j] < v; arr[j+1...i) > v
		//初始化两个为空的区间arr[left+1...j]和arr[j+1...i)
		//使得整个程序从初始的情况下，都满足这个条件。
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
	 * 插入排序算法，对数组中子数组[left, right]进行排序.
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
	 * 交换数组中两个元素的值
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
