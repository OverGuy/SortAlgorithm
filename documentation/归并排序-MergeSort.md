###### 思路
当我们要排序一个数组时，归并排序会把这个数组分成两半，再单独对左边和右边的数组进行排序，之后再将它们归并起来。当然了当我们对左边和右边的数组进行排序时，再分别把左边的数组和右边的数组分成两半，然后对每一部分的数组先排序再归并。对于这每一部分的数组，我们依然是先分半再归并。<br>
当我们分到一定粒度的时候，每一部分就只有一个元素，那么此时我们不用排序，它本身就是有序的，所以这这时候我们只需要进行归并。归并到上一个层级之后，进一步进行归并，归并到一个更高的层级，逐层的向上归并。直至归并到最后一层的时候，整个数组全部有序了。<br>
<br>
![](https://github.com/OverGuy/SortAlgorithm/blob/master/image/merge_thinking.png)<br>
上图是我们对8个元素的数组进行归并排序，一步一步划分的过程。大家可以看到一层一层的下来一共分成了3级，到第3级的时候，每一部分就只剩下一个元素了。那么这个3是怎么来的呢，我们一共有8个元素，每次分成2份，经过3次这样除以2的计算，每一部分就只剩下一个元素了，也就是 log8 = 3；如果是n个元素，那么就有logn这么多层级（当n不是2^x时，logn就是一个浮点数，只需要向上取整就行了），所以总之层数是logn数量级的。<br>
在这种情况下，每一层要处理的元素的个数是一样的，虽然我们把它分成了不同的部分。如果每一层级的归并过程的时间复杂度是O(n)，那么我们就设计出了一个O(nlogn)级别的算法。<br>
事实上这也是O(nlogn)时间复杂度算法的主要来源，通常是通过二分法达到了logn这样的层级，之后每一层级用O(n)级别的算法来做事情。<br>
<br>
所以接下来的问题就变成了，我们能不能将整个数组分成两部分，之后这两部分都分别排好序，使用O(n)的算法将他们归并到一起，形成一个新的有序的数组。<br>
这个问题的答案是可以的，我们需要开辟一个同样大小的临时空间，来辅助我们完成这个归并过程（我们设计一个算法，通常是优先考虑时间复杂度，除非有其余特定的需求）<br>
具体的归并过程操作如下图：<br>
![](https://github.com/OverGuy/SortAlgorithm/blob/master/image/merge_top_1.png)<br>
![](https://github.com/OverGuy/SortAlgorithm/blob/master/image/merge_top_2.png)<br>
![](https://github.com/OverGuy/SortAlgorithm/blob/master/image/merge_top_3.png)<br>
![](https://github.com/OverGuy/SortAlgorithm/blob/master/image/merge_top_4.png)<br>
![](https://github.com/OverGuy/SortAlgorithm/blob/master/image/merge_top_5.png)<br>
![](https://github.com/OverGuy/SortAlgorithm/blob/master/image/merge_top_6.png)<br>

归并排序的本质就是一个递归排序的过程。<br>
时间复杂度：O(nlogn)<br>
代码实现如下：<br>

```Java
package sort;

import java.util.Arrays;

/**
 * 用递归实现归并排序
 */
public class MergeSort {
	
	public void mergeSort(int[] arr){
		mergeSort(arr, 0, arr.length - 1);
	}
	
	/**
	 * 递归实现归并排序，对arr数组[left, right]区间(闭区间，包括left和right)进行归并排序
	 * @param arr
	 * @param left
	 * @param right
	 */
	private void mergeSort(int[] arr, int left, int right){
		//递归执行的条件
		if(left < right){ 
			int mid = (left + right) / 2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			merge(arr, left, right, mid);
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
```

###### 优化
1，对两个已经排好序的子数组做归并操作，可以先判断一下左边子数组最后一个元素是否小余等于右边子数组第一个元素，如果是小余等于，则不需要做本次归并操作，因为当前部分已经是有序的。这个优化能提高归并排序算法对近乎有序数组的排序效率。<br>
2，对于近乎所有的高级排序算法，都存在一种优化情况，那就是对递归到底的优化。<br>
当递归到元素量非常小的时候，可以转而使用插入排序，来提高性能。这是基于两个原因：一方面当元素数据比较少的时候，整个数组近乎有序的概率比较大，此时插入排序有优势。另外一方面虽然插入排序最差的时间复杂度是O(n^2)，而归并排序是O(nlogn)，但是大家要知道对于时间复杂度来说n^2和nlogn也好，前面是有一个常数系数的，对于这个系数而言插入排序是要比归并排序小的。换句话说，当n小余到一定的程度的时候，插入排序会比归并排序要快一些。<br><br>
第二条优化性能测试：<br>
生成一个一百万个元素的数组，每个元素都是随机生成的[0,10000000]。在我的电脑上运行时间如下。<br>
优化前耗时：197ms<br>
优化后耗时：162ms<br>

这里有一个问题可以思考一下，对多小规模的数组用插入排序比较好呢？这里使用的是15，那么是不是有其他的值能达到更好的性能效果。这个问题的答案肯定是随着测试用例的不同，性能效果会有所不同。不过统计意义上来讲，在完全随机生成的数据上，什么样的值是效果最优的，可以做实验统计一下。<br>
以上两个优化都不是数量级的优化，经过这两个优化，归并排序依然是O(nlogn)级别的算法，但是还是让归并排序的整体性能更优。<br>
归并排序还有一些其他的优化方式，但是并不常用，代码写起来比较复杂，所以不这里一一列举。<br><br>
优化后的代码（这里只贴了优化修改的代码，其余代码同上）如下：<br>
```Java
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
		if((right - left) <= 15){
			insertionSort(arr, left, right);
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
```

###### 自底向上的归并排序算法
上面介绍的归并排序实现，都是自顶向下逐步递归来完成整个归并排序的过程，使用递归实现自顶向下的归并排序。<br>
当大家真正理解了归并排序以后，不一定非要用自顶向下的来完成归并排序过程，我们完全可以自底向上的来完成这个归并排序过程。具体过程如下。<br>
现在有一个数组，从左到右依次把这个数组划分成小段，比如说两个元素一个小段，然后来进行归并排序的过程。当归并排序完一轮之后，我们再四个元素一个小段的来进行归并排序，最后八个元素一个小段，在下面这个例子中就是整个数组全体，最终完成了整个归并排序的过程。<br>
在这样的一个实现中，我们并不需要递归，只需要迭代就可以完成归并排序。<br><br>


![](https://github.com/OverGuy/SortAlgorithm/blob/master/image/merge_bottom_1.png)<br>
![](https://github.com/OverGuy/SortAlgorithm/blob/master/image/merge_bottom_2.png)<br>
![](https://github.com/OverGuy/SortAlgorithm/blob/master/image/merge_bottom_3.png)<br>
![](https://github.com/OverGuy/SortAlgorithm/blob/master/image/merge_bottom_4.png)<br>
![](https://github.com/OverGuy/SortAlgorithm/blob/master/image/merge_bottom_45.png)<br>
![](https://github.com/OverGuy/SortAlgorithm/blob/master/image/merge_bottom_5.png)<br>
![](https://github.com/OverGuy/SortAlgorithm/blob/master/image/merge_bottom_6.png)<br>
![](https://github.com/OverGuy/SortAlgorithm/blob/master/image/merge_bottom_7.png)<br>

具体代码实现如下：<br>
```Java
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
```

自底向上的归并排序，有一个非常重要的作用。大家看看代码就会发现，这个排序过程中，没有使用通过数组索引直接获取元素，正因为如此，这样的一个自底向上的归并排序算法，可以非常好的使用O(nlogn)级别的时间，对链表这样的数据结构进行排序<br><br>
_merge通过索引访问值，这是因为我们的底层数据结构是数组。<br>
但仔细观察，你就会发现，_merge函数不会跳跃访问元素（这是和快排本质不同的地方）。我们只需要顺次访问元素就可以。而链表，相当于只提供顺次访问的功能。所以，归并排序的思路可以很容易地应用于链表。<br><br>

大规模数据的情况下，递归本身就是一种劣势。他增加了函数调用的开销，占用了系统栈空间，而且在一些底层编程环境中，甚至可能不被支持











