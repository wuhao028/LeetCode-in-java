
/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * 
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * 
 * Note:
 * 
 * The solution is guaranteed to be unique.
 *
 */

public class GasStation {
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int sum = 0, total = 0, len = gas.length, index = -1;
		for (int i = 0; i < len; i++) {
			sum += gas[i] - cost[i];
			total += gas[i] - cost[i];
			if (sum < 0) {
				index = i;
				sum = 0;
			}
		}
		return total >= 0 ? index + 1 : -1;
	}
}



Note:
The solution is guaranteed to be unique.

题目大意：有n个加油站首尾相连成一个圆，已知每个加油站的油量，以及从第i个加油站到第i+1个加油站需消耗的油量，问：能否开车从某个加油站出发，循环一圈又回到起点，如果可以返回出发的起点（车的邮箱容量是无限的）。

该题实际上可以转化为求最大字段和问题，只不过是求循环数组的最大子段和。

我们把gas[i] - cost[i]保存在数组edge中，然后对数组edge求最大子段和（求最大子段和和时，我们把edge数组看作首尾相连的循环数组），如果edge的所有元素之和>=0，则汽车可以循环一圈，且这个最大子段的起始位置就是汽车的出发位置；若edge之和小于0，则汽车不能循环一圈。我们之所以对edge数组求最大子段和，是因为按照最大子段和问题的特性，保证了汽车从最大子段的起始端出发到最大子段的末尾的过程中汽车油箱内的油量一直是正数，且汽车走完该子段后，车内可以剩余最多的油。

其实：edge数组所有元素之和>=0 是 汽车可以循环一圈的充分必要条件

另外在求循环数组最大子段和时需要注意几点：（1）最大子段的起始端只会循环一次，即起始端不会两次经过起点（2）防止子段出现首尾相连的情况。这两点代码里都有注释

算法时间复杂度O(N)，代码如下：

复制代码
 1 class Solution {
 2 public:
 3     int canCompleteCircuit(vector<int> &gas, vector<int> &cost)
 4     {
 5         // Note: The Solution object is instantiated only once and is reused by each test case.
 6         const int n = gas.size();
 7         vector<int> edge(n);
 8         int sum = 0;//sum是edge数组所有元素之和
 9         for(int i = 0; i < n; i++)
10         {
11             edge[i] = gas[i] - cost[i];
12             sum += edge[i];
13         }
14         if(sum < 0)
15             return -1;
16         int startpos = 0, endpos = 0;
17         int maxSub = maxSubSegment(edge, startpos, endpos);
18         return startpos;
19     }
20 
21     //求循环数组的最大子段和,并返回最大子段的首尾位置
22     int maxSubSegment(vector<int>&arr, int &startpos, int &endpos)
23     {
24         int n = arr.size();
25         int result = INT_MIN, sum = -1;
26         int starting = -1, ending = -1;//当前子段的起始端
27         int i = 0, j=0; //j是i对n求模之前的值，即i = j%n
28         while(starting < n)
29         {
30             if(i == starting)//防止子段首尾相接
31                 break;
32             if(sum >= 0)
33             {
34                 sum += arr[i];
35                 ending = i;
36             }
37             else
38             {
39                 sum = arr[i];
40                 starting = j;
41                 //设置成j是为了控制while循环结束条件，
42                 //因为子段的起始位置不会过了尾部又循环到数组首部
43                 ending = i;
44             }
45             if(result < sum)
46             {
47                 result = sum;
48                 startpos = starting;
49                 endpos = ending;
50             }
51             i = (i+1)%n;
52             j ++;
53         }
54 
55         return result;
56     }
57 };
复制代码
 

 

关于循环数组最大子段和可以参考我的另一篇博文：最大子数组和（最大子段和），根据这篇博文，本题有以下代码：

复制代码
 1 class Solution {
 2 public:
 3     int canCompleteCircuit(vector<int> &gas, vector<int> &cost)
 4     {
 5         // Note: The Solution object is instantiated only once and is reused by each test case.
 6         const int n = gas.size();
 7         vector<int> edge(n);
 8         int sum = 0;//sum是edge数组所有元素之和
 9         for(int i = 0; i < n; i++)
10         {
11             edge[i] = gas[i] - cost[i];
12             sum += edge[i];
13         }
14         if(sum < 0)
15             return -1;
16         int startpos = 0, endpos = 0;
17         int maxSub = maxSumCycle(edge, startpos, endpos);
18         return startpos;
19     }
20 
21     //求循环数组的最大子段和,并返回最大子段的首尾位置
22     int maxSumCycle(vector<int>&vec, int &left, int&right)
23     {
24         int maxsum = INT_MIN, curMaxSum = 0;
25         int minsum = INT_MAX, curMinSum = 0;
26         int sum = 0;
27         int begin_max = 0, begin_min = 0;
28         int minLeft, minRight;
29         for(int i = 0; i < vec.size(); i++)
30         {
31             sum += vec[i];
32             if(curMaxSum >= 0)
33             {
34                 curMaxSum += vec[i];
35             }
36             else
37             {
38                 curMaxSum = vec[i];
39                 begin_max = i;
40             }
41      
42             if(maxsum < curMaxSum)
43             {
44                 maxsum = curMaxSum;
45                 left = begin_max;
46                 right = i;
47             }
48             ///////////////求和最小的子数组
49             if(curMinSum <= 0)
50             {
51                 curMinSum += vec[i];
52             }
53             else
54             {
55                 curMinSum = vec[i];
56                 begin_min = i;
57             }
58      
59             if(minsum > curMinSum)
60             {
61                 minsum = curMinSum;
62                 minLeft = begin_min;
63                 minRight = i;
64             }
65         }
66         if(maxsum >= sum - minsum)
67             return maxsum;
68         else
69         {
70             left = minRight+1;
71             right = minLeft-1;
72             return sum - minsum;
73         }
74     }
75 };
复制代码
 

 

网上看到了一种更优雅的实现，它的本质还是最大子段和，代码如下

复制代码
 1 public class Solution {
 2     public int canCompleteCircuit(int[] gas, int[] cost) {
 3         // Note: The Solution object is instantiated only once and is reused by each test case.
 4         int N = gas.length, startIndex = -1;
 5         int sum = 0, total = 0;
 6         for(int i = 0; i < N; i++){
 7             sum += (gas[i] - cost[i]);
 8             total += (gas[i] - cost[i]);
 9             if(sum < 0){
10                 startIndex = i; 
11                 sum = 0;
12             }
13         }
14         return total >= 0 ? startIndex + 1 : -1;
15     }
16 }
复制代码
解释：

若 i = k时，sum小于0，表示车无法到达第k个加油站，必须从下一个加油站开始出发，total来判断是否能够循环一圈