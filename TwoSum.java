

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of integers, find two numbers such that they add up to a
 * specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2. Please note that
 * your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
 */

public class TwoSum {
	public class Num {
		private int value;
		private int index;

		public Num(int value, int index) {
			super();
			this.value = value;
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	public int[] twoSum(int[] numbers, int target) {
		Num[] newArray = new Num[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			newArray[i] = new Num(numbers[i], i);
		}
		Arrays.sort(newArray, new Comparator<Num>() {
			public int compare(Num n1, Num n2) {
				if (n1.getValue() == n2.getValue())
					return 0;
				if (n1.getValue() > n2.getValue())
					return 1;
				return -1;
			}
		});
		int[] result = new int[2];
		int i = 0, j = numbers.length - 1;
		while (i < j) {
			int tmp = newArray[i].getValue() + newArray[j].getValue();
			if (tmp == target) {
				result[0] = Math.min(newArray[i].getIndex() + 1,
						newArray[j].getIndex() + 1);
				result[1] = Math.max(newArray[i].getIndex() + 1,
						newArray[j].getIndex() + 1);
				break;
			} else if (tmp > target) {
				j--;
			} else {
				i++;
			}
		}
		return result;
	}
}

//超时！
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
     int [] res = new int[2];
     int n=numbers.length;
     for(int t=0;t<n;t++){
            for(int m=0;m<n;m++){
                if (numbers[m]==target-numbers[t]&& (m!=n))
                res[0]=t+1;
                res[1]=m+1;
            }
        }
     
        return res;
    }
}
思路1：

 利用HashMap，把target-numbers[i]的值放入hashmap中，value存index。遍历数组时，检查hashmap中是否已经存能和自己加一起等于target的值存在，存在的话把index取出，连同自己的index也出去，加1（index要求从1开始）后存入结果数组中返回。如果不存在的话，把自己的值和index存入hashmap中继续遍历。由于只是一遍遍历数组，时间复杂度为O(n)。

代码如下：

public int[] twoSum(int[] numbers, int target) {
        int [] res = new int[2];
        if(numbers==null||numbers.length<2)
            return res;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0; i < numbers.length; i++){
            if(!map.containsKey(target-numbers[i])){
                map.put(numbers[i],i);
            }else{
                res[0]= map.get(target-numbers[i])+1;
                res[1]= i+1;
                break;
            }
        }
        return res;
    }
思路2：

又碰到敏感的关键字array和target，自然而然想到二分查找法。但是这道题不能像传统二分查找法那样舍弃一半在另外一半查找，需要一点点挪low和high指针，所以时间复杂度为O(n)。

首先先将整个list拷贝并排序，使用Arrays.Sort()函数，时间复杂度O(nlogn) 
然后利用二分查找法，判断target和numbers[low]+numbers[high]。
target == numbers[low]+numbers[high]， 记录copy[low]和copy[high]；
target > numbers[low]+numbers[high]，说明最大的和最小的加一起还小于target，所以小值要取大一点，即low++；

target > numbers[low]+numbers[high], 说明最大的和最小的加一起大于target，那么大值需要往下取一点，即high--。

再把找到的两个合格值在原list中找到对应的index，返回即可。 

总共的时间复杂度为O(n+nlogn+n+n) = O(nlogn)。

代码如下：

public int[] twoSum(int[] numbers, int target) {
        int [] res = new int[2];
        if(numbers==null||numbers.length<2)
            return res;
        
        //copy original list and sort
        int[] copylist = new int[numbers.length];  
        System.arraycopy(numbers, 0, copylist, 0, numbers.length);  
        Arrays.sort(copylist);    
        
        int low = 0;
        int high = copylist.length-1;
        
        while(low<high){
            if(copylist[low]+copylist[high]<target)
                low++;
            else if(copylist[low]+copylist[high]>target)
                high--;
            else{
                res[0]=copylist[low];
                res[1]=copylist[high];
                break;
            }
        }
        
        //find index from original list
        int index1 = -1, index2 = -1;  
        for(int i = 0; i < numbers.length; i++){  
            if(numbers[i] == res[0]&&index1==-1)
                index1 = i+1;
            else if(numbers[i] == res[1]&&index2==-1)
                index2 = i+1;
       } 
        res[0] = index1;
        res[1] = index2;
        Arrays.sort(res);
        return res;
    }