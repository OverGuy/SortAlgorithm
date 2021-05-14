#####核心思想：
1，遍历数组，找出其中最小（或最大）的元素。和第一个元素交换值，确定排序后第一个元素的值。
2，除去已经排序好的元素，再对剩余的元素进行遍历，找到其中最小（或最大）的元素，和剩余元素的第一个元素交换值
3，重复步奏2，直到数组中只剩下一个未排序的数，排序完成。
<br>
时间复杂度O(n^2)
<br>
```Java
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
```

#####总结
选择排序的思想非常简单，缺点也非常明显，对于任何一个数组，选择排序两层循环，每一层循环都必须完全的执行完成。正是因为如此，选择排序的效率在任何情况下都是非常差的O(n^2)。
