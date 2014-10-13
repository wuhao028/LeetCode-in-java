

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such
 * that a + b + c + d = target? 
 * 
 * Find all unique quadruplets in the array which gives the sum of target.
 * 
 * Note:
 * 
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a <= b <= c <= d) The solution set must not contain duplicate quadruplets.
 * 
 * For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 * 
 * A solution set is: (-1, 0, 0, 1) (-2, -1, 1, 2) (-2, 0, 0, 2)
 */

public class FourSum {
	public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		Arrays.sort(num);
		int length = num.length;
		for (int i = 0; i < length - 3; i++) {
			if (i > 0 && num[i] == num[i - 1])
				continue;
			for (int j = i + 1; j < length - 2; j++) {
				if (j > i + 1 && num[j] == num[j - 1])
					continue;
				int l = j + 1;
				int r = length - 1;
				while (l < r) {
					int delta = num[i] + num[j] + num[l] + num[r] - target;
					if (delta == 0) {
						if (l > j + 1 && num[l] == num[l - 1]) {
							l++;
							continue;
						}
						if (r < length - 1 && num[r] == num[r + 1]) {
							r--;
							continue;
						}
						ArrayList<Integer> item = new ArrayList<Integer>();
						item.add(num[i]);
						item.add(num[j]);
						item.add(num[l]);
						item.add(num[r]);
						ret.add(item);
						l++;
						r--;
					} else if (delta < 0) {
						l++;
					} else {
						r--;
					}
				}
			}
		}
		return ret;
	}
}


// HashSet来解决重复问题
public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
    HashSet<ArrayList<Integer>> hashSet = new HashSet<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    Arrays.sort(num);
    for (int i = 0; i <= num.length-4; i++) {
        for (int j = i + 1; j <= num.length-3; j++) {
            int low = j + 1;
            int high = num.length - 1;
 
            while (low < high) {
                int sum = num[i] + num[j] + num[low] + num[high];
 
                if (sum > target) {
                    high--;
                } else if (sum < target) {
                    low++;
                } else if (sum == target) {
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(num[i]);
                    temp.add(num[j]);
                    temp.add(num[low]);
                    temp.add(num[high]);
 
                    if (!hashSet.contains(temp)) {
                        hashSet.add(temp);
                        result.add(temp);
                    }
 
                    low++;
                    high--;
                }
            }
        }
    }
 
    return result;
}


//myversion

public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
         ArrayList<ArrayList<Integer>> result=new  ArrayList<ArrayList<Integer>>();
         
         Arrays.sort(num);
         int a,b,c,d,n;
         n=num.length;
         a=0;
         b=1;
         c=a+1;
         d=n-1;
         for(a=0;a<n-4;a++){
             //去重！！！！
             if (a > 0 && num[a] == num[a - 1])
				continue;
             for(b=a+1;b<n-3;b++){
                 if(b>a+1 && num[b]==num[b-1])
                 continue;
                
                while(c<d){
                    int less=target-num[a]-num[b];
                    if(num[c]+num[d]==less){
                        if (c > d + 1 && num[c] == num[c - 1]) {
							c++;
							continue;
						}
						if (d < n - 1 && num[d] == num[d + 1]) {
							d--;
							continue;
						}
						ArrayList<Integer> res=new ArrayList<Integer>();
                        res.add(num[a]);
                        res.add(num[b]);
                        res.add(num[c]);
                        res.add(num[d]);
                        result.add(res);
                      
                        c++;
                        d--;
                    }
                    if(num[c]+num[d]>less){d--; }
                    if(num[c]+num[d]<less){c++; }
                }
                 
                 
             }
        }
         
        
        return result;
        
        
    }