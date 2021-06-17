package com.zeng.sort;

import java.util.Arrays;

public class FindSpecifyNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final FindSpecifyNumber findSpecifyNumber = new FindSpecifyNumber();
		int n = 10;
		int[] arr = SortTestHelper.generateRandomArray(n, 0, 10000000);
		SortTestHelper.testSort("FindSpecifyNumber", arr, new TestCallBack(){

			public void sort(int[] arr) {
				// TODO Auto-generated method stub
				findSpecifyNumber.findSpecifyNumber(arr, 0);
			}
			
		});
	}
	
	/**
	 * 查找数组中第n大的元素
	 * @param arr
	 * @param n
	 */
	public void findSpecifyNumber(int[] arr, int n){
		findSpecifyNumber(arr, n, 0, arr.length - 1);
	}

	/**
	 * 递归查找第n大的元素
	 * @param arr
	 * @param n
	 * @param left
	 * @param right
	 */
	private void findSpecifyNumber(int[] arr, int n, int left, int right){
		//递归退出条件
		if(left >= right){
			return;
		}
		
		//partition的过程
		int p = partition(arr, left, right);
		findSpecifyNumber(arr, n, left, p);
		findSpecifyNumber(arr, n, p + 1, left);
	}
	
	private int partition(int[] arr, int left, int right){
		//随机取一个元素，作为标定点
		swap(arr, left, (int)(Math.random() * (right - left + 1)) + left);
		
		int v = arr[left];
		
		//定义两个区间，[left + 1, p]<=v，[p + 1, i)>v
		int p = left, i = left + 1; 
		while(i <= right){
			if(arr[i] > v){
				i++;
			}else{
				swap(arr, i, p);
				p++;
				i++;
			}
		}
		swap(arr, left, p);
		
		return p;
	}
	
	/**
	 * 交换数组中的两个元素
	 * @param arr
	 * @param x
	 * @param y
	 */
	private void swap(int[] arr, int x, int y){
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
}
