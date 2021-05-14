package com.zeng.sort;

import java.util.Arrays;

/**
 * 用递归实现归并排序
 */
public class MergeSort {
	
	private static MergeSort mergeSort = new MergeSort();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n = 1000000;
		int[] arr = SortTestHelper.generateRandomArray(n, 0, 10000000);
		int[] arrInsert = Arrays.copyOf(arr, arr.length);
		SortTestHelper.testSort("MergeSort", arr, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				mergeSort.mergeSortBU(arr);
			}
			
		});
//		
//		SortTestHelper.testSort("InsertionSort", arrInsert, new TestCallBack(){
//
//			public void sort(int[] arr) {
//				// TODO Auto-generated method stub
//				InsertionSort.insertionSort(arr);
//			}
//			
//		});
	}
	
	public void mergeSort(int[] arr){
		mergeSort(arr, 0, arr.length - 1);
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
	 * 递归实现归并排序，对arr数组[left, right]区间(闭区间，包括left和right)进行归并排序
	 * @param arr
	 * @param left
	 * @param right
	 */
	private void mergeSort(int[] arr, int left, int right){
		//第二条优化，对元素量比较小的部分，用插入排序代替归并排序
		//这里有一个问题可以思考一下，对多小规模的数组用插入排序比较好呢？这里使用的是15，那么是不是有其他的值能达到更好的性能效果。
		//这个问题的答案肯定是随着测试用例的不同，性能效果会有所不同。不过统计意义上来讲，在完全随机生成的数据上，什么样的值是效果最优的，可以做实验统计一下。
//		if((right - left) <= 15){
//			insertionSort(arr, left, right);
//			return;
//		}
		
		if(left >= right){
			return;
		}
		
		int mid = (left + right) / 2;
		mergeSort(arr, left, mid);
		mergeSort(arr, mid + 1, right);
		//第一条优化，判断如果当前部分已经是有序的，则不需要归并
		if(arr[mid] > arr[mid + 1]){
			merge(arr, left, right, mid);
		}
	}
	
	/**
	 * 迭代自底向上实现归并排序
	 * @param arr
	 */
	private void mergeSortBU(int[] arr){
		int length = arr.length;
		for(int size = 1; size <= length; size += size){
			for(int i = 0; i + size < length; i += size + size){
				//对数组arr的两个子数组[i, i + size - 1]和[i + size, i + size + size + 1]进行归并
				merge(arr, i, Math.min(i + size + size - 1, length - 1), i + size - 1);
			}
		}
	}

	/**
	 * 归并数组中两个有序的子数组[left, mid]和[mid+1, right]
	 * @param arr
	 * @param left
	 * @param right
	 * @param mid
	 */
	private void merge(int[] arr, int left, int right, int mid){
		int[] aux = Arrays.copyOfRange(arr, left, right + 1);
		//初始化i指向左边子数组的起始位置，j指向右边子数组的起始位置
		int i = left, j = mid + 1;
		for(int k = left; k <= right; k ++){
			if(i > mid){
				//左边子数组的元素全部处理完毕
				arr[k] = aux[j - left];
				j++;
			}else if(j > right){
				//右边子数组的元素全部处理完毕
				arr[k] = aux[i - left];
				i++;
			}else if(aux[i - left] < aux[j - left]){
				//左边当前元素小余右边当前元素，处理左边的当前元素
				arr[k] = aux[i - left];
				i++;
			}else{
				//左边当前元素大于等于右边当前元素，处理右边的当前元素
				arr[k] = aux[j - left];
				j++;
			}
		}
	}
	
	
}
