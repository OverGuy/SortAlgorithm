package com.zeng.sort;

import java.util.Arrays;

//求一个数组中逆序对的数量
public class ReversePairNum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReversePairNum reverseParNum = new ReversePairNum();
		int[] arr = {4, 3, 2, 1};
		System.out.println("ReversePairNum: " + reverseParNum.reversePairNum(arr));
	}
	
	private int mPairNum = 0; //逆序对的数量

	/**
	 * 求数组中逆袭对的个数
	 * @param arr
	 * @return
	 */
	public int reversePairNum(int[] arr){
		mPairNum = 0;
		
		reversePairNum(arr, 0, arr.length - 1);
		
		return mPairNum;
	}
	
	/**
	 * 利用归并排序的思想，递归的实现查找逆序对的个数。
	 * 算法过程跟归并排序算法的过程一样，只是增加了一个逆序对的计数器
	 * 对数组arr[left...right]进行归并排序。
	 * @param arr
	 * @param left
	 * @param right
	 * @return
	 */
	private void reversePairNum(int[] arr, int left, int right){
		//递归到底的退出条件，子数组只有一个元素或为空
		if(left >= right){
			return;
		}
		
		int mid = (left + right) / 2;
		reversePairNum(arr, left, mid); //对前面部分的子数组做归并排序
		reversePairNum(arr, mid + 1, right); //对后面部分的子数组做归并排序
		merge(arr, left, mid, right);
	}
	
	/**
	 * 归并的过程
	 * @param arr
	 * @param left
	 * @param mid
	 * @param right
	 */
	private void merge(int[] arr, int left, int mid, int right){
		int[] tempArr = Arrays.copyOfRange(arr, left, right + 1);
		int i = 0, j = mid - left + 1, index = left;
		while(true){
			if(i <= mid - left && j <= right - left){
				if(tempArr[i] <= tempArr[j]){
					arr[index] = tempArr[i];
					index++;
					i++;
				}else{
					arr[index] = tempArr[j];
					index++;
					j++;
					
					//这时候需要逆序对计数器增长
					mPairNum += mid - left - i + 1;
				}
			}else if(i <= mid - left){
				arr[index] = tempArr[i];
				index++;
				i++;
			}else if(j <= right - left){
				arr[index] = tempArr[j];
				index++;
				j++;
			}else{
				break;
			}
			
		}
	}
}
