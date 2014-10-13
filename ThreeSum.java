

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a +
 * b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * 
 * Note:
 * 
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ? b ?
 * c) The solution set must not contain duplicate triplets.
 * 
 * For example, given array S = {-1 0 1 2 -1 -4},
 * 
 * A solution set is: (-1, 0, 1) (-1, -1, 2)
 */
3 Sum是two Sum的变种，可以利用two sum的二分查找法来解决问题。
 本题比two sum增加的问题有：解决duplicate问题，3个数相加返回数值而非index。
 首先，对数组进行排序。
 然后，从0位置开始到倒数第三个位置（num.length-3)，进行遍历，假定num[i]就是3sum中得第一个加数，然后从i+1的位置开始，进行2sum的运算。
 当找到一个3sum==0的情况时，判断是否在结果hashset中出现过，没有则添加。(利用hashset的value唯一性）
 因为结果不唯一，此时不能停止，继续搜索，low和high指针同时挪动。
 
 时间复杂度是O(n2)
 
 
public class ThreeSum {
	public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		Arrays.sort(num);
		int a, b, c, k, l;
		for (int i = 0; i <= num.length - 3; i++) {
			if (i > 0 && num[i] == num[i - 1])
				continue;
			a = num[i];
			k = i + 1;
			l = num.length - 1;
			while (k < l) {
				b = num[k];
				c = num[l];
				if (a + b + c == 0) {
					if (k != i + 1 && num[k] == num[k - 1]) {
						k++;
						continue;
					}
					if (l != num.length - 1 && num[l] == num[l + 1]) {
						l--;
						continue;
					}
					ArrayList<Integer> item = new ArrayList<Integer>();
					item.add(a);
					item.add(b);
					item.add(c);
					result.add(item);
					l--;
					k++;
				} else if (a + b + c > 0) {
					l--;
				} else {
					k++;
				}
			}
		}
		return result;
	}
}