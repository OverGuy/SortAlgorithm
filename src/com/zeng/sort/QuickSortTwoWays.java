package com.zeng.sort;

public class QuickSortTwoWays {
	
	public void quickSort2(int[] arr){
		quickSort2(arr, 0, arr.length - 1);
	}
	
	private void quickSort2(int[] arr, int left, int right){
		if(right - left <= 15){
			insertionSort(arr, left, right);
			return;
		}
		int p = partition2(arr, left, right);
		quickSort2(arr, left, p - 1);
		quickSort2(arr, p + 1, right);
	}
	
    /**
     * 双路快速排序的partition
     * @param arr
     * @param left
     * @param right
     * @return 返回值p，使得两个arr[left...p-1]<=arr[p],arr[p+1...right]>=arr[p]
     */
	private int partition2(int[] arr, int left, int right){
		//随机取一个元素和第一个元素交换位置，优化快速排序
		swap(arr, left, (int)(Math.random() * (right - left + 1)) + left);
		int v = arr[left];
		//定义两个索引i和j,分别区分两个部分[left+1,i)>=v,(j,right]>=v
		//给两个索引赋值，得到两个空的部分
		int i = left + 1, j = right;
		while(true){
			//这里判断arr[i] < v，而不是arr[i] <= v，是为了让重复的元素分布的更均匀一些
			while(i <= right && arr[i] < v) i++;
			//这里判断arr[j] > v，而不是arr[j] >= v，是为了让重复的元素分布的更均匀一些
			while(j >= left + 1 && arr[j] > v) j--;
			if(i > j) break;
			swap(arr, i, j);
			i++;
			j--;
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
