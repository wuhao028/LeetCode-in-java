

/** 
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 *
 * For example,
 * 
 * Given n = 3, there are a total of 5 unique BST's.
 *
 *  1         3     3      2      1
 *   \       /     /      / \      \
 *    3     2     1      1   3      2
 *   /     /       \                 \
 *  2     1         2                 3
 */

public class UniqueBinarySearchTrees {
	public int numTrees(int n) {
		if (n == 1)
			return 1;
		if (n == 2)
			return 2;
		int[] record = new int[n + 1];
		record[0] = 1;
		record[1] = 1;
		record[2] = 2;
		for (int i = 3; i <= n; i++) {
			int tmp = 0;
			for (int k = 0; k < i; k++) {
				tmp += (record[k] * record[i - k - 1]);
			}
			record[i] = tmp;
		}
		return record[n];
	}
}

“这道题要求可行的二叉查找树的数量，其实二叉查找树可以任意取根，只要满足中序遍历有序的要求就可以。从处理子问题的角度来看，选取一个结点为根，就把结点
切成左右子树，以这个结点为根的可行二叉树数量就是左右子树可行二叉树数量的乘积，所以总的数量是将以所有结点为根的可行结果累加起来。写成表达式如下：


熟悉卡特兰数的朋友可能已经发现了，这正是卡特兰数的一种定义方式，是一个典型的动态规划的定义方式（根据其实条件和递推式求解结果）。所以思路也很明确了，维护量res[i]表示含有i个结点的二叉查找树的数量。根据上述递推式依次求出1到n的的结果即可。
时间上每次求解i个结点的二叉查找树数量的需要一个i步的循环，总体要求n次，所以总时间复杂度是O(1+2+...+n)=O(n^2)。空间上需要一个数组来维护，并且需要前i个的所有信息，所以是O(n)。"

 

 

看到了一个解释的更清楚的分析，转自http://fisherlei.blogspot.com/2013/03/leetcode-unique-binary-search-trees.html：

“

   这题想了好久才想清楚。其实如果把上例的顺序改一下，就可以看出规律了。 

      1         3     3      2      1
       \       /     /      / \      \
        3     2     1      1   3      2
       /     /       \                 \
      2     1         2                 3
 

   比如，以1为根的树有几个，完全取决于有二个元素的子树有几种。同理，2为根的子树取决于一个元素的子树有几个。以3为根的情况，则与1相同。

    定义Count[i] 为以[0,i]能产生的Unique Binary Tree的数目，

    如果数组为空，毫无疑问，只有一种BST，即空树，
    Count[0] =1

    如果数组仅有一个元素{1}，只有一种BST，单个节点
    Count[1] = 1

    如果数组有两个元素{1,2}， 那么有如下两种可能
    1                       2
     \                    /
       2                1
    Count[2] = Count[0] * Count[1]   (1为根的情况)
                  + Count[1] * Count[0]  (2为根的情况。

    再看一遍三个元素的数组，可以发现BST的取值方式如下：
    Count[3] = Count[0]*Count[2]  (1为根的情况)  // 可以理解为左边0个元素  右边两个元素
                  + Count[1]*Count[1]  (2为根的情况)  // 左边一个元素，右边一个元素
                  + Count[2]*Count[0]  (3为根的情况) //左边两个元素  右边0个元素

    所以，由此观察，可以得出Count的递推公式为
    Count[i] = ∑ Count[0...k] * [ k+1....i]     0<=k<i-1
    问题至此划归为一维动态规划。

   [Note]
    这是很有意思的一个题。刚拿到这题的时候，完全不知道从那下手，因为对于BST是否Unique，
	很难判断。最后引入了一个条件以后，立即就清晰了，即
    当数组为 1，2，3，4，.. i，.. n时，基于以下原则的BST建树具有唯一性：
   以i为根节点的树，其左子树由[1, i-1]构成， 其右子树由[i+1, n]构成。 

” 

同时为了根据递推式来写程序，需要将递推式简化一下。
根据卡特兰数，C0Cn+1，因为leetcode输入的参数是n，所以为了避免混淆，这里递推式写成Ct+1,
初始值为C0 = 1。

原始的递推式是： Ct+1 += Ci*Ct-i (0<= i <=t)
现在令变量num=t+1，那么t=num-1

所以原始递推式做变量替换得：Cnum += Ci*Cnum-1-i (0<= i <=num-1) 
而num的取值范围是[1, n]因为C0已知。

代码如下： 
复制代码
 1     public int numTrees(int n) {
 2         if(n == 0||n == 1)
 3             return 1;
 4         
 5         int[] C = new int[n+1];
 6         C[0] = 1;
 7      //递推式是Ct+1 += Ci*Ct-i(0<= i <= t)
 8      //令num = t+1
 9      //则 t = num-1;
10      //因此递推式化为：
11      //Cnum += Ci*Cnum-1-i(0<=i<=num-1, 1<=num<=n)
12      //C0 = 1 
13 
14     for(int num=1;num<=n;num++){  
15         for(int i=0;i<=num-1;i++){  
16             C[num] += C[i]*C[(num-1)-i];  
17         }  
18     }
19         return C[n];
20     }




分析：依次把每个节点作为根节点，左边节点作为左子树，右边节点作为右子树，那么总的数目等于左子树数目*右子树数目，实际只要求出前半部分节点作为根节点的树的数目，然后乘以2（奇数个节点还要加上中间节点作为根的二叉树数目）

递归代码：为了避免重复计算子问题，用数组保存已经计算好的结果


class Solution {
public:
    int numTrees(int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int nums[n+1]; //nums[i]表示i个节点的二叉查找树的数目
        memset(nums, 0, sizeof(nums));
        return numTreesRecur(n, nums);
    }
    int numTreesRecur(int n, int nums[])
    {
        if(nums[n] != 0)return nums[n];
        if(n == 0){nums[0] = 1; return 1;}
        int tmp = (n>>1);
        for(int i = 1; i <= tmp; i++)
        {
            int left,right;
            if(nums[i-1])left = nums[i-1];
            else left = numTreesRecur(i-1, nums);
            if(nums[n-i])right = nums[n-i];
            else right = numTreesRecur(n-i, nums);
            nums[n] += left*right;
        }
        nums[n] <<= 1;
        if(n % 2 != 0)
        {
            int val;
            if(nums[tmp])val = nums[tmp];
            else val = numTreesRecur(tmp, nums);
            nums[n] += val*val;
        }
        return nums[n];
    }
};
非递归代码：从0个节点的二叉查找树数目开始自底向上计算，dp方程为nums[i] = sum(nums[k-1]*nums[i-k]) (k = 1,2,3...i)

复制代码class Solution {
public:
    int numTrees(int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int nums[n+1]; //num[i]表示i个节点的二叉查找树数目
        memset(nums, 0, sizeof(nums));
        nums[0] = 1;
        for(int i = 1; i <= n; i++)
        {
            int tmp = (i>>1);
            for(int j = 1; j <= tmp; j++)
                nums[i] += nums[j-1]*nums[i-j];
            nums[i] <<= 1;
            if(i % 2 != 0)
                nums[i] += nums[tmp]*nums[tmp];
        }
        return nums[n];
    }
};
