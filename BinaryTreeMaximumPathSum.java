/**
 * Given a binary tree, find the maximum path sum.
 * 
 * The path may start and end at any node in the tree.
 * 
 * For example: Given the below binary tree,
 * 
 *   1 
 *  / \ 
 * 2   3
 * 
 * Return 6.
 * 
 */

public class BinaryTreeMaximumPathSum {
	public int maxPathSum(TreeNode root) {
		if (root == null)
			return 0;
		int[] max = new int[1];
		max[0] = Integer.MIN_VALUE;
		maxPathSum(root, max);
		return max[0];
	}

	private int maxPathSum(TreeNode root, int[] max) {
		if (root.left == null && root.right == null) {
			max[0] = root.val > max[0] ? root.val : max[0];
			return root.val;
		} else if (root.left != null && root.right == null) {
			int left = maxPathSum(root.left, max);
			max[0] = left > 0 ? Math.max(left + root.val, max[0]) : Math.max(
					root.val, max[0]);
			return left > 0 ? left + root.val : root.val;
		} else if (root.left == null && root.right != null) {
			int right = maxPathSum(root.right, max);
			max[0] = right > 0 ? Math.max(right + root.val, max[0]) : Math.max(
					root.val, max[0]);
			return right > 0 ? right + root.val : root.val;
		} else {
			int left = maxPathSum(root.left, max);
			int right = maxPathSum(root.right, max);
			int tmp = root.val;
			tmp += left > 0 ? left : 0;
			tmp += right > 0 ? right : 0;
			max[0] = tmp > max[0] ? tmp : max[0];
			return Math.max(Math.max(left, right), 0) + root.val;
		}
	}
}


// 需要同时维护一个res，存储最大值，同时返回的是包含当前节点的（包含左支或者右支的path）
// 取当前点和左右边加和，当前点的值中最大的作为本层返回值。并在全局维护一个max。使用数组，因为是引用类型。
//所以在递归过程中可以保存结果。
这道题是求树的路径和的题目，不过和平常不同的是这里的路径不仅可以从根到某一个结点，
而且路径可以从左子树某一个结点，然后到达右子树的结点，就像题目中所说的可以起始和终结于任何结点
。在这里树没有被看成有向图，而是被当成无向图来寻找路径。
因为这个路径的灵活性，我们需要对递归返回值进行一些调整，而不是通常的返回要求的结果。
在这里，函数的返回值定义为以自己为根的一条从根到子结点的最长路径（这里路径就不是当成无向图了，
必须往单方向走）。这个返回值是为了提供给它的父结点计算自身的最长路径用，而结点自身的最长路径
（也就是可以从左到右那种）则只需计算然后更新即可。这样一来，一个结点自身的最长路径就是它的左子树
返回值（如果大于0的话），加上右子树的返回值（如果大于0的话），再加上自己的值。而返回值则是自己的
值加上左子树返回值，右子树返回值或者0（注意这里是“或者”，而不是“加上”，因为返回值只取一支的路径和
）。在过程中求得当前最长路径时比较一下是不是目前最长的，如果是则更新。算法的本质还是一次树的遍历
，所以复杂度是O(n)。而空间上仍然是栈大小O(logn)。代码如下：
public int maxPathSum(TreeNode root) {
    if(root==null)
        return 0;
    ArrayList<Integer> res = new ArrayList<Integer>();
    res.add(Integer.MIN_VALUE);
    helper(root,res);
    return res.get(0);
}


private int helper(TreeNode root, ArrayList<Integer> res)
{
    if(root == null)
        return 0;
    int left = helper(root.left, res);
    int right = helper(root.right, res);
    int cur = root.val + (left>0?left:0)+(right>0?right:0);
    if(cur>res.get(0))
        res.set(0,cur);
    return root.val+Math.max(left, Math.max(right,0));
}


// 这里result是int！！！
public class Solution {
     int result = -100000;
     public int max(int a,int b)
     {
         int themax = -100000;
         if(a>themax) themax = a;
         if(b>themax) themax = b;
         return themax;
     }
    public int maxSum(TreeNode root) {
        if(root==null)
        return 0;
        
        int media=0,l,r;
        media += root.val;
        l = maxSum(root.left);
        r = maxSum(root.right);
        if(l>0)
        media += l;
        if(r>0)
        media += r;
        if(media>result)
        result = media;
        int mm = max(root.val,max(root.val+l,root.val+r));
        return mm;
    }
    public int maxPathSum(TreeNode root) {
        maxSum(root);
        return result;
    }
}








