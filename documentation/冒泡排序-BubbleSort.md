###### 思路
1，循环遍历数组，每次都比较相邻两个元素的大小，如果前面的元素大于后面的元素，就进行值交换。遍历完一次之后，数组中最大的值就是数组的最后一个元素。（这个过程像水下面的一个气泡，会慢慢的漂浮上来，所以叫冒泡排序）
2，循环遍历剩余未排序的部分，将其中最大的元素移动到当前部分的最后面。
3，重复操作2，直到所有的元素都排好序。

时间复杂度 O(n^2)

```Java
	public static void bubbleSort(int arr[]){
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
```

###### 优化
1，如果里层循环一遍之后，没有发生任何的交换操作。则说明整个数组已经是有序的，结束排序。
2，每次记录里层循环最后一次交换的位置，在这个位置之后的元素是有序的，所以下次里层循环不用考虑这些元素。
优化代码如下：

```Java
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
```
