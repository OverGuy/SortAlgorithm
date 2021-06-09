package com.zeng.sort;

/**
 * 三路快速排序
 * @author sks
 *
 */
public class QuickSort3Ways {
	
	/**
	 * 对数组arr排序
	 * @param arr
	 */
	public void quickSort3Ways(int[] arr){
		quickSort3Ways(arr, 0, arr.length - 1);
	}
	
	/**
	 * 使用递归,对数组区间arr[left...right]进行排序，区间前闭后闭
	 * 先是partition过程，将arr[left...right]分为 <v,==v,>v三部分
	 * 然后递归对 <v,>v两部分分别继续进行排序
	 * @param arr
	 * @param left
	 * @param right
	 */
	private void quickSort3Ways(int[] arr, int left, int right){
		//System.out.println(left + " " + right);
		//递归退出条件
		//对元素量较少的部分，用插入排序进行优化
		if(right - left <= 15){
			insertionSort(arr, left, right);
			return;
		}
		
		//partition过程
		//优化：随机化快速
		//随机取一个元素和第一个元素交换位置，作为标记元素
		swap(arr, left, (int)(Math.random() * (right - left + 1) + left));
		
		int v = arr[left];
		
		//定义并初始化下标索引，使得三部分区间为空
		int lt = left; //arr[left+1...lt] < v
		int gt = right + 1; //arr[gt...right] > v
		int i = left + 1; //arr[lt + 1, i) == v
		
		while(i < gt){
			if(arr[i] < v){
				swap(arr, i, lt + 1);
				lt ++;
				i ++;
			}else if(arr[i] > v){
				swap(arr, gt - 1, i);
				gt --;
			}else{ //arr[i] == v
				i ++;
			}
		}
		swap(arr, left, lt);
		
		quickSort3Ways(arr, left, lt - 1); //继续对 <v 部分进行快速排序
		quickSort3Ways(arr, gt, right); //继续对 >v 部分进行快速排序
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
